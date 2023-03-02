using IIS.Client.Interactive.CommandLine.Parser;
using System.Diagnostics;
using System.Text;
using SlaveProgram = IIS.Client.Program;

namespace IIS.Client.Interactive.CommandLine;

internal class IOForwarder
{
    private readonly Process _slave;
    private static readonly object _consoleLock = new();

    private IOForwarder(Process slave) => _slave = slave;

    public static void ForwardUntilExits(Process slave)
    {
        IOForwarder self = new(slave);
        ThreadPool.QueueUserWorkItem(_ => self.RedirectStdOut());
        ThreadPool.QueueUserWorkItem(_ => self.ForwardStdIn());
        ThreadPool.QueueUserWorkItem(_ => self.RedirectStdErr());
        slave.WaitForExit();
    }

    private void RedirectStdOut()
    {
        Span<char> buffer = stackalloc char[256];
        StreamReader slaveOutputReader = _slave.StandardOutput;
        while (!_slave.HasExited)
        {
            int bytesRead = 0;
            do
            {
                bytesRead = slaveOutputReader.Read(buffer);
                lock (_consoleLock)
                {
                    Stdout.Write(buffer[..bytesRead].ToString());
                }
            } while (bytesRead > 0);
            Thread.Sleep(10);
        }
    }

    private void RedirectStdErr()
    {
        Span<char> buffer = stackalloc char[256];
        StreamReader slaveErrorReader = _slave.StandardError;
        while (!_slave.HasExited)
        {
            int bytesRead = 0;
            do
            {
                bytesRead = slaveErrorReader.Read(buffer);
                lock (_consoleLock)
                {
                    Stdout.Write(buffer[..bytesRead].ToString(), ConsoleColor.Red);
                }
            } while (bytesRead > 0);
            Thread.Sleep(10);
        }
    }

    private void ForwardStdIn()
    {
        StreamWriter slaveInputWriter = _slave.StandardInput;
        LineRenderer lineRenderer = new(SlaveProgram.SLAVE_PROMPT);
        WordBuilder inputBuilder = new();
        while (!_slave.HasExited)
        {
            if (Console.KeyAvailable)
            {
                lineRenderer.RenderLine();
                ConsoleKeyInfo keyInfo = Console.ReadKey();
                if (keyInfo.Key is ConsoleKey.Enter)
                {
                    Stdout.WriteLine();
                    string input = inputBuilder.Flush();
                    slaveInputWriter.WriteLine(input);
                    lineRenderer.Clear();
                    continue;
                }
                else if (keyInfo.Key is ConsoleKey.Backspace)
                {
                    lineRenderer.TryRemoveLastCharacter();
                    inputBuilder.TryRemoveLast();
                }
                else
                {
                    lineRenderer.Append(keyInfo);
                    inputBuilder.Append(keyInfo);
                }
                lineRenderer.RenderLine();
            }
            else
            {
                Thread.Sleep(10);
            }
        }
    }
}
