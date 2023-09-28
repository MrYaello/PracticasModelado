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
        /*
         * Se crea un objeto Scanner para leer la entrada del usuario desde la consola estándar Esto permite 
         * que el programa interactúe con el usuario y recopile información a través del teclado.
         */
        Scanner sc = new Scanner(System.in);
        String blue = "\u001B[36m";
        String red = "\u001B[31m";
        String clasico = "\u001B[0m";

        /**
         * Se crea un objeto de la clase AutoFactory llamado autoFactory.
         */
        AutoFactory autoFactory = new AutoFactory();
        Autos autos = new Autos(autoFactory);

        boolean continuar = true;
        System.out.println(blue + "BIENVENIDO. :D");
        /*
         *  Inicia un bucle while que se ejecutará siempre que la variable booleana continuar sea verdadera.
         */
        while (continuar) {
            System.out.println(blue + "Tienes " + autos.dinero + " pesos." + clasico);
            autos.runAutos();

            System.out.println(clasico + "¿Deseas adquirir otro auto? (y/n): " + red);
            String respuesta = sc.next();

            if (!respuesta.equalsIgnoreCase("y")) {
                System.out.println("¡Hasta luego!");
                continuar = false; /** Cierra el bucle while */
            }
        }

        //* Cierra el Scanner llamado sc. */
        sc.close();
    }
}


