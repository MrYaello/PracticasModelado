import java.util.*;

/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Autos {
    int dinero;
    AutoFactory autoFactory;

    public Autos(AutoFactory autoFactory) {
        dinero = 20000;
        this.autoFactory = autoFactory;
    }

    public void runAutos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Autos para elegir:");
        Auto economico = autoFactory.crearAuto("economico");
        Auto deportivo = autoFactory.crearAuto("deportivo");
        Auto laBestia = autoFactory.crearAuto("bestia");
        Auto comprado = null;

        System.out.println("-- OPCION 1 - ECONOMICO --");
        economico.mostrarCaracteristicas();

        System.out.println("-- OPCION 2 - DEPORTIVO --");
        deportivo.mostrarCaracteristicas();

        System.out.println("-- OPCION 3 - LA BESTIA --");
        laBestia.mostrarCaracteristicas();

        System.out.println("-- OPCION 4 - PERSONALIZADO --");
        int op = sc.nextInt();

        if (op == 1) {
            comprado = economico;
        } else if (op == 2) {
            comprado = deportivo;
        } else if (op == 3) {
            comprado = laBestia;
        } else if (op == 4) {
            Auto personalizado = autoFactory.crearAuto("custom");
            comprado = personalizado;
        } else {
            System.err.println("\u001B[31m" + "Seleccionó una opción inválida.");
            return;
        }

        if (dinero < comprado.obtenerCosto()) {
            System.err.println("\u001B[31m" + "No te puedes permitir este auto.");
            return;
        } else {
            dinero -= comprado.obtenerCosto();
            String purple = "\u001B[35m";
            System.out.println(purple + "FELICIDADES :D. Disfruta tu nueva adquisición. \n¡Y recuerda siempre conducir con cuidado!" + "\u001B[0m");
        }
        comprado.mostrarCaracteristicas();
        comprado.mostrarEstadisticas();
    }
}

