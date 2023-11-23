/**
 * Proyecto final del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
package mx.unam.ciencias.chatApp;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
 /*
  * CLASE QUE ENLAZA AL SERVIDOR CON LOS CLIENTES
  */
public class ServerClient {

    public static void main(String[] args) {
        final String servidorIP = "127.0.0.1"; // Cambia a la dirección IP de tu servidor
        final int puerto = 5050;

        try {
            Socket clienteSocket = new Socket(servidorIP, puerto);

            // Obtener flujos de entrada y salida del cliente
            Scanner entrada = new Scanner(clienteSocket.getInputStream());
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Obtener el nombre de usuario del cliente (puede ser ingresado por el usuario)
            System.out.print("Ingrese su nombre de usuario: ");
            Scanner scanner = new Scanner(System.in);
            String nombreUsuario = scanner.nextLine();

            // Enviar el nombre de usuario al servidor
            salida.println(nombreUsuario);

            // Crear un hilo para manejar la recepción de mensajes del servidor
            new Thread(() -> manejarMensajesServidor(entrada)).start();

            // Ahora, puedes enviar mensajes al servidor
            Scanner scannerMensaje = new Scanner(System.in);
            while (true) {
                System.out.print("Ingrese un mensaje (use /privado destinatario mensaje para mensajes privados): ");
                String mensaje = scannerMensaje.nextLine();
                salida.println(generarMensajeJSON(nombreUsuario, mensaje));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void manejarMensajesServidor(Scanner entrada) {
        while (entrada.hasNextLine()) {
            String mensajeServidor = entrada.nextLine();
            System.out.println(mensajeServidor);
        }
    }

    private static String generarMensajeJSON(String remitente, String mensaje) {
        MensajeJSON mensajeJSON = new MensajeJSON(remitente, mensaje);
        Gson gson = new Gson();
        return gson.toJson(mensajeJSON);
    }

    private static class MensajeJSON {
        private String remitente;
        private String mensaje;

        public MensajeJSON(String remitente, String mensaje) {
            this.remitente = remitente;
            this.mensaje = mensaje;
        }
    }
}
