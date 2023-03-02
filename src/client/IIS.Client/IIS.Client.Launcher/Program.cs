using IIS.Client.Interactive.CommandLine;
using System.CommandLine;
using SlaveProgram = IIS.Client.Program;

Stdout.ResetColor();
Stdout.WriteLine($"Welcome to the IIS cinema client v{SlaveProgram.Version}!");

Command rootCommand = SlaveProgram.BuildCommandTree();

Shell shell = Shell.Create(rootCommand);
shell.Start();
