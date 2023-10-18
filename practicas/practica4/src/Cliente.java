/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

public class Cliente {
    private int edad;
    private String nombre;
    private String rfc;
    private int scoreBuro;

    public Cliente(int edad, String nombre, String rfc, int scoreBuro) {
        this.edad = edad;
        this.nombre = nombre;
        this.rfc = rfc;
        this.scoreBuro = scoreBuro;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getScoreBuro() {
        return scoreBuro;
    }

    public void setScoreBuro(int scoreBuro) {
        this.scoreBuro = scoreBuro;
    }
}