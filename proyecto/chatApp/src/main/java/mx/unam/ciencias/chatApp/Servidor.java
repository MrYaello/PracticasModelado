/**
 * Proyecto final del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
package mx.unam.ciencias.chatApp;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Servidor {

    private static final int puerto = 5050;
    private static Map<String, PrintWriter> clientesConectados = new HashMap<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        try {
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress().getHostAddress());

                new Thread(() -> manejarCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    private static void manejarCliente(Socket clienteSocket) {
        try {
            Scanner entrada = new Scanner(clienteSocket.getInputStream());
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Obtener el nombre de usuario del cliente
            String nombreUsuario = entrada.nextLine();
            System.out.println("Usuario conectado: " + nombreUsuario);

            // Agregar el cliente a la lista de clientes conectados
            clientesConectados.put(nombreUsuario, salida);
            clients.add(new Client(nombreUsuario, ClientState.ACTIVE));

            while (true) {
                String mensajeCliente = entrada.nextLine();

                // Verificar si el mensaje es privado
                if (mensajeCliente.startsWith("/privado")) {
                    // Convertir el mensaje JSON a un objeto Java
                    MensajePrivado mensajePrivado = gson.fromJson(mensajeCliente.substring(9), MensajePrivado.class);

                    // Enviar el mensaje privado al destinatario
                    enviarMensajePrivado(nombreUsuario, mensajePrivado.getDestinatario(), mensajePrivado.getMensaje());
                } else {
                    // Si no es privado, enviar el mensaje a todos los clientes
                    enviarMensajeATodos(nombreUsuario, mensajeCliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarMensajePrivado(String remitente, String destinatario, String mensaje) {
        // Obtener el flujo de salida del destinatario
        PrintWriter destino = clientesConectados.get(destinatario);

        // Verificar si el destinatario existe
        if (destino != null) {
            // Enviar el mensaje privado al destinatario
            destino.println("Mensaje privado de " + remitente + ": " + mensaje);
        } else {
            System.out.println("El usuario " + destinatario + " no está conectado.");
        }
    }

    private static void enviarMensajeATodos(String remitente, String mensaje) {
        // Enviar el mensaje a todos los clientes conectados
        for (PrintWriter clienteSalida : clientesConectados.values()) {
            clienteSalida.println(remitente + ": " + mensaje);
        }
    }

    public static int getClientNumber() {
        System.out.println(clientesConectados.size());
        return clientesConectados.size();
    }

    private static class MensajePrivado {
        private String destinatario;
        private String mensaje;

        public String getDestinatario() {
            return destinatario;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
