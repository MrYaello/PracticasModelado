/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class CuentaBancaria {
    private Cliente cliente;
    private float tasaInteres;
    private int anualidad;
    private String numeroCuenta;
    private String contrasenia;

    public CuentaBancaria(Cliente cliente, float tasaInteres, int anualidad, String numeroCuenta) {
        this.cliente = cliente;
        this.tasaInteres = tasaInteres;
        this.anualidad = anualidad;
        this.numeroCuenta = numeroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getAnualidad() {
        return anualidad;
    }

    public void setAnualidad(int anualidad) {
        this.anualidad = anualidad;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public boolean validaContrasenia(String contrasenia) {
        return contrasenia.equals(this.contrasenia);
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}