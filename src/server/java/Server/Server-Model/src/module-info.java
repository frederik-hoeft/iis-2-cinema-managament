module generated.cinemaService {
    requires src;
    
    requires java.sql;

    exports generated.cinemaService;
    exports generated.cinemaService.commands;
    exports generated.cinemaService.commands.booking;
    exports generated.cinemaService.commands.bookingState;
    exports generated.cinemaService.commands.cinemaHall;
    exports generated.cinemaService.commands.customer;
    exports generated.cinemaService.commands.movie;
    exports generated.cinemaService.commands.movieScreening;
    exports generated.cinemaService.commands.reservation;
    exports generated.cinemaService.commands.seat;
    exports generated.cinemaService.commands.seatRow;
    exports generated.cinemaService.proxies;
    exports generated.cinemaService.relationControl;
}