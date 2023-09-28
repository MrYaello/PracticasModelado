/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Auto {
    protected String nombre;
    protected String llantas;
    protected String motor;
    protected String carroceria;
    protected String armas;
    protected String blindaje;
    protected int ataque;
    protected int defensa;
    protected int velocidad;
    protected int costo;

    public int obtenerCosto() {
        return costo;
    }

    public void mostrarCaracteristicas() {
        System.out.println(nombre);
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Blindaje: " + blindaje);
        System.out.println("Costo: " + obtenerCosto() + " pesos.");
    }

    public void mostrarEstadisticas() {
        System.out.println("Las estadísticas de " + nombre + " son:");
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
        System.out.println("Velocidad: " + velocidad);
    }
}

