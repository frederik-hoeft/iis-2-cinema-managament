package IIS.Server.api.management.cinema_hall;

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

import IIS.Server.api.management.cinema_hall.requests.*;
import IIS.Server.api.management.cinema_hall.responses.*;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
import IIS.Server.management.AsyncWorkload;
import IIS.Server.management.GenericAsyncResult;
import IIS.Server.management.PersistencyService;
import IIS.Server.utils.ObjectX;
import exceptions.ConstraintViolation;
import generated.cinemaService.CinemaHall;
import generated.cinemaService.CinemaService;
import generated.cinemaService.proxies.CinemaHallProxy;
import src.db.connection.NoConnectionException;
import src.db.executer.PersistenceException;

@RestController
@RequestMapping(path="/management/cinema-hall", produces="application/json")
@CrossOrigin(origins="*")
public class CinemaHallController {
    
    @GetMapping("/list")
    public ResponseEntity<GetCinemaHallsResponse> listCinemaHalls() {
        
        AsyncWorkload<ResponseEntity<GetCinemaHallsResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values(), GetCinemaHallsResponseEntry.class));
            response.setSuccess(true);
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetCinemaHallsResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetCinemaHallsFullResponse> listDetailedCinemaHalls() {

        AsyncWorkload<ResponseEntity<GetCinemaHallsFullResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            GetCinemaHallsFullResponse response = new GetCinemaHallsFullResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values(), GetCinemaHallsFullResponseEntry.class));
            List<GetCinemaHallsFullResponseEntry> cinemaHalls = new ArrayList<GetCinemaHallsFullResponseEntry>();
            for (CinemaHallProxy ch : CinemaService.getInstance().getCinemaHallCache().values()) {
                GetCinemaHallsFullResponseEntry entry = ObjectX.createFrom(ch, GetCinemaHallsFullResponseEntry.class);
                entry.setRows(ObjectX.createFromMany(ch.getRows(), GetSeatRowsResponseEntry.class));
                cinemaHalls.add(entry);
            }
            response.setCinemaHalls(cinemaHalls);
            response.setSuccess(true);
            return new ResponseEntity<GetCinemaHallsFullResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<GetCinemaHallsFullResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateCinemaHallResponse> createCinemaHall(@RequestBody CreateCinemaHallRequest request) {

        AsyncWorkload<ResponseEntity<CreateCinemaHallResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            try {
                // TODO: name field is missing
                CinemaHall.createFresh(request.getAvailable());
            }
            catch (PersistenceException e) {
                CreateCinemaHallResponse response = new CreateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<CreateCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            CreateCinemaHallResponse response = new CreateCinemaHallResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<CreateCinemaHallResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<CreateCinemaHallResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateCinemaHallResponse> updateCinemaHall(@RequestBody UpdateCinemaHallRequest request) {

        AsyncWorkload<ResponseEntity<UpdateCinemaHallResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getId())) {
                UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getId()));
                return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                CinemaHall cinemaHall = CinemaService.getInstance().getCinemaHall(request.getId());
                // TODO: name field is missing 
                cinemaHall.setAvailable(request.getAvailable());
            }
            catch (PersistenceException e) {
                UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<UpdateCinemaHallResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteCinemaHallResponse> deleteCinemaHall(@RequestBody DeleteCinemaHallRequest request) {

        AsyncWorkload<ResponseEntity<DeleteCinemaHallResponse>> workload = PersistencyService.getInstance().schedule(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getId())) {
                DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getId()));
                return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                CinemaHall.delete(request.getId());
            }
            catch (ConstraintViolation e) {
                DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (SQLException e) {
                DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (NoConnectionException e) {
                DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of(e.getMessage()));
                return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            DeleteCinemaHallResponse response = new DeleteCinemaHallResponse();
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<DeleteCinemaHallResponse>(response, HttpStatus.OK);
        });
        GenericAsyncResult<ResponseEntity<DeleteCinemaHallResponse>> result = workload.getResultAsync().join();
        return result.getValue();
    }
}
