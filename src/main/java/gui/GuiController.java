package gui;

import lib.Fechas;
import lib.IO;
import logic.LogicController;
import persistance.entity.Movie;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GuiController {
    private final LogicController logicController;

    public GuiController() {
        this.logicController = new LogicController(this);
    }

    public void start() {
        int option;
        do {
            option = menu();

            switch (option) {
                case 1:
                    showMovieCreationInterface();
                    break;
                case 2:
                    showAllMoviesInfo();
                    break;
                case 3:
                    showUpdateMovieInterface();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("chao");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 5);
    }


    public void showAllMoviesInfo() {
        List<Movie> movies = logicController.retrieveAllMovies();
        if (movies == null || movies.isEmpty()) {
            System.out.println("No se encontraron películas.");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    private void showMovieCreationInterface() {
        boolean validado = false;

        do {
            System.out.println("\n========== CREAR PELÍCULA ==========");

            String titulo = IO.readNonEmptyString("Dime el título (minúscula):");
            String genero = IO.readNonEmptyString("Dime el género:");
            String director = IO.readNonEmptyString("Dime el director:");

            System.out.println("Clasificaciones disponibles: " + Arrays.toString(PegiEnum.values()));
            PegiEnum pegi = PegiEnum.valueOf(IO.readNonEmptyString("Dime la calificación de edad:").toUpperCase());

            LocalDate fecha = Fechas.parsearFecha(IO.readString("Dime la fecha (dd/MM/yyyy):"));
            int stock = IO.readInt("Dime el stock:");

            if (logicController.createMovie(titulo, genero, director, pegi, fecha, stock)) {
                System.out.println(logicController.getStatusMessage());
                validado = true;
            } else {
                System.out.println("Error: " + logicController.getStatusMessage());
                System.out.println("Por favor, intenta de nuevo.\n");
            }

        } while (!validado);
    }

    private void showUpdateMovieInterface() {
        boolean validado = false;
        do {
            System.out.println("\n========== ACTUALIZAR PELÍCULA ==========");
            int id = IO.readInt("Introduce el ID de la película a actualizar:");

            Movie movie = logicController.getMovieById(id);
            if (movie == null) {
                System.out.println(logicController.getStatusMessage() + "\n");
                return;
            }

            System.out.println("Datos actuales:");
            System.out.println(movie);

            String titulo = movie.getTitulo();
            if (IO.readString("¿Quieres cambiar el título? (s/n):").equalsIgnoreCase("s")) {
                titulo = IO.readNonEmptyString("Nuevo título:");
            }

            String genero = movie.getGenero();
            if (IO.readString("¿Quieres cambiar el género? (s/n):").equalsIgnoreCase("s")) {
                genero = IO.readNonEmptyString("Nuevo género:");
            }

            String director = movie.getNombreDirector();
            if (IO.readString("¿Quieres cambiar el director? (s/n):").equalsIgnoreCase("s")) {
                director = IO.readNonEmptyString("Nuevo director:");
            }

            PegiEnum pegi = movie.getClasificacionEdad();
            if (IO.readString("¿Quieres cambiar la clasificación de edad? (s/n):").equalsIgnoreCase("s")) {
                System.out.println("Clasificaciones disponibles: " + java.util.Arrays.toString(PegiEnum.values()));
                pegi = PegiEnum.valueOf(IO.readNonEmptyString("Nueva clasificación de edad:").toUpperCase());
            }

            LocalDate fecha = movie.getFechaLanzamiento();
            if (IO.readString("¿Quieres cambiar la fecha de lanzamiento? (s/n):").equalsIgnoreCase("s")) {
                fecha = Fechas.parsearFecha(IO.readString("Nueva fecha (dd/MM/yyyy):"));
            }

            int stock = movie.getStock();
            if (IO.readString("¿Quieres cambiar el stock? (s/n):").equalsIgnoreCase("s")) {
                stock = IO.readInt("Nuevo stock:");
            }

            if (logicController.updateMovie(id, titulo, genero, director, pegi, fecha, stock)) {
                System.out.println(logicController.getStatusMessage() + "\n");
                validado = true;
            } else {
                System.out.println("Error: " + logicController.getStatusMessage());
                System.out.println("Por favor, intenta de nuevo.\n");
            }
        } while (!validado);
    }


    private int menu() {
        boolean valida;
        int option;

        do {
            System.out.println("\n========== VIDEOCLUB ==========");
            System.out.println("1. Crear película");
            System.out.println("2. Mostrar películas");
            System.out.println("3. Actualizar película");
            System.out.println("4. Eliminar película");
            System.out.println("5. Salir");

            option = IO.readInt("Seleccione una opción:");

            valida = option >= 1 && option <= 5;

            if (!valida) {
                System.out.println("Opción no válida. Por favor, selecciona entre 1 y 5.");
            }

        } while (!valida);

        return option;
    }
}