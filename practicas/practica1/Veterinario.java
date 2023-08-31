/**
 * Practica 1 del curso de Modelado y Programación.
 * Clase Veterinario
 * @author Yael Lozano Estrada 319007095
 * @version Version 1.0
 */

public class Veterinario {

  /** Nombre del veterinario. */
  public String nombre;
  /** La mascota A de la última amistad creada */
  private Mascota ultimaAmistadA;
  /** La mascota B de la última amistad creada */
  private Mascota ultimaAmistadB;

  /**
   * Construye un nuevo veterinario.
   * @param nombre el nombre del veterinario.
   */
  public Veterinario(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Hace mejores amigos a dos mascotas.
   * @param m1 primera mascota.
   * @param m2 segunda mascota.
   */
  public void hagaseLaAmistad(Mascota m1, Mascota m2) {
    if (m1.equals(ultimaAmistadA) || m2.equals(ultimaAmistadA) ||
            m1.equals(ultimaAmistadB) || m2.equals(ultimaAmistadB)) {
      m1.eliminaUltimoMejorAmigo();
      m2.eliminaUltimoMejorAmigo();
    }
    ultimaAmistadA = m1;
    ultimaAmistadB = m2;
    m1.agregaMejoresAmigos(m2);
    m2.agregaMejoresAmigos(m1);
    System.out.println("El veterinario " + nombre + " está haciendo amigos a " + m1.getNombre() + " y " + m2.getNombre());
  }

  /**
   * Método principal para probar la clase Veterinario.
   * @param args argumentos de linea de comandos.
   */
  public static void main(String[] args) {
    Mascota m1 = new Mascota("Bruno", 6, true, "Ladrar");
    Mascota m2 = new Mascota("Michi", 6, false, "Ladrar");
    Mascota m3 = new Mascota("Poti", 6, false, "Ladrar");
    Mascota m4 = new Mascota("Pepo", 6, true, "Ladrar");
    Mascota m5 = new Mascota("Suki", 6, false, "Ladrar");
    Mascota m6 = new Mascota("Wuwai", 6, true, "Ladrar");

    Veterinario vet = new Veterinario("Yael");
    System.out.println("Los mejores amigos de " + m1.getNombre() + " son " + m1.getMejoresAmigos());
    vet.hagaseLaAmistad(m1, m5);
    System.out.println("Los mejores amigos de " + m1.getNombre() + " son " + m1.getMejoresAmigos());
    System.out.println("Los mejores amigos de " + m5.getNombre() + " son " + m5.getMejoresAmigos());
    vet.hagaseLaAmistad(m1, m2);
    vet.hagaseLaAmistad(m5, m4);
    System.out.println("Los mejores amigos de " + m3.getNombre() + " son " + m3.getMejoresAmigos());
    System.out.println("Los mejores amigos de " + m4.getNombre() + " son " + m4.getMejoresAmigos());
    vet.hagaseLaAmistad(m1, m2);

    System.out.println("Los mejores amigos de " + m1.getNombre() + " son " + m1.getMejoresAmigos());
    System.out.println("Los mejores amigos de " + m2.getNombre() + " son " + m2.getMejoresAmigos());
    System.out.println("Los mejores amigos de " + m5.getNombre() + " son " + m5.getMejoresAmigos());
    vet.hagaseLaAmistad(m5, m6);
    System.out.println("Los mejores amigos de " + m5.getNombre() + " son " + m5.getMejoresAmigos());
    System.out.println("Los mejores amigos de " + m6.getNombre() + " son " + m6.getMejoresAmigos());
  }
}
