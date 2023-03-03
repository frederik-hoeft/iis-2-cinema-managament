using IIS.Client.Interactive.CommandLine;
using System.CommandLine;
using SlaveProgram = IIS.Client.Program;

Command rootCommand = SlaveProgram.BuildCommandTree();

Shell shell = Shell.Create(rootCommand);
shell.Start();
