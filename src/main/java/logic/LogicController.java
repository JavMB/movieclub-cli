package logic;


import logic.filters.Filter;
import logic.filters.GenreFilter;
import logic.filters.ProfanityFilter;
import persistance.PersistencaController;
import persistance.entity.Movie;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;

public class LogicController {
    private final static int MAX_MOVIES = 50;
    private PersistencaController persistanceController;

    private Filter<String> profanityFilter = new ProfanityFilter();
    private Filter<String> generoFilter = new GenreFilter();

    public LogicController() {
        persistanceController = new PersistencaController();
    }

    public void createMovie(String titulo, String genero, String director, PegiEnum pegi, LocalDate fecha, int stock) throws IllegalArgumentException {

        if (!validateTitulo(titulo)) {
            throw new IllegalArgumentException("Titulo invalido");
        }
        if (!validateGenero(genero)) {
            throw new IllegalArgumentException("Genero invalido");
        }
        if (!validateDirector(director)) {
            throw new IllegalArgumentException("Director invalido");
        }
        if (!validateFecha(fecha)) {
            throw new IllegalArgumentException("Fecha invalido");
        }
        if (!validatePegi(pegi)) {
            throw new IllegalArgumentException("Pegi invalido");

        }
        if (!validateStock(stock)) {
            throw new IllegalArgumentException("Stock invalido");
        }
        Movie movie = new Movie();

        movie.setId(persistanceController.getMovieCount() + 1); // int newId=generarId();

        persistanceController.saveMovie(movie);

    }


    private boolean validateId(int id) {
        int totalMoviesInDB = persistanceController.getMovieCount();
        boolean condicionMovieIdMenorQueTotalInDB = id <= totalMoviesInDB;
        boolean condicionMovieIdNegativeOrZero = id <= 0;

        //TODO Habria que validar que el id que se va meter no exista ya dentro de la BD

        return condicionMovieIdNegativeOrZero && !condicionMovieIdMenorQueTotalInDB;

    }

    private boolean validateTitulo(String titulo) {
        //Validar que no haya palabras malsonantes, formato = minusculas

        if (!titulo.equals(titulo.toLowerCase()) || !profanityFilter.isValid(titulo)) {
            return false;
        }

        return true;
    }

    private boolean validateGenero(String genero) {
        // Validar que no sean de porno
        return true;
    }

    private boolean validateDirector(String director) {
        //Validar no quiero que sea de Nacho Vidal o Derivadas , Tarantino
        return true;
    }

    private boolean validateFecha(LocalDate fecha) {
        //Fechas despues del 2000
        LocalDate limite = LocalDate.of(2000, 1, 1);
        return fecha.isAfter(limite);
    }


    private boolean validatePegi(PegiEnum pegi) {
        //No quiero ALL publicos
        return !pegi.equals(PegiEnum.ALL);
    }

    private boolean validateStock(int stock) {
        // No stock negativo y ademas validar valor maximo.
        return stock <= MAX_MOVIES && stock >= 0;
    }


}
