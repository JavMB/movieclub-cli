package gui;

import lib.Fechas;
import lib.IO;
import logic.LogicController;
import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;
import java.util.Arrays;

public class GuiController {
    private final LogicController logicController;

    public GuiController() {
        this.logicController = new LogicController();
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


                case 3:


                case 4:


                case 5:
                    System.out.println("chao");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (option != 5);
    }

    private void showMovieCreationInterface() {
        boolean validado = false;

        do {
            try {
                System.out.println("\n========== CREAR PELÍCULA ==========");

                String titulo = IO.readNonEmptyString("Dime el título (minsucula):");
                String genero = IO.readNonEmptyString("Dime el género:");
                String director = IO.readNonEmptyString("Dime el director:");

                System.out.println("Clasificaciones disponibles: " + Arrays.toString(PegiEnum.values()));
                PegiEnum pegi = PegiEnum.valueOf(IO.readNonEmptyString("Dime la calificación de edad:").toUpperCase());

                LocalDate fecha = Fechas.parsearFecha(IO.readString("Dime la fecha (dd/MM/yyyy):"));
                int stock = IO.readInt("Dime el stock:");

                logicController.createMovie(titulo, genero, director, pegi, fecha, stock);

                System.out.println("Película creada exitosamente!");
                validado = true;

            } catch (IllegalArgumentException iae) {
                System.out.println("Error: " + iae.getMessage());
                System.out.println("Por favor, intenta de nuevo.\n");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
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
            System.out.println("2. Mostrar película");
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