using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;
using System.Reflection;

namespace IIS.Client.ApiAccess;

internal static class ValidationService
{
    public static bool ValidateRecord<T>(T model) where T : IEquatable<T>
    {
        _ = model ?? throw new ArgumentNullException(nameof(model));

        // yes this is incredibly dumb... :P
        // when using validation attributes on record types they become parameter attributes (in the ctor) causing
        // them to be overlooked by the framework.
        // therefore we must manually validate them agains matching *property* names.
        List<(PropertyInfo MatchingProperty, List<ValidationAttribute> Attributes)> attributeData = model.GetType()
            .GetConstructors()
            .Select(c => c
                .GetParameters()
                .Select(param =>
                (
                    MatchingProperty: typeof(T).GetProperties().Where(prop => prop.Name.Equals(param.Name)).FirstOrDefault(),
                    Attributes: param.GetCustomAttributes()
                        .Where(a => a
                            .GetType().IsAssignableTo(typeof(ValidationAttribute)))
                        .Select(a => a as ValidationAttribute)
                        .ToList()
                ))
                .Where(tuple => tuple.MatchingProperty is not null)
                .ToList())
            .Aggregate(new List<(PropertyInfo, List<ValidationAttribute>)>(), (result, data) =>
            {
                result.AddRange(data!);
                return result;
            });

        bool success = true;

        if (attributeData.Count > 0)
        {
            List<ValidationResult> validationResults = new(0);
            foreach ((PropertyInfo MatchingProperty, List<ValidationAttribute> Attributes) in attributeData)
            {
                object? value = MatchingProperty.GetValue(model);
                if (!Validator.TryValidateValue(value!, _context, validationResults, Attributes))
                {
                    Console.ForegroundColor = ConsoleColor.Red;
                    foreach (ValidationResult validationResult in validationResults)
                    {
                        Console.WriteLine(validationResult.ErrorMessage?.Replace(_contextDummyTypeName, MatchingProperty.Name) + $" Value was '{value}'.");
                    }
                    success = false;
                    validationResults.Clear();
                }
            }
        }
        Console.ResetColor();
        return success;
    }

    private static readonly object _contextDummy = new ValidationContextDummy();

    private static readonly string _contextDummyTypeName = _contextDummy.GetType().Name;

    private static readonly ValidationContext _context = new(_contextDummy);
}

file record class ValidationContextDummy();