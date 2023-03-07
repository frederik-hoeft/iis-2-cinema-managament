package IIS.Server.api.management.seat_row;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import IIS.Server.api.management.seat.responses.GetSeatsResponseEntry;
import IIS.Server.api.management.seat_row.requests.CreateSeatRowRequest;
import IIS.Server.api.management.seat_row.requests.DeleteSeatRowRequest;
import IIS.Server.api.management.seat_row.requests.GetSeatRowRequest;
import IIS.Server.api.management.seat_row.requests.UpdateSeatRowRequest;
import IIS.Server.api.management.seat_row.responses.CreateSeatRowResponse;
import IIS.Server.api.management.seat_row.responses.DeleteSeatRowResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsFullResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsFullResponseEntry;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponse;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.api.user.account.responses.UpdateSeatRowResponse;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import exceptions.ConstraintViolation;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.PriceCategoryBox;
import generated.cinemaService.SeatRow;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/seat-row", produces="application/json")
@CrossOrigin(origins="*")
public class SeatRowController {
    
    @PostMapping("/list")
    public ResponseEntity<GetSeatRowsResponse> listSeatRows(@RequestBody GetSeatRowRequest request) {
        // TODO: validate this method works. i cant test it because response body is empty always :) 
        AsyncWorkload<ResponseEntity<GetSeatRowsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                GetSeatRowsResponse response = new GetSeatRowsResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getCinemaHallId()));
                return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.BAD_REQUEST);
            }

            CinemaHall hall = CinemaService.getInstance().getCinemaHall(request.getCinemaHallId());
            GetSeatRowsResponse response = new GetSeatRowsResponse();
            List<GetSeatRowsResponseEntry> seatRows = new ArrayList<GetSeatRowsResponseEntry>();
            for (SeatRow seatRow : hall.getRows()) {
                GetSeatRowsResponseEntry entry = ObjectX.createFrom(seatRow, GetSeatRowsResponseEntry.class);
                entry.setSeatCount(seatRow.getSeats().size());
                seatRows.add(entry);
            }
            response.setRows(seatRows);
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetSeatRowsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/list-full")
    public ResponseEntity<GetSeatRowsFullResponse> listDetailedSeatRows(@RequestBody GetSeatRowRequest request) {
        // TODO: validate this method works. i cant test it because response body is empty always :) 
        AsyncWorkload<ResponseEntity<GetSeatRowsFullResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                GetSeatRowsFullResponse response = new GetSeatRowsFullResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getCinemaHallId()));
                return new ResponseEntity<GetSeatRowsFullResponse>(response, HttpStatus.BAD_REQUEST);
            }

            CinemaHall hall = CinemaService.getInstance().getCinemaHall(request.getCinemaHallId());
            GetSeatRowsFullResponse response = new GetSeatRowsFullResponse();
            List<GetSeatRowsFullResponseEntry> seatRows = new ArrayList<GetSeatRowsFullResponseEntry>();
            for (SeatRow seatRow : hall.getRows()) {
                GetSeatRowsFullResponseEntry entry = ObjectX.createFrom(seatRow, GetSeatRowsFullResponseEntry.class);
                entry.setCinemaHallName(hall.getName());
                entry.setSeats(ObjectX.createFromMany(seatRow.getSeats(), GetSeatsResponseEntry.class));
                seatRows.add(entry);
            }
            response.setRows(seatRows);
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetSeatRowsFullResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetSeatRowsFullResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateSeatRowResponse> createSeatRow(@RequestBody CreateSeatRowRequest request) {

        AsyncWorkload<ResponseEntity<CreateSeatRowResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                CreateSeatRowResponse response = new CreateSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getCinemaHallId()));
                return new ResponseEntity<CreateSeatRowResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                CinemaHall hall = CinemaService.getInstance().getCinemaHall(request.getCinemaHallId());
                SeatRow.createFresh(PriceCategoryBox.getInstance(), request.getName(), hall);
            }
            catch (PersistenceException e) {
                CreateSeatRowResponse response = new CreateSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<CreateSeatRowResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateSeatRowResponse response = new CreateSeatRowResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateSeatRowResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<CreateSeatRowResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatRowResponse> updateSeatRow(@RequestBody UpdateSeatRowRequest request) {

        AsyncWorkload<ResponseEntity<UpdateSeatRowResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getSeatRowCache().containsKey(request.getId())) {
                UpdateSeatRowResponse response = new UpdateSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find seat row for id " + request.getId()));
                return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                SeatRow seatRow = CinemaService.getInstance().getSeatRow(request.getId());
                seatRow.setName(request.getName());
                seatRow.setPrice(request.getPriceCategory());
            }
            catch (PersistenceException e) {
                UpdateSeatRowResponse response = new UpdateSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateSeatRowResponse response = new UpdateSeatRowResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<UpdateSeatRowResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatRowResponse> deleteSeatRow(@RequestBody DeleteSeatRowRequest request) {

        AsyncWorkload<ResponseEntity<DeleteSeatRowResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getSeatRowCache().containsKey(request.getId())) {
                DeleteSeatRowResponse response = new DeleteSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find seat row for id " + request.getId()));
                return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                SeatRow.delete(request.getId());
            }
            catch (ConstraintViolation e) {
                DeleteSeatRowResponse response = new DeleteSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (SQLException e) {
                DeleteSeatRowResponse response = new DeleteSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (NoConnectionException e) {
                DeleteSeatRowResponse response = new DeleteSeatRowResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            DeleteSeatRowResponse response = new DeleteSeatRowResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<DeleteSeatRowResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

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
