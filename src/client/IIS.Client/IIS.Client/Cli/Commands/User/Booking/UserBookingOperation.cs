using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IIS.Client.Cli.Commands.User.Booking;

public enum UserBookingOperation
{
    New,
    Reserve,
    Upgrade,
    Cancel,
    Show
}
