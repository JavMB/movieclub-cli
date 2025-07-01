package gui;

import lib.Fechas;
import lib.IO;
import logic.LogicController;
import persistance.entity.Movie;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;

public class GuiController {
    public final LogicController logicController;

    public GuiController() {
        this.logicController = new LogicController();
    }

    public void start() {
        switch (menu()) {
            case 1:
                showMovieCreationInterface();

            case 2:

            case 3:

            case 4:

            default:

        }


    }


    private void showMovieCreationInterface() {
        String titulo = IO.readNonEmptyString("Dime el titulo");
        String genero = IO.readNonEmptyString("Dime el genero");
        String director = IO.readNonEmptyString("Dime el director");
        PegiEnum pegi = PegiEnum.valueOf(IO.readNonEmptyString("Dime la calificacion de edad"));
        LocalDate fecha = Fechas.parsearFecha(IO.readString("Dime la fecha"));
        int stock = IO.readInt("Dime la stock");

        logicController.createMovie(titulo, genero, director, pegi, fecha, stock);
    }

    // private void read() {
//     System.out.println("Dime el id:");
//     int id = IO.readInt("Dime el id");
//     try {
//
//         Movie movie = logicController.read(id);
//
//         if (movie == null) {
//             System.out.println("La película con ese ID no existe.");
//         } else {
//             System.out.println("Película encontrada: " + movie.getTitulo());
//         }

//     } catch (IllegalArgumentException e) {
//         System.out.println("Error al leer la película: " + e.getMessage());
//     }
// }


    private int menu() {
        boolean valida;
        int option;

        do {
            System.out.println("========== VIDEOCLUB ==========");
            System.out.println("1. Crear cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");

            option = IO.readInt("Seleccione una opción:");

            valida = option >= 1 && option <= 4;

        } while (!valida);

        return option;

    }


}
