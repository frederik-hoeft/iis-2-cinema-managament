namespace IIS.Client.Interactive.CommandLine.Parser;

internal record CinemaArguments(Stack<(string Argument, bool IsValid)> Arguments, string Line);