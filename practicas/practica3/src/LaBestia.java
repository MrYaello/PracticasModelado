/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */


/**
 * Define la clase LaBestia y especifica que extiende la clase Auto. Esto significa que LaBestia hereda propiedades 
 * y métodos de la clase Auto.
 */
public class LaBestia extends Auto {

    /**
     * El constructor de la clase LaBestia, que se llama cuando se crea una instancia de LaBestia.
     */
    public LaBestia() {

      
        nombre = "La Bestia"; /** Establece el atributo nombre de la instancia de LaBestia en "La Bestia".*/ 
        llantas = "Oruga de Tanque"; //* Establece el atributo llantas en "Oruga de Tanque". */
        motor = "Turbo";  /* Establece el atributo motor en "Turbo". */ 
        carroceria = "Camion"; /*  Establece el atributo carroceria en "Camion".*/
        armas = "Lanzallamas"; /* Establece el atributo armas en "Lanzallamas". */
        blindaje = "Tanque"; /*  Establece el atributo blindaje en "Tanque".*/

        /**
         * Calculan el valor del atributo ataque como la suma de los valores indicados.
         */
        ataque = 120 + 290 + 120 + 150 + 120;  
        defensa = 100 + 80 + 50 + 120 + 80;
        velocidad = 10 + 250 + 50 + 60 + 60;
        costo = 2500 + 2000 + 2000 + 3000;
    }
}
