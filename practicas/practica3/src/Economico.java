
/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

/**
 * Define la clase Economico y especifica que extiende la clase Auto. Esto significa que Economico hereda propiedades 
 * y métodos de la clase Auto.
 */
public class Economico extends Auto {
    public Economico() {
        nombre = "Economico"; /** Establece el atributo nombre de la instancia de Economico en "Economico".*/ 
        llantas = "Simple";
        motor = "Diesel";
        carroceria = "Casual";
        armas = "Arpones";
        blindaje = "Simple";
        ataque = 100 + 180 + 80 + 80 + 100;
        defensa = 50 + 50 + 60 + 80 + 50;
        velocidad = 20 + 100 + 30 + 40 + 40;
        costo = 1000 + 1000 + 1000 + 1500;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println(nombre + ": Nissan Versa.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + obtenerCosto() + " pesos.");
    }
}