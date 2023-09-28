/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

 /**
 * Esta línea define la clase Economico y especifica que extiende la clase Auto. Esto significa que Economico 
 * hereda propiedades y métodos de la clase Auto y puede agregar o sobrescribir comportamientos según sea necesario.
 */
public class Economico extends Auto {
    /*
     * Este es el constructor de la clase Economico, que se llama cuando se crea una instancia de Economico.
     */
    public Economico() {
        nombre = "Economico"; /** Establece el atributo nombre de la instancia de Economico en "Economico". */
        llantas = "Simple"; /* Establece el atributo llantas en "Simple". */
        motor = "Diesel"; /* Establece el atributo motor en "Diesel". */
        carroceria = "Casual"; /* Establece el atributo carroceria en "Casual". */
        armas = "Arpones"; /* Establece el atributo armas en "Arpones". */
        blindaje = "Simple"; /* Establece el atributo blindaje en "Simple". */
        
         /**
         * Calcula el valor de cada atributo  como la suma de los valores indicados.
         */
        ataque = 100 + 180 + 80 + 80 + 100;
        defensa = 50 + 50 + 60 + 80 + 50;
        velocidad = 20 + 100 + 30 + 40 + 40;
        costo = 1000 + 1000 + 1000 + 1500;
    }

    /**
     * Imprime las características del objeto Economico.
     */
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

