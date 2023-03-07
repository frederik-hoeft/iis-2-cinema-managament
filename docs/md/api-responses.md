# Responses

```csharp
// ============================================================
//                      /admin/revenue
// ============================================================

/// <summary>
/// POST /admin/revenue/list-movies
/// </summary>
internal record GetMovieRevenueResponse(bool Success, string? Error, decimal TotalRevenue) : IApiResponse;

/// <summary>
/// POST /admin/revenue/list-screenings
/// </summary>
internal record GetScreeningRevenueResponse(bool Success, string? Error, decimal TotalRevenue) : IApiResponse;

// ============================================================
//                      /management/cinema-hall
// ============================================================

/// <summary>
/// POST /management/cinema-hall/create
/// </summary>
internal record CreateCinemaHallResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/cinema-hall/delete
/// </summary>
internal record DeleteCinemaHallResponse(bool Success, string? Error) : IApiResponse;

internal record GetCinemaHallsResponseEntry(int Id, string Name, bool IsAvailable);

/// <summary>
/// GET /management/cinema-hall/list
/// GET /management/movie-screening/available-halls
/// GET /management/seat/available-halls
/// </summary>
internal record GetCinemaHallsResponse(bool Success, string? Error, GetCinemaHallsResponseEntry[] CinemaHalls) : IApiResponse;

internal record GetCinemaHallsFullResponseEntry(int Id, string Name, bool IsAvailable, GetSeatRowsResponseEntry[] Rows);

/// <summary>
/// GET /management/cinema-hall/list-full
/// </summary>
internal record GetCinemaHallsFullResponse(bool Success, string? Error, GetCinemaHallsFullResponseEntry[] CinemaHalls) : IApiResponse;

/// <summary>
/// POST /management/cinema-hall/update
/// </summary>
internal record UpdateCinemaHallResponse(bool Success, string? Error) : IApiResponse;

// ============================================================
//                      /management/movie
// ============================================================

/// <summary>
/// POST /management/movie/create
/// </summary>
internal record CreateMovieResponse(bool Success, string? Error): IApiResponse;

/// <summary>
/// POST /management/movie/delete
/// </summary>
internal record DeleteMovieResponse(bool Success, string? Error) : IApiResponse;

internal record GetMoviesResponseEntry(int Id, string Title, string Description);

/// <summary>
/// GET /management/movie/list
/// GET /management/movie-screening/available-movies
/// GET /user/booking/available-movies
/// </summary>
internal record GetMoviesResponse(bool Success, string? Error, GetMoviesResponseEntry[] Movies) : IApiResponse;

internal record GetMoviesFullResponseEntry(int Id, string Title, string Description, int ScreeningCount);

/// <summary>
/// GET /management/movie/list-full
/// </summary>
internal record GetMoviesFullResponse(bool Success, string? Error, GetMoviesFullResponseEntry[] Movies) : IApiResponse;

/// <summary>
/// POST /management/movie/update
/// </summary>
internal record UpdateMovieResponse(bool Success, string? Error) : IApiResponse;

// ============================================================
//                      /management/movie-screening
// ============================================================

/// <summary>
/// POST /management/movie-screening/create
/// </summary>
internal record CreateMovieScreeningResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/movie-screening/delete
/// </summary>
internal record DeleteMovieScreeningResponse(bool Success, string? Error) : IApiResponse;

internal record GetMovieScreeningsResponseEntry(int Id, string Name, string MovieTitle, bool HasExpired);

/// <summary>
/// GET /management/movie-screening/list
/// </summary>
internal record GetMovieScreeningsResponse(bool Success, string? Error, GetMovieScreeningsResponseEntry[] Screenings) : IApiResponse;

internal record GetMovieScreeningsFullResponseEntry(int Id, string Name, GetMoviesResponseEntry Movie, GetCinemaHallsResponseEntry CinemaHall, bool HasExpired);

/// <summary>
/// GET /management/movie-screening/list-full
/// POST /user/booking/available-screenings
/// </summary>
internal record GetMovieScreeningsFullResponse(bool Success, string? Error, GetMovieScreeningsFullResponseEntry[] Screenings) : IApiResponse;

/// <summary>
/// POST /management/movie-screening/update
/// </summary>
internal record UpdateMovieScreeningResponse(bool Success, string? Error) : IApiResponse;

// ============================================================
//                      /management/seat
// ============================================================

/// <summary>
/// POST /management/seat/create
/// </summary>
internal record CreateSeatResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/seat/delete
/// </summary>
internal record DeleteSeatResponse(bool Success, string? Error) : IApiResponse;

internal record GetSeatsResponseEntry(int Id, string Name);

internal record GetSeatsFullResponseEntry(int Id, string Name, GetSeatRowsResponseEntry Row);

/// <summary>
/// POST /management/seat/list
/// POST /user/booking/available-seats
/// </summary>
internal record GetSeatsResponse(bool Success, string? Error, GetSeatsResponseEntry[] Seats) : IApiResponse;

/// <summary>
/// POST /management/seat/update
/// </summary>
internal record UpdateSeatResponse(bool Success, string? Error) : IApiResponse;

// ============================================================
//                      /management/seat-row
// ============================================================

/// <summary>
/// POST /management/seat-row/create
/// </summary>
internal record CreateSeatRowResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /management/seat-row/delete
/// </summary>
internal record DeleteSeatRowResponse(bool Success, string? Error) : IApiResponse;

internal record GetSeatRowsResponseEntry(int Id, string Name, int SeatCount, PriceCategory PriceCategory);

/// <summary>
/// POST /management/seat-row/list
/// POST /management/seat/available-rows
/// </summary>
internal record GetSeatRowsResponse(bool Success, string? Error, GetSeatRowsResponseEntry[] Rows) : IApiResponse;

internal record GetSeatRowsFullResponseEntry(int Id, string Name, string CinemaHallName, PriceCategory PriceCategory, GetSeatsResponseEntry[] Seats);

/// <summary>
/// GET /management/seat-row/list-full
/// </summary>
internal record GetSeatRowsFullResponse(bool Success, string? Error, GetSeatRowsFullResponseEntry[] Rows) : IApiResponse;

/// <summary>
/// POST /management/seat-row/create
/// </summary>
internal record UpdateSeatRowResponse(bool Success, string? Error) : IApiResponse;

// ============================================================
//                      /user/account
// ============================================================

/// <summary>
/// POST /user/account/create
/// </summary>
internal record UserCreateAccountResponse(bool Success, string? Error) : IApiResponse;

internal record GetUserAccountsResponseEntry(int Id, string FirstName, string LastName, string Email);

/// <summary>
/// GET /user/account/list
/// </summary>
internal record GetUserAccountsResponse(bool Success, string? Error, GetUserAccountsResponseEntry[] Accounts) : IApiResponse;

/// <summary>
/// POST /user/account/get
/// </summary>
internal record GetUserAccountResponse(bool Success, string? Error, GetUserAccountsResponseEntry Account) : IApiResponse;

/// <summary>
/// POST /user/account/delete
/// </summary>
internal record UserDeleteAccountResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /user/account/update
/// </summary>
internal record UserUpdateAccountResponse(bool Success, string? Error) : IApiResponse;

// ============================================================
//                      /user/booking
// ============================================================

/// <summary>
/// POST /user/booking/book
/// </summary>
internal record UserBookingResponse(bool Success, string? Error) : IApiResponse;

internal record UserCancelReservationResponse(bool Success, string? Error) : IApiResponse;

/// <summary>
/// POST /user/booking/reserve
/// </summary>
internal record UserReservationResponse(bool Success, string? Error): IApiResponse;

/// <summary>
/// POST /user/booking/upgrade
/// </summary>
internal record UserUpgradeReservationResponse(bool Success, string? Error): IApiResponse;

internal record GetAvailableSeatRowsResponseEntry(int RowId, string Name, int AvailableSeatCount, PriceCategory PriceCategory);

/// <summary>
/// POST /user/booking/available-seat-rows
/// </summary>
internal record GetAvailableSeatRowsResponse(bool Success, string? Error, GetAvailableSeatRowsResponseEntry[] Rows) : IApiResponse;

internal record GetUserBookingsResponseEntry(int Id, GetMovieScreeningsFullResponseEntry MovieScreening, GetSeatsFullResponseEntry Seat, GetUserAccountsResponseEntry User);

/// <summary>
/// GET /user/booking/list-bookings
/// POST /user/booking/get-bookings
/// </summary>
internal record GetUserBookingsResponse(bool Success, string? Error, GetUserBookingsResponseEntry[] Bookings) : IApiResponse;

internal record GetUserReservationsResponseEntry(int Id, GetMovieScreeningsFullResponseEntry MovieScreening, GetSeatsFullResponseEntry Seat, GetUserAccountsResponseEntry User);

/// <summary>
/// GET /user/booking/list-reservations
/// POST /user/booking/get-reservations
/// </summary>
internal record GetUserReservationsResponse(bool Success, string? Error, GetUserReservationsResponseEntry[] Reservations) : IApiResponse;
```
