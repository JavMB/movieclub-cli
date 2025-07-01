package logic;

import persistance.PersistanceController;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;

public class LogicController {
    private PersistanceController persistanceController;

    public LogicController() {
        persistanceController = new PersistanceController();
    }

    public void createMovie(int id, String titulo, String genero, String director, PegiEnum pegi, LocalDate fecha, int stock) throws IllegalArgumentException {
        if (!validateId(id)) {
            throw new IllegalArgumentException("Id invalido");
        }
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


    }


    private boolean validateId(int id) {
        int totalMoviesInDB = persistanceController.getMovieCount();
        boolean condicionMovieIdMenorQueTotalInDB = totalMoviesInDB <= id;
        boolean condicionMovieIdNegativeOrZero = totalMoviesInDB <= 0;
        //Habria que validar que el id que se va meter no exista ya dentro de la BD

    }

    private boolean validateTitulo(String titulo) {
        //Validar que no haya palabras malsonantes, formato = minusculas
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
        //Todas las pelis antes del 2000
        return true;
    }

    private boolean validatePegi(PegiEnum pegi) {
        //No quiero ALL publicos
        return true;
    }

    private boolean validateStock(int stock) {
        // No stock negativo y ademas validar valor maximo.
        return true;
    }


}
