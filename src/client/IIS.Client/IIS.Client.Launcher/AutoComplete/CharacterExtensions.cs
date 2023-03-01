using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.Interactive.AutoComplete;

internal static class CharacterExtensions
{
    public static bool IsPrintable(this char c) =>
        char.IsAscii(c) && !char.IsControl(c);
}
