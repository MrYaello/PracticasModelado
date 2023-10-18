import java.util.Scanner;

/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Main extends BancaMovil {
    public static void main(String[] args) {
        BancaMovil banca = new BancaMovil();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean logeado = false;
        while (!salir) {
            while (!logeado) {
                System.out.println("¿Ya es cliente? (y/n)");
                boolean cliente = sc.next().toLowerCase().equals("y");
                if (cliente) {
                    banca.clienteExistente();
                    if (banca.cliente == null) continue;
                    System.out.println(banca.mostarCuentas());
                } else {
                    banca.nuevoCliente();
                    logeado = true;
                }
            }
            System.out.println("Seleccione una de las siguientes operaciones:");
            System.out.println("1. Crear nueva cuenta.");
            System.out.println("2. Solicitar prestamo.");
            System.out.println("3. Obtener información detallada de la cuenta.");
            System.out.println("4. Salir.");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    banca.crearCuenta();
                    break;
                case 2:
                    banca.solicitarPrestamo();
                    break;
                case 3:
                    banca.consultarCuenta();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println(opcion + " no es una opción válida.");
            }
            System.out.println("¿Desea realizar otra operación? (y/n)");
            salir = !sc.next().toLowerCase().equals("y");
        }
    }
}