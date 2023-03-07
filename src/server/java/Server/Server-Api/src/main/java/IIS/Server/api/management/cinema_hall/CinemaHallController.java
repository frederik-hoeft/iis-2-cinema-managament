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

import IIS.Server.api.BaseController;
import IIS.Server.api.management.cinema_hall.requests.*;
import IIS.Server.api.management.cinema_hall.responses.*;
import IIS.Server.api.management.seat_row.responses.GetSeatRowsResponseEntry;
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
public class CinemaHallController extends BaseController {
    
    @GetMapping("/list")
    public ResponseEntity<GetCinemaHallsResponse> listCinemaHalls() 
    {
        return scheduled(() ->
        {
            GetCinemaHallsResponse response = new GetCinemaHallsResponse();
            response.setCinemaHalls(ObjectX.createFromMany(CinemaService.getInstance().getCinemaHallCache().values(), GetCinemaHallsResponseEntry.class));
            response.setSuccess(true);
            response.setError(Optional.empty());
            return new ResponseEntity<GetCinemaHallsResponse>(response, HttpStatus.OK);
        });
    }

    @GetMapping("/list-full")
    public ResponseEntity<GetCinemaHallsFullResponse> listDetailedCinemaHalls() 
    {
        return scheduled(() -> 
        {
            GetCinemaHallsFullResponse response = new GetCinemaHallsFullResponse();
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
    }

    @PostMapping("/create")
    public ResponseEntity<CreateCinemaHallResponse> createCinemaHall(@RequestBody CreateCinemaHallRequest request) {

        return scheduled(() -> 
        {
            try {
                CinemaHall.createFresh(request.getAvailable(), request.getName());
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
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateCinemaHallResponse> updateCinemaHall(@RequestBody UpdateCinemaHallRequest request) {

        return scheduled(() -> 
        {
            if (!CinemaService.getInstance().getCinemaHallCache().containsKey(request.getId())) {
                UpdateCinemaHallResponse response = new UpdateCinemaHallResponse();
                response.setSuccess(false);
                response.setError(Optional.of("Could not find cinema hall for id " + request.getId()));
                return new ResponseEntity<UpdateCinemaHallResponse>(response, HttpStatus.BAD_REQUEST);
            }

            try {
                CinemaHall cinemaHall = CinemaService.getInstance().getCinemaHall(request.getId());
                cinemaHall.setName(request.getNewName());
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
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteCinemaHallResponse> deleteCinemaHall(@RequestBody DeleteCinemaHallRequest request) {

        return scheduled(() -> 
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
    }
}
