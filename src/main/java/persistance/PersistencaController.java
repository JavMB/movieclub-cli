package persistance;


import persistance.entity.Movie;

import java.util.HashMap;
import java.util.Map;

public class PersistencaController {
    Map<Integer, Movie> movies;
    private int movieCount ;

    public PersistencaController() {
        this.movies = new HashMap<>();
        this.movieCount = 0;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void saveMovie(Movie movie) {
        movies.put(movie.getId(), movie);
        movieCount++;
    }


}
