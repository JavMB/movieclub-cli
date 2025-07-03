package persistance;


import logic.LogicController;
import persistance.entity.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersistanceController {
    private LogicController logicController;
    private final Map<Integer, Movie> movies;
    private int movieCount;

    public PersistanceController(LogicController lc) {
        logicController = lc;
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
    public List<Movie> getAllMovies() {
        return movies.values().stream().toList(); // mutabilidad? new?
    }

    public Optional<Movie> getMovieById(int id) {
        return Optional.ofNullable(movies.get(id));
    }

    public LogicController getLogicController() {
        return logicController;
    }


}
