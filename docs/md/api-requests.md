# Requests

```csharp
// ============================================================
//                      /admin/revenue
// ============================================================

/// <summary>
/// POST /admin/revenue/list-movies
/// </summary>
internal record GetMovieRevenueRequest([Required][Range(1, int.MaxValue)] int MovieId) : IJustFixGitHubSyntaxHighlightingLol;

/// <summary>
/// POST /admin/revenue/list-screenings
/// </summary>
internal record GetScreeningRevenueRequest([Required][Range(1, int.MaxValue)] int ScreeningId);

// ============================================================
//                      /management/cinema-hall
// ============================================================

/// <summary>
/// POST /management/cinema-hall/create
/// </summary>
internal record CreateCinemaHallRequest([Required] string Name, [Required] bool IsAvailable);

/// <summary>
/// POST /management/cinema-hall/delete
/// </summary>
internal record DeleteCinemaHallRequest([Required][Range(1, int.MaxValue)] int Id);

/// <summary>
/// POST /management/cinema-hall/update
/// </summary>
internal record UpdateCinemaHallRequest([Required] [Range(1, int.MaxValue)] int Id, [Required] string NewName, [Required] bool IsAvailable);

// ============================================================
//                      /management/movie
// ============================================================

/// <summary>
/// POST /management/movie/create
/// </summary>
internal record CreateMovieRequest([Required] string Title, [Required] string Description);

/// <summary>
/// POST /management/movie/delete
/// </summary>
internal record DeleteMovieRequest([Required][Range(1, int.MaxValue)] int Id);

/// <summary>
/// POST /management/movie/update
/// </summary>
internal record UpdateMovieRequest([Required][Range(1, int.MaxValue)] int Id, [Required] string Title, [Required] string Description);

// ============================================================
//                      /management/movie-screening
// ============================================================

/// <summary>
/// POST /management/movie-screening/create
/// </summary>
internal record CreateMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int MovieId, 
    [Required][Range(1, int.MaxValue)] int CinemaHallId, 
    [Required] string Name,
    [Required] bool HasExpired
);

/// <summary>
/// POST /management/movie-screening/delete
/// </summary>
internal record DeleteMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int Id
);

/// <summary>
/// POST /management/movie-screening/update
/// </summary>
internal record UpdateMovieScreeningRequest
(
    [Required][Range(1, int.MaxValue)] int Id,
    [Required] string NewName,
    [Required] bool NewHasExpired
);

/// <summary>
/// POST /user/booking/available-screenings
/// </summary>
internal record GetMovieScreeningsRequest
(
    [Required][Range(1, int.MaxValue)] int MovieId
);

// ============================================================
//                      /management/seat
// ============================================================

/// <summary>
/// POST /management/seat/create
/// </summary>
internal record CreateSeatRequest
(
    [Required][Range(1, int.MaxValue)] int RowId,
    [Required] string Name
);

/// <summary>
/// POST /management/seat/delete
/// </summary>
internal record DeleteSeatRequest
(
    [Required][Range(1, int.MaxValue)] int Id
);

/// <summary>
/// POST /management/seat/update
/// </summary>
internal record UpdateSeatRequest
(
    [Required][Range(1, int.MaxValue)] int Id, 
    [Required] string Name
);

/// <summary>
/// POST /management/seat/list
/// </summary>
internal record GetSeatsRequest
(
    [Required][Range(1, int.MaxValue)] int RowId
);

/// <summary>
/// POST /user/booking/available-seats
/// </summary>
internal record GetAvailableSeatsRequest
(
    [Required][Range(1, int.MaxValue)] int RowId,
    [Required][Range(1, int.MaxValue)] int ScreeningId
);

// ============================================================
//                      /management/seat-row
// ============================================================

/// <summary>
/// POST /management/seat-row/create
/// </summary>
internal record CreateSeatRowRequest
(
    [Required][Range(1, int.MaxValue)] int CinemaHallId,
    [Required]string Name,
    [Required][Range(1, 3)] PriceCategory PriceCategory
);

/// <summary>
/// POST /management/seat-row/delete
/// </summary>
internal record DeleteSeatRowRequest([Required][Range(1, int.MaxValue)] int Id);

/// <summary>
/// POST /management/seat-row/update
/// </summary>
internal record UpdateSeatRowRequest
(
    [Required][Range(1, int.MaxValue)] int Id,
    [Required] string Name,
    [Required][Range(1, 3)] PriceCategory PriceCategory
);

/// <summary>
/// POST /management/seat-row/list
/// POST /management/seat/available-rows
/// POST /user/booking/available-seat-rows
/// </summary>
internal record GetSeatRowRequest([Required][Range(1, int.MaxValue)] int CinemaHallId);

// ============================================================
//                      /user/account
// ============================================================

/// <summary>
/// POST /user/account/create
/// </summary>
internal record UserCreateAccountRequest
(
    [Required][EmailAddress] string Email,
    [Required] string FirstName,
    [Required] string LastName
);

/// <summary>
/// POST /user/account/delete
/// </summary>
internal record UserDeleteAccountRequest
(
    [Required][EmailAddress] string Email
);

/// <summary>
/// POST /user/account/get
/// </summary>
internal record GetUserAccountRequest
(
    [Required][EmailAddress] string Email
);

/// <summary>
/// POST /user/account/update
/// </summary>
internal record UserUpdateAccountRequest
(
    [Required][EmailAddress] string Email,
    [Required] string FirstName,
    [Required] string LastName
);

// ============================================================
//                      /user/booking
// ============================================================

/// <summary>
/// POST /user/booking/book
/// </summary>
internal record UserBookingRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ScreeningId, 
    [Required][Range(1, int.MaxValue)] int SeatId
);

/// <summary>
/// POST /user/booking/cancel
/// </summary>
internal record UserCancelReservationRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ReservationId
);

/// <summary>
/// POST /user/booking/reserve
/// </summary>
internal record UserReservationRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ScreeningId, 
    [Required][Range(1, int.MaxValue)] int SeatId
);

/// <summary>
/// POST /user/booking/upgrade
/// </summary>
internal record UserUpgradeReservationRequest
(
    [Required][EmailAddress] string Email, 
    [Required][Range(1, int.MaxValue)] int ReservationId
);

/// <summary>
/// POST /user/booking/get-bookings
/// </summary>
internal record GetUserBookingsRequest([Required][EmailAddress] string Email);

/// <summary>
/// POST /user/booking/get-reservation
/// </summary>
internal record GetUserReservationsRequest([Required][EmailAddress] string Email);

```
