import java.util.Scanner;

/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Practica3 {

    /**
   * @param args the command line arguments
   */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String blue = "\u001B[36m";
        String red = "\u001B[31m";
        String clasico = "\u001B[0m";

        AutoFactory autoFactory = new AutoFactory();
        Autos autos = new Autos(autoFactory);

        boolean continuar = true;
        System.out.println(blue + "BIENVENIDO. :D");

        /**
         * Inicia un bucle while que se ejecutará siempre que la variable booleana continuar sea verdadera.
         */
        while (continuar) {
            /**
             * Llama a un método llamado runAutos() en el objeto.
             */
            System.out.println(blue + "Tienes " + autos.dinero + " pesos." + clasico);
            autos.runAutos();

            System.out.println(clasico + "¿Deseas adquirir otro auto? (y/n): " + red);
            String respuesta = sc.next();

            if (!respuesta.equalsIgnoreCase("y")) {
                System.out.println("¡Hasta luego!");
                /**
                 * Establece la variable continuar en falso, lo que hará que el bucle while se detenga en la próxima iteración.
                 */
                continuar = false;
            }
        }

        sc.close();
    }
}