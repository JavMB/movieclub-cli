package logic.services;

import logic.filters.DirectorFilter;
import logic.filters.Filter;
import logic.filters.GenreFilter;
import logic.filters.ProfanityFilter;

import persistance.PersistanceController;
import persistance.entity.Movie;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;
import java.util.List;

public class MoviesService {

    private Filter<String> profanityFilter;
    private Filter<String> generoFilter;
    private Filter<String> directorFilter;
    private PersistanceController persistanceController;
    private final int MAX_MOVIES;


    public MoviesService(PersistanceController pc) {
        this.persistanceController = pc;
        this.MAX_MOVIES = 40;
        this.profanityFilter = new ProfanityFilter();
        this.generoFilter = new GenreFilter();
        this.directorFilter = new DirectorFilter();
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
        movie.setTitulo(titulo);
        movie.setGenero(genero);
        movie.setNombreDirector(director);
        movie.setClasificacionEdad(pegi);
        movie.setFechaLanzamiento(fecha);
        movie.setStock(stock);

        persistanceController.saveMovie(movie);

    }

    public Movie getMovieById(int id) throws IllegalArgumentException {
        if (!validateId(id)) {
            throw new IllegalArgumentException("Id inválido");
        }
        return persistanceController.getMovieById(id).orElse(null);
    }

    private boolean validateId(int id) {
        int totalMoviesInDB = persistanceController.getMovieCount();
        boolean condicionMovieIdMenorQueTotalInDB = id <= totalMoviesInDB;
        boolean condicionMovieIdNegativeOrZero = id <= 0;

        // valida que el id existe para poder traerlo
        return !condicionMovieIdNegativeOrZero && condicionMovieIdMenorQueTotalInDB;

    }

    private boolean validateTitulo(String titulo) {
        //Validar que no haya palabras malsonantes, formato = minusculas

        if (!titulo.equals(titulo.toLowerCase()) || !profanityFilter.isValid(titulo)) {
            return false;

        }

        return true;
    }

    private boolean validateGenero(String genero) {
        // Validar que no sean de porno/similar
        return generoFilter.isValid(genero);
    }

    private boolean validateDirector(String director) {
        //Validar no sea de Nacho Vidal o Derivadas ,Tarantino
        return directorFilter.isValid(director);
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

    public List<Movie> retrieveAllMovies() {
        return persistanceController.getAllMovies();
    }


    public Movie updateMovie(int id, String titulo, String genero, String director, PegiEnum pegi, LocalDate fecha, Integer stock) throws IllegalArgumentException {

        if (!validateId(id)) {
            throw new IllegalArgumentException("Id inválido");
        }

        Movie toUpdateMovie = getMovieById(id);
        if (toUpdateMovie == null) {
            throw new IllegalArgumentException("Película no encontrada");
        }


        if (titulo != null) {
            if (!validateTitulo(titulo)) {
                throw new IllegalArgumentException("Título inválido");
            }
            toUpdateMovie.setTitulo(titulo);
        }

        if (genero != null) {
            if (!validateGenero(genero)) {
                throw new IllegalArgumentException("Género inválido");
            }
            toUpdateMovie.setGenero(genero);
        }

        if (director != null) {
            if (!validateDirector(director)) {
                throw new IllegalArgumentException("Director inválido");
            }
            toUpdateMovie.setNombreDirector(director);
        }

        if (pegi != null) {
            if (!validatePegi(pegi)) {
                throw new IllegalArgumentException("Clasificación PEGI inválida");
            }
            toUpdateMovie.setClasificacionEdad(pegi);
        }

        if (fecha != null) {
            if (!validateFecha(fecha)) {
                throw new IllegalArgumentException("Fecha inválida");
            }
            toUpdateMovie.setFechaLanzamiento(fecha);
        }

        if (stock != null) {
            if (!validateStock(stock)) {
                throw new IllegalArgumentException("Stock inválido");
            }
            toUpdateMovie.setStock(stock);
        }

        persistanceController.saveMovie(toUpdateMovie);
        return toUpdateMovie;
    }
}