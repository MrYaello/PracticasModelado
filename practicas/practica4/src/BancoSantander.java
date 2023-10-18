/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

import java.util.ArrayList;
import java.util.Random;

public class BancoSantander extends Banco {
    public BancoSantander() {
        scoreNecesario = 650;
        edadMinima = 21;
        cuentasBancarias = new ArrayList<CuentaBancaria>();
        tasaBase = 0.50F;
        anualidadBase = 500;
    }

    public void agregarCuenta(Cliente cliente) {
        Random rnd = new Random();
        CuentaBancaria cuenta = new CuentaBancaria(cliente,
                tasaBase + rnd.nextFloat(0.20F),
                anualidadBase + (rnd.nextInt(5) * 50),
                "S" + (cuentasBancarias.size() + 1));
        nuevaCuenta(cuenta);
    }

    @Override
    public String solicitarPrestamo(Cliente cliente) {
        if (cliente.getScoreBuro() >= scoreNecesario && cliente.getEdad() >= edadMinima) {
            return "Su prestamo en Santander ha sido aprobado.";
        }
        return "Su prestamo en Santander no se ha aprobado.";
    }

    @Override
    public String[] getCuenta(Cliente cliente) {
        CuentaBancaria cuenta = getCuentaPorCliente(cliente);
        return new String[]{
                cliente.getNombre() + " " + cliente.getEdad(),
                String.valueOf(cliente.getScoreBuro()),
                cuenta.getNumeroCuenta(),
                String.valueOf(cuenta.getTasaInteres()),
                String.valueOf(cuenta.getAnualidad())
            };
    }
}