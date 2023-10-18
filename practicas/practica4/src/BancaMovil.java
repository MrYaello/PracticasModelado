import java.util.Random;
import java.util.Scanner;

/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class BancaMovil {
    Cliente cliente;
    public Banco santander = new BancoSantander();
    public Banco bancomer = new BancoBancomer();
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
        crearCuenta();
    }

    public void crearCuenta() {
        String opcion = "";
        System.out.println("Seleccione el banco donde se creará la cuenta: B - Bancomer, S - Santander");
        opcion = sc.next().toUpperCase();
        if ((opcion.equals("B") && bancomer.getCuentaPorCliente(cliente) != null) ||
                (opcion.equals("S") && santander.getCuentaPorCliente(cliente) != null)) {
            System.out.println("Ya tiene una cuenta en este banco.");
            return;
        }
        switch (opcion) {
            case "B":
                bancomer.agregarCuenta(cliente);
                System.out.println("Nueva cuenta " + bancomer.getCuentaPorCliente(cliente).getNumeroCuenta() + " creada.");
                break;
            case "S":
                santander.agregarCuenta(cliente);
                System.out.println("Nueva cuenta " + santander.getCuentaPorCliente(cliente).getNumeroCuenta() + " creada.");
                break;
            default:
                System.out.println(opcion + " no es una opción válida.");
                return;
        }
    }

    public void clienteExistente()
}
