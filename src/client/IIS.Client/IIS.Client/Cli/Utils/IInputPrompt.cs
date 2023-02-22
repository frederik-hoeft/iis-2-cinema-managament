namespace IIS.Client.Cli.Utils;

public interface IInputPrompt<out T>
{
    T? RequestInput();
}
