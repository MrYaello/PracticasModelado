import java.util.Random;
import java.util.Scanner;

/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class BancaMovil {
    Cliente cliente;
    Scanner sc = new Scanner(System.in);
    public void nuevoCliente() {
        String nombre, rfc;
        int edad, score;
        System.out.println("Ingrese su nombre:");
        nombre = sc.nextLine();
        System.out.println("Ingrese su edad:");
        edad = sc.nextInt();
        System.out.println("Ingrese su RFC:");
        rfc = sc.next();
        System.out.println("Consultando buro...");
        Random rnd = new Random();
        score = rnd.nextInt(500, 800);
        cliente = new Cliente(edad, nombre, rfc, score);
        System.out.println("Su score en buro es: " + cliente.getScoreBuro());
    }

    public void crearCuenta() {

    }
}
