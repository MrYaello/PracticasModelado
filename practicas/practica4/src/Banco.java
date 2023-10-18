/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

import java.util.ArrayList;

public abstract class Banco {
    protected ArrayList<CuentaBancaria> cuentasBancarias;
    int scoreNecesario;
    int edadMinima;
    float tasaBase;
    int anualidadBase;

    public void nuevaCuenta(CuentaBancaria cuentaBancaria) {
        cuentasBancarias.add(cuentaBancaria);
    }
    public void eliminarCuenta(Cliente cliente) {
        cuentasBancarias.removeIf(c -> cliente.equals(c.getCliente()));
    }

    public CuentaBancaria getCuentaPorCliente(Cliente cliente) {
        for (CuentaBancaria cuentaBancaria : cuentasBancarias) {
            if (cliente.equals(cuentaBancaria.getCliente())) {
                return cuentaBancaria;
            }
        }
        return null;
    }

    public abstract String solicitarPrestamo(Cliente cliente);
    public abstract String[] getCuenta(Cliente cliente);
    public ArrayList<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }
}