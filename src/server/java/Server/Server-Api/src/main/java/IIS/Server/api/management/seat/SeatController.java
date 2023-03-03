package IIS.Server.api.management.seat;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponse;
import IIS.Server.api.management.cinema_hall.responses.GetCinemaHallsResponseEntry;
import IIS.Server.api.management.seat.requests.*;
import IIS.Server.api.management.seat.responses.*;
import IIS.Server.api.management.seat_row.requests.GetSeatRowRequest;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import exceptions.ConstraintViolation;
import generated.cinemaService.CinemaService;
import generated.cinemaService.Movie;
import generated.cinemaService.Seat;
import generated.cinemaService.SeatRow;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/seat", produces="application/json")
@CrossOrigin(origins="*")
public class SeatController {
    
    @PostMapping("/list")
    public ResponseEntity<GetSeatsResponse> listSeats(@RequestBody GetSeatsRequest request) {

        AsyncWorkload<ResponseEntity<GetSeatsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getSeatRowCache().containsKey(request.getRowId())) {
                GetSeatsResponse response = new GetSeatsResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find row for id " + request.getRowId()));
                return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.BAD_REQUEST);
            }

            GetSeatsResponse response = new GetSeatsResponse();
            response.setSeats(ObjectX.createFromMany(CinemaService.getInstance().getSeatRow(request.getRowId()).getSeats(), GetSeatsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetSeatsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetSeatsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSeatResponse> createSeat(@RequestBody CreateSeatRequest request) {

        AsyncWorkload<ResponseEntity<CreateSeatResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getSeatRowCache().containsKey(request.getRowId())) {
                CreateSeatResponse response = new CreateSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find row for id " + request.getRowId()));
                return new ResponseEntity<CreateSeatResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                SeatRow row = CinemaService.getInstance().getSeatRow(request.getRowId());
                Seat.createFresh(row);
            }
            catch (PersistenceException e) {
                CreateSeatResponse response = new CreateSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<CreateSeatResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateSeatResponse response = new CreateSeatResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateSeatResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<CreateSeatResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatResponse> updateSeat(@RequestBody UpdateSeatRequest request) {

        AsyncWorkload<ResponseEntity<UpdateSeatResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getSeatCache().containsKey(request.getId())) {
                UpdateSeatResponse response = new UpdateSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find seat for id " + request.getId()));
                return new ResponseEntity<UpdateSeatResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                Seat seat = CinemaService.getInstance().getSeat(request.getId());
                // TODO: set name here
            }
            catch (PersistenceException e) {
                UpdateSeatResponse response = new UpdateSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateSeatResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateSeatResponse response = new UpdateSeatResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateSeatResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<UpdateSeatResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatResponse> deleteSeat(@RequestBody DeleteSeatRequest request) {

        AsyncWorkload<ResponseEntity<DeleteSeatResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getSeatCache().containsKey(request.getId())) {
                DeleteSeatResponse response = new DeleteSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find seat for id " + request.getId()));
                return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                Seat.delete(request.getId());
            }
            catch (ConstraintViolation e) {
                DeleteSeatResponse response = new DeleteSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (SQLException e) {
                DeleteSeatResponse response = new DeleteSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (NoConnectionException e) {
                DeleteSeatResponse response = new DeleteSeatResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            DeleteSeatResponse response = new DeleteSeatResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteSeatResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<DeleteSeatResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    /**
     * Get all seat rows of a given cinema hall.
     * @param request
     * @return
     */
    @PostMapping("/available-rows")
    public ResponseEntity<GetSeatRowsResponse> getAvailableRows(@RequestBody GetSeatRowRequest request) {

        AsyncWorkload<ResponseEntity<GetSeatRowsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                GetSeatRowsResponse response = new GetSeatRowsResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getCinemaHallId()));
                return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.BAD_REQUEST);
            }

            GetSeatRowsResponse response = new GetSeatRowsResponse();
            response.setRows(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHall(request.getCinemaHallId()).getRows(), GetSeatRowsResponseEntry.class));
            response.setSuccess(false);
            response.setError(Optional.empty());
            return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetSeatRowsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    /**
     * Get all cinema halls which are available.
     * @return
     */
    @GetMapping("/available-halls")
    public ResponseEntity<GetCinemaHallsResponse> getAvailableHalls() {

        AsyncWorkload<ResponseEntity<GetCinemaHallsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values().stream().filter(c -> c.getAvailable()).toList(), GetCinemaHallsResponseEntry.class));
            response.setSuccess(false);
            response.setError(Optional.empty());
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetCinemaHallsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }
}
