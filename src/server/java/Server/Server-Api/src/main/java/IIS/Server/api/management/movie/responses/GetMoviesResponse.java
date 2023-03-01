package IIS.Server.api.management.movie.responses;

import java.util.Collection;

import lombok.Data;

@Data
public class GetMoviesResponse {
    boolean success;
    Collection<GetMoviesResponseEntry> movies;
}