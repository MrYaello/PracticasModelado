package mx.unam.ciencias.chatApp;

import java.io.Serializable;

public class InfoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombreUsuario;
    private String direccionIP;
    private int puertoCliente;

    public InfoCliente(String nombreUsuario, String direccionIP, int puertoCliente) {
        this.nombreUsuario = nombreUsuario;
        this.direccionIP = direccionIP;
        this.puertoCliente = puertoCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public int getPuertoCliente() {
        return puertoCliente;
    }
}
