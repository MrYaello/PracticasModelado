/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

/**
 * Esta línea define la clase Deportivo y especifica que extiende la clase Auto. Esto significa que Deportivo hereda propiedades y métodos de la clase Auto 
 * y puede agregar o sobrescribir comportamientos según sea necesario.
 */
public class Deportivo extends Auto {
    /*
     * Este es el constructor de la clase Deportivo, que se llama cuando se crea una instancia de Deportivo.
     */
    public Deportivo() {
        nombre = "Deportivo"; /** Establece el atributo nombre de la instancia de Deportivo en "Deportivo". */
        llantas = "Deportivas"; /* Establece el atributo llantas en "Deportivas". */
        motor = "Deportivo"; /* Establece el atributo motor en "Deportivo". */
        carroceria = "Camion"; /* Establece el atributo carroceria en "Casual". */
        armas = "Metralleta"; /* Establece el atributo armas en "Metralleta". */
        blindaje = "Reforzado"; /* Establece el atributo blindaje en "Reforzado". */

        /**
         * Calcula el valor de cada atributo  como la suma de los valores indicados.
         */
        
        ataque = 200 + 150 + 120 + 100 + 180;
        defensa = 30 + 60 + 50 + 100 + 140;
        velocidad = 40 + 200 + 50 + 80 + 110;
        costo = 1500 + 2000 + 2000 + 2500;
    }

    /**
     * Imprime las características del objeto Deportivo.
     */
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
