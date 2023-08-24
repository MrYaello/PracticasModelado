/**
* Practica 1 del curso de Modelado y Programación.
 * Descripcion de la clase 
 * @author Yael Lozano Estrada
 * @version Version 1.0
*/

public class Mascota {

     /** Nombre de la mascota. */
    public String nombre;
    /** Edad de la mascota */
    private int edad;
    /** Tipo de mascota. */
    private String tipo;
    /** Raza de la mascota */
    private String raza;
    /** Color de la mascota */
    private String color;
    /** Sexo de la mascota.
     *  Verdadero - Macho 
     *  Falso - Hembra*/
    final private boolean sexo;

     /**
     * Construye una nueva mascota con sus atributos.
     * @param nombre el nombre de la mascota.
     * @param edad la edad de la mascota.
     * @param raza la raza de la mascota.
     * @param tipo el tipo de la mascota.
     */    
    public Mascota(String nombre, int edad, String tipo, String raza, String color, boolean sexo) {
      this.nombre = nombre;
      this.edad = edad;
      this.tipo = tipo;
      this.raza = raza;º
      this.color = color;
      this.sexo = sexo;
    }

    public Mascota(String nombre, int edad, String tipo, String color, boolean sexo) {

    }

     /**
     * Permite accesar al nombre de la mascota.
     * @return el nombre de la mascota.
     */
    public String getNombre(){
	
        return this.nombre;
    }

     /**
     * Modifica el nombre de la mascota.
     * @param nombre el nuevo nombre de la mascota.
     */
    public void setNombre(String nombre){
	
        this.nombre=nombre;
    }
}
