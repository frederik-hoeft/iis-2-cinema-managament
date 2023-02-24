module generated.cinema {
    requires src;
    
    requires java.sql;

    exports generated.cinema;
    exports generated.cinema.commands;
    exports generated.cinema.commands.booking;
    exports generated.cinema.commands.bookingState;
    exports generated.cinema.commands.cinemaHall;
    exports generated.cinema.commands.customer;
    exports generated.cinema.commands.movie;
    exports generated.cinema.commands.movieScreening;
    exports generated.cinema.commands.reservation;
    exports generated.cinema.commands.seat;
    exports generated.cinema.commands.seatRow;
    exports generated.cinema.proxies;
    exports generated.cinema.relationControl;
}