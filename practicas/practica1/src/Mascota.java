/**
 * Practica 1 del curso de Modelado y Programación.
 * Clase Mascota.
 * @author Yael Lozano Estrada 319007095
 * @version Version 1.0
 */

public class Mascota {

  /** Contador de instancias creadas */
  static int contador = 0;
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
  /** Mejor amigo de la mascota */
  private Mascota[] mejoresAmigos;
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
    this.mejoresAmigos = new Mascota[0];
    contador++;
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
    this.edad = edad;
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
    return this.sexo ? "Macho" : "Hembra";
  }

  /**
   * Modifica la adoracion de la mascota.
   * @param adoracion la nueva adoracion de la mascota.
   */
  public void setAdoracion(String adoracion) {
    this.adoracion = adoracion;
  }

  /**
   * Permite accesar a la adoración de la mascota.
   * @return la adoración de la mascota
   */ 
  public String getAdoracion() {
    return this.adoracion;
  }

  /**
   * Muestra la mascota en forma de cadena.
   * @return cadena con información de la mascota.
   */
  @Override
  public String toString() {
    return String.format("%s (%d) %s%s%s \n" +
            "Adora: %s \n" +
            "Sus recuerdos son: %s \n", nombre, edad, tipo != null ? tipo + " ": "", raza != null ? raza  + " ": "", getSexo(), adoracion, getRecuerdos());
  }

  /**
   * Muestra los recuerdos de la mascota.
   * @return cadena con los recuerdos de la mascota.
   */
  public String getRecuerdos() {
    String temp = "[";
    for (String s: recuerdos) temp += s + ", ";
    return temp.substring(0, temp.lastIndexOf(", ") < 0 ? temp.length() : temp.lastIndexOf(", ")) + "]";
  }

  /**
   * Agrega recuerdos a la lista de recuerdos de la mascota, con un máximo
   * de tres.
   * @param recuerdo nuevo recuerdo que agregar a la lista.
   * @throws Exception Si la mascota ya tiene más de 3 recuerdos.
   */
  private void agregaRecuerdo(String recuerdo) throws Exception {
    if (recuerdos.length + 1 > 3) throw new Exception(this.nombre + " no puede recordar más.");
    String[] temp = new String[recuerdos.length + 1];
    for (int i = 0; i < recuerdos.length; i++) temp[i] = recuerdos[i];
    recuerdos = temp;
    recuerdos[temp.length - 1] = recuerdo;
  }

  /**
   * Muestra los mejores amigos de la mascota.
   * @return cadena con los mejores amigos de la mascota.
   */
  public String getMejoresAmigos() {
    if (mejoresAmigos.length == 0) return "si mismo :(";
    if (mejoresAmigos.length == 1) return mejoresAmigos[0].nombre;
    String temp = "";
    for (int i = 0; i < mejoresAmigos.length; i++) {
      temp += mejoresAmigos[i].nombre;
      if (i == mejoresAmigos.length - 2) temp += " y ";
      else temp += ", ";
    }
    return temp.substring(0, temp.lastIndexOf(", "));
  }

  /**
   * Agrega mejores amigos a la lista de mejores amigos de la mascota.
   * @param amigo nuevo amigo que agregar a la lista.
   */
  public void agregaMejoresAmigos(Mascota amigo) {
    Mascota[] temp = new Mascota[mejoresAmigos.length + 1];
    for (int i = 0; i < mejoresAmigos.length; i++) temp[i] = mejoresAmigos[i];
    mejoresAmigos = temp;
    mejoresAmigos[temp.length - 1] = amigo;
  }

  /**
   * Elimina el último mejor amigo agregado a la lista.
   */
  public void eliminaUltimoMejorAmigo() {
    Mascota[] temp = new Mascota[Math.max(mejoresAmigos.length - 1, 0)];
    for (int i = 0; i < temp.length; i++) temp[i] = mejoresAmigos[i];
    mejoresAmigos = temp;
  }

  /**
   * Método para compartir los recuerdos entre mascotas.
   * @param mascota mascota a la que se trasmitirá el recuerdo.
   * @throws Exception Si se envía como argumento el mismo objeto.
   */
  public void contar(Mascota mascota) throws Exception {
    if (this.equals(mascota)) throw new Exception("No te puedes contar algo a ti mismo.");
    mascota.agregaRecuerdo(this.nombre + " adora " + this.adoracion);
  }

  /**
   * Método principal para probar la clase Mascota.
   * @param args argumentos de linea de comandos.
   */
  public static void main(String[] args) {
    Mascota m1 = new Mascota("Bruno", 6, "Perro", "Salchicha", "Cafe", true, "Ser acariciado");
    Mascota m2 = new Mascota("Michi", 2, false, "Jugar");
    Mascota m3 = new Mascota("Lucky", 2, "Perico", "Australiano", "Verde", false, "Ser alimentado");
    Mascota m4 = new Mascota("Pepo", 9, "Perro", "Chihuahua", "Negro", true, "Perseguir ratones");
    Mascota m5 = new Mascota("Suki", 6, false, "Ladrar");
    Mascota m6 = new Mascota("Wuwai", 4, "Gato", "Naranjoso", "Naranja", true, "Odiar su existencia");
    try {
      m1.contar(m2);
      m1.contar(m3);
      m1.contar(m4);
      m1.contar(m5);
      m1.contar(m6);
      m2.contar(m1);
      m3.contar(m1);
      m4.contar(m1);
      //Debe imprimir Bruno no puede recordar más.
      m5.contar(m1);
      //Debe imprimir No te puedes contar algo a ti mismo.
      //m1.contar(m1);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(m1);
    System.out.println(m2);
    System.out.println(m3);
    System.out.println(m4);
    System.out.println(m5);
    System.out.println(m6);
    System.out.println(Mascota.contador + " instancias creadas.");
  }
}
