/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

import java.util.Random;
import java.util.Scanner;

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
        String opcion;
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
        }
    }

    public void clienteExistente() {
        String numeroCuenta;
        System.out.println("Ingrese el número de cuenta:");
        numeroCuenta = sc.next();
        switch (numeroCuenta.charAt(0)) {
            case 'S':
                for (CuentaBancaria cuenta : santander.getCuentasBancarias()) {
                    if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                        cliente = cuenta.getCliente();
                    }
                }
                break;
            case 'B':
                for (CuentaBancaria cuenta : bancomer.getCuentasBancarias()) {
                    if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                        cliente = cuenta.getCliente();
                    }
                }
                break;
            default:
                System.out.println(numeroCuenta + " no es un número de cuenta válido.");
                return;
        }
        System.out.println("Bienvenido " + cliente.getNombre());
    }

    public void solicitarPrestamo() {
        Adapter adapter = new Adapter();
        String opcion = "";
        System.out.println("Seleccione el banco donde solicitará el prestamo: B - Bancomer, S - Santander");
        opcion = sc.next().toUpperCase();
        if ((opcion.equals("B") && bancomer.getCuentaPorCliente(cliente) == null) ||
                (opcion.equals("S") && santander.getCuentaPorCliente(cliente) == null)) {
            System.out.println("No tiene una cuenta en este banco.");
            return;
        }
        boolean aprobado = false;
        switch (opcion) {
            case "B":
                adapter.solicitarPrestamo(cliente, "bancomer");
                break;
            case "S":
                adapter.solicitarPrestamo(cliente, "santander");
                break;
            default:
                System.out.println(opcion + " no es una opción válida.");
                return;
        }
        System.out.println("Su préstamo" + (!aprobado ? " NO " : " ") + "ha sido aprobado.");
    }

    public void consultarCuenta() {
        Adapter adapter = new Adapter();
        String opcion = "";
        System.out.println("Seleccione el banco del que consultara su cuenta: B - Bancomer, S - Santander");
        opcion = sc.next().toUpperCase();
        if ((opcion.equals("B") && bancomer.getCuentaPorCliente(cliente) == null) ||
                (opcion.equals("S") && santander.getCuentaPorCliente(cliente) == null)) {
            System.out.println("No tiene una cuenta en este banco.");
            return;
        }
        switch (opcion) {
            case "B":
                System.out.println(adapter.getCuenta(cliente, "bancomer"));
                break;
            case "S":
                System.out.println(adapter.getCuenta(cliente, "santander"));
                break;
            default:
                System.out.println(opcion + " no es una opción válida.");
                return;
        }
    }
}
