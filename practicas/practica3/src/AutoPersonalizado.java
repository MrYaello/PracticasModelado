import java.util.Scanner;

/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class AutoPersonalizado extends Auto {

    public AutoPersonalizado() {
        llantas = "";
        motor = "";
        carroceria = "";
        blindaje = "";
        armas = "";
        ataque = 0;
        defensa = 0;
        velocidad = 0;
        costo = 0;
        personalizarAuto();
    }

    public void personalizarAuto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Personaliza tu auto estilo Mad Max:");
        System.out.println("Selecciona el nombre de tu nuevo auto: ");
        nombre = sc.nextLine();

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
        switch (llantas.toLowerCase()) {
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
            default:
                System.err.println("\u001B[31m" + llantas + " no es un tipo válido.");
                return;
        }

        switch (motor.toLowerCase()) {
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
            default:
                System.err.println("\u001B[31m" + motor + " no es un tipo válido.");
                return;
        }

        switch (carroceria.toLowerCase()) {
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
            default:
                System.err.println("\u001B[31m" + carroceria + " no es un tipo válido.");
                return;
        }

        switch (blindaje.toLowerCase()) {
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
            default:
                System.err.println("\u001B[31m" + blindaje + " no es un tipo válido.");
                return;
        }

        switch (armas.toLowerCase()) {
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
            default:
                System.err.println("\u001B[31m" + armas + " no es un tipo válido.");
                return;
        }
    }
}

