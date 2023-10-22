/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Adapter extends BancaMovil{
    public boolean solicitarPrestamo(Cliente cliente, String banco) {
        boolean res = false;
        if (banco.equals("santander")) {
            res = !santander.solicitarPrestamo(cliente).contains("no");
        } else if (banco.equals("bancomer")) {
            res = !bancomer.solicitarPrestamo(cliente).contains("no");
        }
        return res;
    }

    public String getCuenta(Cliente cliente, String banco) {
        String cuenta = "";
        String[] cuentaBancaria = null;
        if (banco.equals("santander")) {
            cuentaBancaria = santander.getCuenta(cliente);
            cuenta = cuentaBancaria[2] + " {\n" +
                    "Nombre: " + cuentaBancaria[0].split(" ")[0] + "\n" +
                    "Edad: " + cuentaBancaria[0].split(" ")[1] + "\n" +
                    "Score: " + cuentaBancaria[1] + "\n" +
                    "Tasa interés: " + cuentaBancaria[3] + "\n" +
                    "Anualidad: " + cuentaBancaria[4] + " }";

        } else if (banco.equals("bancomer")) {
            cuentaBancaria = bancomer.getCuenta(cliente);
            cuenta = cuentaBancaria[0] + " {\n" +
                    "Nombre: " + cuentaBancaria[1] + "\n" +
                    "Edad: " + cuentaBancaria[3] + "\n" +
                    "Score: " + cuentaBancaria[2] + "\n" +
                    "Tasa interés: " + cuentaBancaria[4] + "\n" +
                    "Anualidad: " + cuentaBancaria[5] + " }";
        }
        return cuenta;
    }
}