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

    public LogicController(GuiController gc) {
        guiController = gc;
        persistanceController = new PersistanceController(this);

    }

    public void createMovie(String titulo, String genero, String director, PegiEnum pegi, LocalDate fecha, int stock) throws IllegalArgumentException {
        var moviesService = new MoviesService(persistanceController);
        moviesService.createMovie(titulo, genero, director, pegi, fecha, stock);
    }
    public List<Movie> retrieveAllMovies(){
        var movies=persistanceController.getAllMovies();

        return null;
    }



    public int getMaxMovies() {
        return MAX_MOVIES;
    }

    // public Movie getMovieById(int id) throws IllegalArgumentException {
    //     if (!validateId(id)) {
    //        throw new IllegalArgumentException("Id invalido");
    //     }
    //Movie movie = persistanceController.getMovieById(id);
    //     return null;
}


