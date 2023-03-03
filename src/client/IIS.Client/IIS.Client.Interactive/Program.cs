using IIS.Client.ApiAccess;
using IIS.Client.Interactive.CommandLine;
using System.CommandLine;
using SlaveProgram = IIS.Client.Program;

RuntimeConfig slaveConfig = SlaveProgram.LoadConfig() with { IsSlave = true };

Command rootCommand = SlaveProgram.BuildCommandTree(slaveConfig);

Shell shell = Shell.Create(rootCommand, slaveConfig);
shell.Start();
