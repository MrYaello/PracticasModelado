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

            if (elegir == 1) {
                Autos autos = new Autos();
                autos.AutosDefault();
            } else if (elegir == 2) {
                MiAutoPersonalizado miAuto = new MiAutoPersonalizado();
                miAuto.personalizarAuto();
            } else {
                System.err.println("Elige una opción correcta.");
            }

            sc.nextInt();

            System.out.println(formatoClasico + "¿Deseas personalizar otro auto? (1.si \n 2. no): " + red);
            int respuesta = sc.nextInt();

            if (respuesta != 1) {
                System.out.println("¡Hasta luego!");
                continuar = false;
            }
        }

        sc.close();
    }
}


