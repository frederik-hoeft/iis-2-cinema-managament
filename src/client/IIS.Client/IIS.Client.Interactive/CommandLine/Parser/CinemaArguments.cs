using IIS.Client.Interactive.CommandLine.Parser.AutoComplete;

namespace IIS.Client.Interactive.CommandLine.Parser;

internal record CinemaArguments(Stack<ArgumentStackFrame> Arguments, string Line);

internal record ArgumentStackFrame(string Argument, bool IsValid, CompletionTreeNode? Node);