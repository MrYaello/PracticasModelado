import java.util.Scanner;

public class MiAutoPersonalizado {
    private String llantas;
    private String motor;
    private String carroceria;
    private String blindaje;
    private String armas;

    private int ataque;
    private int defensa;
    private int velocidad;
    private int costo;
    private int miDinero = 10000;
    Scanner sc;

    public MiAutoPersonalizado(Scanner sc) {
        llantas = "";
        motor = "";
        carroceria = "";
        blindaje = "";
        armas = "";
        ataque = 0;
        defensa = 0;
        velocidad = 0;
        costo = 0;
        this.sc = sc;
    }

    public void personalizarAuto() {
        System.out.println("Personaliza tu auto estilo Mad Max:");
        System.out.println("Selecciona el tipo de llantas (simple, deportivas, off-road, oruga de tanque): ");
        llantas = sc.nextLine();

        System.out.println("Selecciona el tipo de motor (deportivo, diesel, turbo): ");
        motor = sc.nextLine();

        System.out.println("Selecciona el tipo de carrocería (casual, camión, deportiva): ");
        carroceria = sc.nextLine();

        System.out.println("Selecciona el tipo de blindaje (simple, reforzado, tanque): ");
        blindaje = sc.nextLine();

        System.out.println("Selecciona el tipo de armas (arpones, lanzallamas, cañones, sierra, metralleta): ");
        armas = sc.nextLine();

        calcularAtributos();
    }

    private void calcularAtributos() {
        // Define los valores para los componentes elegidos
        switch (llantas) {
            case "simple":
                ataque += 100;
                defensa += 50;
                velocidad += 20;
                costo += 1000;
                break;
            case "deportivas":
                ataque += 200;
                defensa += 30;
                velocidad += 40;
                costo += 1500;
                break;
            case "off-road":
                ataque += 80;
                defensa += 70;
                velocidad += 30;
                costo += 2000;
                break;
            case "oruga de tanque":
                ataque += 120;
                defensa += 100;
                velocidad += 10;
                costo += 2500;
                break;
        }

        switch (motor) {
            case "deportivo":
                ataque += 150;
                defensa += 60;
                velocidad += 200;
                costo += 1500;
                break;
            case "diesel":
                ataque += 180;
                defensa += 50;
                velocidad += 100;
                costo += 2000;
                break;
            case "turbo":
                ataque += 290;
                defensa += 80;
                velocidad += 250;
                costo += 2500;
                break;
        }

        switch (carroceria) {
            case "casual":
                ataque += 80;
                defensa += 60;
                velocidad += 30;
                costo += 1000;
                break;
            case "camion":
                ataque += 120;
                defensa += 50;
                velocidad += 50;
                costo += 1500;
                break;
            case "deportivo":
                ataque += 150;
                defensa += 90;
                velocidad += 40;
                costo += 2000;
                break;
        }

        switch (blindaje) {
            case "simple":
                ataque += 80;
                defensa += 80;
                velocidad += 40;
                costo += 1000;
                break;
            case "reforzado":
                ataque += 100;
                defensa += 100;
                velocidad += 80;
                costo += 1500;
                break;
            case "tanque":
                ataque += 150;
                defensa += 120;
                velocidad += 60;
                costo += 2000;
                break;

        }

        switch (armas) {
            case "arpones":
                ataque += 100;
                defensa += 50;
                velocidad += 40;
                costo += 1000;
                break;
            case "lanzallamas":
                ataque += 120;
                defensa += 80;
                velocidad += 60;
                costo += 1500;
                break;
            case "cañones":
                ataque += 140;
                defensa += 100;
                velocidad += 80;
                costo += 2000;
                break;
            case "sierra":
                ataque += 150;
                defensa += 120;
                velocidad += 100;
                costo += 2500;
                break;
            case "metralleta":
                ataque += 180;
                defensa += 140;
                velocidad += 110;
                costo += 2500;
                break;
        }

        // Muestra las estadísticas del auto personalizado
        String purple = "\u001B[35m"; 
        String formatoClasico = "\u001B[0m";
        System.out.println(purple+"FELICIDADES :D."+formatoClasico);
        System.out.println("Tu nuevo auto personalizado tiene las siguientes estadísticas:");
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
        System.out.println("Velocidad: " + velocidad);
        int meSobra = miDinero - costo;
        System.out.println("Costo: " + costo + " pesos. Ahora tienes: "  + meSobra + " pesos.");
        System.out.println("¡Que lo disfrutes! ¡Y recuerda siempre conducir con cuidado!");
    }
}

