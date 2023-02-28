namespace IIS.Client.Cli.Utils;

public static class List
{
    public static List<T> Of<T>(T v1) => new() { v1 };
    public static List<T> Of<T>(T v1, T v2) => new() { v1, v2 };
    public static List<T> Of<T>(T v1, T v2, T v3) => new() { v1, v2, v3 };
    public static List<T> Of<T>(T v1, T v2, T v3, T v4) => new() { v1, v2, v3, v4 };
    public static List<T> Of<T>(T v1, T v2, T v3, T v4, T v5) => new() { v1, v2, v3, v4, v5 };
    public static List<T> Of<T>(T v1, T v2, T v3, T v4, T v5, T v6) => new() { v1, v2, v3, v4, v5, v6 };
    public static List<T> Of<T>(T v1, T v2, T v3, T v4, T v5, T v6, T v7) => new() { v1, v2, v3, v4, v5, v6, v7 };
    public static List<T> Of<T>(T v1, T v2, T v3, T v4, T v5, T v6, T v7, T v8) => new() { v1, v2, v3, v4, v5, v6, v7, v8 };
    public static List<T> Of<T>(params T[] vs) => vs.ToList();
}