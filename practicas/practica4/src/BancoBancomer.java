/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

import java.util.ArrayList;
import java.util.Random;

public class BancoBancomer extends Banco {
    public BancoBancomer() {
        scoreNecesario = 600;
        edadMinima = 18;
        cuentasBancarias = new ArrayList<CuentaBancaria>();
        tasaBase = 0.35F;
        anualidadBase = 300;
    }

    public void agregarCuenta(Cliente cliente) {
        Random rnd = new Random();
        CuentaBancaria cuenta = new CuentaBancaria(cliente,
                tasaBase + rnd.nextFloat(0.20F),
                anualidadBase + (rnd.nextInt(5) * 50),
                "B" + (cuentasBancarias.size() + 1));
        nuevaCuenta(cuenta);
    }

    @Override
    public String solicitarPrestamo(Cliente cliente) {
        if (cliente.getScoreBuro() >= scoreNecesario && cliente.getEdad() >= edadMinima) {
            return "Bancomer puede proporcionarle un prestamo.";
        }
        return "Bancomer no puede proporcionarle un prestamo.";
    }

    @Override
    public String[] getCuenta(Cliente cliente) {
        CuentaBancaria cuenta = getCuentaPorCliente(cliente);
        System.out.println(cliente);
        return new String[]{
                cuenta.getNumeroCuenta(),
                cliente.getNombre(),
                String.valueOf(cliente.getScoreBuro()),
                String.valueOf(cliente.getEdad()),
                String.valueOf(cuenta.getTasaInteres()),
                String.valueOf(cuenta.getAnualidad())
        };
    }
}