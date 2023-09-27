import java.util.Scanner;

public class Practica3 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String blue = "\u001B[36m";
        String red = "\u001B[31m";
        String clasico = "\u001B[0m";

        AutoFactory autoFactory = new AutoFactory();
        Autos autos = new Autos(autoFactory);

        boolean continuar = true;
        System.out.println(blue + "BIENVENIDO. :D");
        while (continuar) {
            System.out.println(blue + "Tienes " + autos.dinero + " pesos." + clasico);
            autos.runAutos();

            System.out.println(clasico + "¿Deseas adquirir otro auto? (y/n): " + red);
            String respuesta = sc.next();

            if (!respuesta.equalsIgnoreCase("y")) {
                System.out.println("¡Hasta luego!");
                continuar = false;
            }
        }

        sc.close();
    }
}


