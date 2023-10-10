/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

/**
 * Se define la clase Auto que representa la forma básica de un auto y los métodos principales.
 */
public class Auto {
    /** Atributo correspondiente al nombre del auto */
    protected String nombre;
    /** Atributo correspondiente al tipo de llantas del auto */
    protected String llantas;
    /** Atributo correspondiente al tipo de motor del auto */
    protected String motor;
    /** Atributo correspondiente al tipo de carroceria del auto */
    protected String carroceria;
    /** Atributo correspondiente al tipo de armas del auto */
    protected String armas;
    /** Atributo correspondiente al tipo de blindaje del auto */
    protected String blindaje;
    /** Atributo correspondiente al ataque del auto */
    protected int ataque;
    /** Atributo correspondiente a la defensa del auto */
    protected int defensa;
    /** Atributo correspondiente a la velocidad del auto */
    protected int velocidad;
    /** Atributo correspondiente al costo del auto */
    protected int costo;

    /**
     * Devuelve el costo del auto.
     * @return costo -  Costo del auto.
     */
    public int obtenerCosto() {
        return costo;
    }

    /**
     * Imprime en terminal las características del auto. Llantas, Motor, Carroceria,
     * Armas, Blindaje y Costo.
     */
    public void mostrarCaracteristicas() {
        System.out.println(nombre);
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Blindaje: " + blindaje);
        System.out.println("Costo: " + obtenerCosto() + " pesos.");
    }

    /**
     * Imprime en terminal las estadísticas del auto. Ataque, Defensa y Velocidad.
     */
    public void mostrarEstadisticas() {
        System.out.println("Las estadísticas de " + nombre + " son:");
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
        System.out.println("Velocidad: " + velocidad);
    }
}

