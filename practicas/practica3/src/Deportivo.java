/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Deportivo extends Auto {
    public Deportivo() {
        nombre = "Deportivo";
        llantas = "Deportivas";
        motor = "Deportivo";
        carroceria = "Camion";
        armas = "Metralleta";
        blindaje = "Reforzado";
        ataque = 200 + 150 + 120 + 100 + 180;
        defensa = 30 + 60 + 50 + 100 + 140;
        velocidad = 40 + 200 + 50 + 80 + 110;
        costo = 1500 + 2000 + 2000 + 2500;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println(nombre + ": Mazda 3.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + obtenerCosto() + " pesos.");
    }
}
