/**
 * Practica 1 del curso de Modelado y Programación.
 * Clase Veterinario
 * @author Yael Lozano Estrada 319007095
 * @version Version 1.0
 */

public class Veterinario {

  /** Constructor sin parametros */
  public Veterinario() { }

  /**
   * Hace mejores amigos a dos mascotas.
   * @param m1 primera mascota.
   * @param m2 segunda mascota.
   */
  public void hagaseLaAmistad(Mascota m1, Mascota m2) {
    if (m1.getMejorAmigo() != null) eliminaMejorAmigo(m1);
    if (m2.getMejorAmigo() != null) eliminaMejorAmigo(m2);
    m1.setMejorAmigo(m2);
    m2.setMejorAmigo(m1);
    System.out.println("Haciendo amigos a " + m1.getNombre() + " y " + m2.getNombre());
  }

  /**
   * Elimina la amistad entre mascotas.
   * @param m mascota que rompe la relación.
   */
  public void eliminaMejorAmigo(Mascota m) {
    m.getMejorAmigo().setMejorAmigo(null);
    m.setMejorAmigo(null);
  }

  /**
   * Método principal para probar la clase Veterinario.
   * @param args argumentos de linea de comandos.
   */
  public static void main(String[] args) {
    Mascota m1 = new Mascota("Bruno", 6, false, "Ladrar");
    Mascota m2 = new Mascota("Michi", 6, false, "Ladrar");
    Mascota m3 = new Mascota("Poti", 6, false, "Ladrar");
    Mascota m4 = new Mascota("Pepo", 6, false, "Ladrar");
    Mascota m5 = new Mascota("Suki", 6, false, "Ladrar");
    Mascota m6 = new Mascota("Wuwai", 6, false, "Ladrar");

    Veterinario vet = new Veterinario();
    vet.hagaseLaAmistad(m1, m5);
    System.out.println(m1.muestraMejorAmigo());
    System.out.println(m5.muestraMejorAmigo());
    vet.hagaseLaAmistad(m3, m4);
    System.out.println(m3.muestraMejorAmigo());
    System.out.println(m4.muestraMejorAmigo());
    vet.hagaseLaAmistad(m1, m2);
    System.out.println(m1.muestraMejorAmigo());
    System.out.println(m2.muestraMejorAmigo());
    System.out.println(m5.muestraMejorAmigo());
    vet.hagaseLaAmistad(m5, m6);
    System.out.println(m5.muestraMejorAmigo());
    System.out.println(m6.muestraMejorAmigo());
  }
}
