package IIS.Server.api.management.seat_row;

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

import IIS.Server.api.management.seat_row.requests.*;
import IIS.Server.api.management.seat_row.responses.*;
import IIS.Server.api.user.account.responses.UpdateSeatRowResponse;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import generated.cinemaService.CinemaService;
import generated.cinemaService.SeatRow;

@RestController
@RequestMapping(path="/management/seat-row", produces="application/json")
@CrossOrigin(origins="*")
public class SeatRowController {
    
    @PostMapping("/list")
    public ResponseEntity<GetSeatRowsResponse> listSeatRows(@RequestBody GetSeatRowRequest request) {

        AsyncWorkload<ResponseEntity<GetSeatRowsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                GetSeatRowsResponse response = new GetSeatRowsResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getCinemaHallId()));
                return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.BAD_REQUEST);
            }

            GetSeatRowsResponse response = new GetSeatRowsResponse();
            response.setRows(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values().stream().map(ch -> ch.getRows()).toList(), GetSeatRowsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetSeatRowsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/list-full")
    public ResponseEntity<GetSeatRowsFullResponse> listDetailedSeatRows(@RequestBody GetSeatRowRequest request) {

        AsyncWorkload<ResponseEntity<GetSeatRowsFullResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getCinemaHallId())) {
                GetSeatRowsFullResponse response = new GetSeatRowsFullResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getCinemaHallId()));
                return new ResponseEntity<GetSeatRowsFullResponse>(response, HttpStatus.BAD_REQUEST);
            }

            GetSeatRowsFullResponse response = new GetSeatRowsFullResponse();
            List<GetSeatRowsFullResponseEntry> seatRows = new ArrayList<GetSeatRowsFullResponseEntry>();
            for (List<SeatRow> sr : CinemaService.getInstance().getCinemaHallCache().values().stream().map(ch -> ch.getRows()).toList()) {
                // TODO: fix
                GetSeatRowsFullResponseEntry entry = ObjectX.createFrom(sr, GetSeatRowsFullResponseEntry.class);
                entry.setSeats(null);
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

        CreateSeatRowResponse response = new CreateSeatRowResponse();
        response.setSuccess(false);
        return new ResponseEntity<CreateSeatRowResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateSeatRowResponse> updateSeatRow(@RequestBody UpdateSeatRowRequest request) {

        UpdateSeatRowResponse response = new UpdateSeatRowResponse();
        response.setSuccess(false);
        return new ResponseEntity<UpdateSeatRowResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteSeatRowResponse> deleteSeatRow(@RequestBody DeleteSeatRowRequest request) {

        DeleteSeatRowResponse response = new DeleteSeatRowResponse();
        response.setSuccess(false);
        return new ResponseEntity<DeleteSeatRowResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/available-halls")
    public ResponseEntity<GetSeatRowsResponse> getAvailableHalls() {

        GetSeatRowsResponse response = new GetSeatRowsResponse();
        response.setSuccess(false);
        return new ResponseEntity<GetSeatRowsResponse>(response, HttpStatus.OK);
    }
}
