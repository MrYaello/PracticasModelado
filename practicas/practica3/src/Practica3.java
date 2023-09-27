import java.util.Scanner;

public class Practica3 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String blue = "\u001B[36m";
        String red = "\u001B[31m";
        String formatoClasico = "\u001B[0m";

        boolean continuar = true;

        while (continuar) {
            System.out.println(blue + "BIENVENIDO USUARIO. :D \n Por favor, elige alguna de nuestras opciones: \n 1. Elegir auto existente. \n 2. Personalizar mi auto." + formatoClasico);
            int elegir = sc.nextInt();
            sc.nextLine(); //Limpiar el buffer.

            if (elegir == 1) {
                Autos autos = new Autos(sc);
                autos.AutosDefault();
            } else if (elegir == 2) {
                MiAutoPersonalizado miAuto = new MiAutoPersonalizado(sc);
                miAuto.personalizarAuto();
            } else {
                System.err.println("Elige una opción correcta.");
            }

            System.out.println(formatoClasico + "¿Deseas personalizar otro auto? (y/n): " + red);
            String respuesta = sc.next();

            if (!respuesta.equalsIgnoreCase("y")) {
                System.out.println("¡Hasta luego!");
                continuar = false;
            }
        }

        sc.close();
    }
}


