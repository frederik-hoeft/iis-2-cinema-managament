using IIS.Client.Interactive.AutoComplete;
using System;
using System.CommandLine;
using System.CommandLine.Completions;
using System.Diagnostics;
using System.Text;
using CProgram = IIS.Client.Program;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");

Command rootCommand = CProgram.BuildCommandTree();

CompletionTreeNode root = CreateAutoCompleteTree(null, rootCommand);

LineRenderer renderer = new();
StringBuilder bobTheBuilder = new();
List<string> arguments = new();

while (true)
{
    renderer.Line.Clear();
    arguments.Clear();
    bobTheBuilder.Clear();
    CompletionTreeNode node = root;
    string beforeCompletionBackup = string.Empty;
    bool isCurrentValid;
    bool isParentValid = true;
    bool isAutoCompleting = false;
    IEnumerator<CompletionItem>? enumerator = null;
    while (true)
    {
        renderer.RenderLine();
        ConsoleKeyInfo keyInfo = Console.ReadKey();
        if (char.IsLetter(keyInfo.KeyChar))
        {
            bobTheBuilder.Append(keyInfo.KeyChar);
            enumerator = null;
            renderer.Line.Append(keyInfo.KeyChar);
        }
        if (keyInfo.Key is ConsoleKey.Backspace)
        {
            if (renderer.Line.Length > 0)
            {
                renderer.PopLength(1);
                Console.Write(' ');
            }
            if (bobTheBuilder.Length > 0)
            {
                bobTheBuilder.Remove(bobTheBuilder.Length - 1, 1);
            }
            else if (arguments.Count > 0)
            {
                string pop = arguments[^1];
                arguments.RemoveAt(arguments.Count - 1);
                bobTheBuilder.Append(pop);
                isParentValid = true;
                node = node.Parent ?? node;
            }
        }
        if (keyInfo.Key is ConsoleKey.Spacebar or ConsoleKey.Enter)
        {
            string arg = bobTheBuilder.ToString();
            arguments.Add(arg);
            bobTheBuilder.Clear();
            enumerator = null;
            CompletionTreeNode? newNode = node.GetChildFor(arg);
            if (newNode != null)
            {
                node = newNode;
                isParentValid = true;
            }
            else
            {
                isParentValid = false;
            }
            renderer.Line.Append(keyInfo.KeyChar);
            if (keyInfo.Key is ConsoleKey.Enter)
            {
                Console.WriteLine();
                break;
            }
        }
        if (isParentValid)
        {
            if (!isAutoCompleting)
            {
                enumerator = node.AutoComplete(bobTheBuilder.ToString());
            }
            else if (keyInfo.Key is not ConsoleKey.Tab)
            {
                isAutoCompleting = false;
            }
        }
        isCurrentValid = isParentValid && (enumerator is not null || bobTheBuilder.Length is 0);
        if (keyInfo.Key is ConsoleKey.Tab && isParentValid && isCurrentValid)
        {
            if (enumerator is null)
            {
                continue;
            }
            // auto-complete
            if (enumerator.MoveNext())
            {
                if (isAutoCompleting)
                {
                    renderer.PopLength(bobTheBuilder.Length);
                    bobTheBuilder.Clear();
                    string completion = enumerator.Current.ToString();
                    bobTheBuilder.Append(completion);
                    renderer.Line.Append(completion);
                }
                else
                {
                    isAutoCompleting = true;
                    beforeCompletionBackup = bobTheBuilder.ToString();
                    string completion = enumerator.Current.ToString()[bobTheBuilder.Length..];
                    bobTheBuilder.Append(completion);
                    renderer.Line.Append(completion);
                }
            }
            else if (isAutoCompleting)
            {
                // cycled though all suggestions
                enumerator = null;
                isAutoCompleting = false;
                renderer.PopLength(bobTheBuilder.Length);
                bobTheBuilder.Clear();
                // restore original value
                bobTheBuilder.Append(beforeCompletionBackup);
                renderer.Line.Append(beforeCompletionBackup);
            }
            else
            {
                enumerator = null;
            }
        }
        renderer.IsValid = isCurrentValid && isParentValid;
    }
    //ProcessStartInfo cinemaClientInfo = new("cinema.exe", string.Join(' ', arguments))
    //{
    //    RedirectStandardOutput = true,
    //    RedirectStandardError = true,
    //    RedirectStandardInput = true,
    //    UseShellExecute = false,
    //};
    //Process cinemaClient = new()
    //{
    //    StartInfo = cinemaClientInfo,
    //    EnableRaisingEvents = true
    //};
    //cinemaClient.Start();
    //StreamReader outputReader = cinemaClient.StandardOutput;
    //StreamReader errorReader = cinemaClient.StandardError;
    //StreamWriter inputWriter = cinemaClient.StandardInput;

    //while (true)
    //{
    //    string s = outputReader.ReadToEnd();
    //    Console.Write(s);
    //    string e = errorReader.ReadToEnd();
    //    Console.Write(e);
    //    string? input = Console.ReadLine();
    //    inputWriter.Write(input);
    //    inputWriter.Flush();
    //}
}

void CinemaClient_ErrorDataReceived(object sender, DataReceivedEventArgs e)
{
    Console.WriteLine(e.Data);
}

void CinemaClient_OutputDataReceived(object sender, DataReceivedEventArgs e)
{
    Console.WriteLine(e.Data);
}

static CompletionTreeNode CreateAutoCompleteTree(CompletionTreeNode? parent, Symbol current)
{
    CompletionTreeNode node = new(current)
    {
        Parent = parent
    };
    if (current is Command command)
    {
        foreach (Symbol child in command.Children)
        {
            CompletionTreeNode childNode = CreateAutoCompleteTree(node, child);
            node.Children.Add(childNode);
        }
    }
    return node;
}

class LineRenderer
{
    public StringBuilder Line { get; } = new();

    public bool IsValid { get; set; } = true;

    private int _previousContentLength = 0;

    private readonly string _prompt = "> ";

    public void RenderLine()
    {
        if (!IsValid)
        {
            Console.ForegroundColor = ConsoleColor.Red;
        }
        if (Line.Length < _previousContentLength)
        {
            Console.Write('\r');
            Console.Write(new string(' ', _prompt.Length + _previousContentLength));
        }
        _previousContentLength = Line.Length;
        Console.Write('\r');
        Console.Write(_prompt);
        Console.Write(Line);
        Console.ResetColor();
    }

    public void PopLength(int length) => Line.Remove(Line.Length - length, length);
}