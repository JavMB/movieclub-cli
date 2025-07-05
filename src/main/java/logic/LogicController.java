package logic;

import gui.GuiController;
import logic.services.MoviesService;
import persistance.PersistanceController;
import persistance.entity.Movie;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;
import java.util.List;

public class LogicController {
    private final static int MAX_MOVIES = 50;
    private final GuiController guiController;
    private final PersistanceController persistanceController;
    private String statusMessage = "";

    public LogicController(GuiController gc) {
        guiController = gc;
        persistanceController = new PersistanceController();
    }

    public boolean createMovie(String titulo, String genero, String director, PegiEnum pegi, LocalDate fecha, int stock) {
        try {
            var moviesService = new MoviesService(persistanceController);
            moviesService.createMovie(titulo, genero, director, pegi, fecha, stock);
            statusMessage = "Película creada exitosamente!";
            return true;
        } catch (IllegalArgumentException e) {
            statusMessage = e.getMessage();
            return false;
        } catch (Exception e) {
            statusMessage = "Error inesperado: " + e.getMessage();
            return false;
        }
    }

    public List<Movie> retrieveAllMovies() {
        var moviesService = new MoviesService(persistanceController);
        return moviesService.retrieveAllMovies();
    }

    public Movie getMovieById(int id) {
        try {
            var moviesService = new MoviesService(persistanceController);
            Movie movie = moviesService.getMovieById(id);
            if (movie == null) {
                statusMessage = "No existe una película con ese ID.";
            }
            return movie;
        } catch (IllegalArgumentException e) {
            statusMessage = e.getMessage();
            return null;
        } catch (Exception e) {
            statusMessage = "Error inesperado: " + e.getMessage();
            return null;
        }
    }

    public boolean updateMovie(int id, String titulo, String genero, String director, PegiEnum pegi, LocalDate fecha, Integer stock) {
        try {
            var moviesService = new MoviesService(persistanceController);
            moviesService.updateMovie(id, titulo, genero, director, pegi, fecha, stock);
            statusMessage = "Película actualizada exitosamente!";
            return true;
        } catch (IllegalArgumentException e) {
            statusMessage = e.getMessage();
            return false;
        } catch (Exception e) {
            statusMessage = "Error inesperado: " + e.getMessage();
            return false;
        }
    }

    public Movie deleteMovie(int id) {
        try {
            var moviesService = new MoviesService(persistanceController);
            Movie deletedMovie = moviesService.deleteMovie(id);
            statusMessage = "Pelicula eliminada exitosamente!";
            return deletedMovie;
        } catch (IllegalArgumentException e) {
            statusMessage = e.getMessage();
            return null;
        } catch (Exception e) {
            statusMessage = "Error inesperado: " + e.getMessage();
            return null;
        }
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public int getMaxMovies() {
        return MAX_MOVIES;
    }
}
