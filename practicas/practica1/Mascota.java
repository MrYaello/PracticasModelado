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
  /** Lo que más adora la mascota de su dueño */
  private String adoracion;
  /** Recuerdos de la mascota */
  private String[] recuerdos;

  /**
   * Construye una nueva mascota solo con nombre y edad.
   * @param nombre el nombre de la mascota.
   * @param edad la edad de la mascota.
   * @param sexo el sexo de la mascota. V - Macho, F - Hembra
   * @param adoracion la adoración de la mascota.
   */ 
  public Mascota(String nombre, int edad, boolean sexo, String adoracion) {
    this.nombre = nombre;
    this.edad = edad;
    this.sexo = sexo;
    this.adoracion = adoracion;
    this.recuerdos = new String[0];
  }

  /**
   * Construye una nueva mascota con sus atributos sin especificar raza.
   * @param nombre el nombre de la mascota.
   * @param edad la edad de la mascota.
   * @param tipo el tipo de la mascota.
   * @param color el color de la mascota.
   * @param sexo el sexo de la mascota. V - Macho, F - Hembra
   * @param adoracion la adoración de la mascota.
   */
  public Mascota(String nombre, int edad, String tipo, String color, boolean sexo, String adoracion) {
    this(nombre, edad, sexo, adoracion);
    this.tipo = tipo;
    this.color = color;
  } 

  /**
   * Construye una nueva mascota con todos sus atributos.
   * @param nombre el nombre de la mascota.
   * @param edad la edad de la mascota.
   * @param tipo el tipo de la mascota.
   * @param raza la raza de la mascota.
   * @param color el color de la mascota.
   * @param sexo el sexo de la mascota. V - Macho F - Hembra
   * @param adoracion la adoración de la mascota.
   */    
  public Mascota(String nombre, int edad, String tipo, String raza, String color, boolean sexo, String adoracion) {
    this(nombre, edad, tipo, color, sexo, adoracion);
    this.raza = raza;
  }


  /**
   * Permite accesar al nombre de la mascota.
   * @return el nombre de la mascota.
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Modifica el nombre de la mascota.
   * @param nombre el nuevo nombre de la mascota.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Permite accesar a la edad de la mascota.
   * @return la edad de la mascota.
   */
  public int getEdad() {
    return this.edad;
  }

  /**
   * Modifica el nombre de la mascota.
   * @param edad la nueva edad de la mascota.
   */
  public void setEdad(int edad) {
    this.nombre = nombre;
  }

  /**
   * Permite accesar al tipo de la mascota.
   * @return el tipo de la mascota.
   */
  public String getTipo() {
    return this.tipo;
  }

  /**
   * Modifica el tipo de la mascota.
   * @param tipo el nuevo tipo de la mascota.
   */
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   * Permite accesar a la raza de la mascota.
   * @return la raza de la mascota.
   */
  public String getRaza() {
    return this.raza;
  }

  /**
   * Modifica la raza de la mascota.
   * @param raza la nueva raza de la mascota.
   */
  public void setRaza(String raza) {
    this.raza = raza;
  }

  /**
   * Permite accesar al color de la mascota.
   * @return el color de la mascota.
   */
  public String getColor() {
    return this.color;
  }

  /**
   * Modifica el color de la mascota.
   * @param color el nuevo color de la mascota.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Permite accesar al sexo de la mascota.
   * @return el sexo de la mascota.
   */
  public String getSexo() {
    return this.sexo == true ? "Macho" : "Hembra";
  }
  
  /**
   * Permite accesar a la adoración de la mascota.
   * @return la adoración de la mascota
   */ 
  public String getAdoracion() {
    return this.adoracion;
  }
  
  /** 
   * Modifica la adoracion de la mascota.
   * @return la adoracion de la mascota.
   */
  public void setAdoracion() {
    this.adoracion = adoracion;
  }

  @Override
  public String toString() {
    return String.format("%s (%d) %s%s%s \n" +
            "Adora: %s \n" +
            "Sus recuerdos son: %s \n", nombre, edad, tipo != null ? tipo + " ": "", raza != null ? raza  + " ": "", getSexo(), adoracion, getRecuerdos());
  }

  public String getRecuerdos() {
    String temp = "[";
    for (String s: recuerdos) temp += s + ", ";
    return temp.substring(0, temp.lastIndexOf(", ") < 0 ? temp.length() : temp.lastIndexOf(", ")) + "]";
  }

  public void contar(Mascota mascota) throws Exception {
    System.out.println(nombre + " le está contando a " + mascota.nombre);
    if (this.equals(mascota)) throw new Exception("No te puedes contar algo a ti mismo.");
    try {
      mascota.agregaRecuerdo(this.adoracion);
    } catch (Exception e) {
      System.out.println(mascota.nombre + " no puede recordar más.");
    }
  }

  private void agregaRecuerdo(String recuerdo) throws Exception {
    if (recuerdos.length + 1 > 3) throw new Exception("Demasiados recuerdos");
    String[] temp = new String[recuerdos.length + 1];
    for (int i = 0; i < recuerdos.length; i++) temp[i] = recuerdos[i];
    recuerdos = temp;
    recuerdos[temp.length - 1] = recuerdo;
  }
}
