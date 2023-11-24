/**
 * Proyecto final del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
package mx.unam.ciencias.chatApp;

import com.google.gson.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Servidor {

    private static final int puerto = 5050;
    private static Map<Client, PrintWriter> clientesConectados = new HashMap<>();
    private static Gson gson = new Gson();
    private static int online;

    public static void main(String[] args) {
        online = 0;
        try {
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                online++;
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress().getHostAddress());

                new Thread(() -> manejarCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void manejarCliente(Socket clienteSocket) {
        try {
            Scanner entrada = new Scanner(clienteSocket.getInputStream());
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            sendUserNumber(online, salida);

            // Obtener el nombre de usuario del cliente
            procesarSolicitud(entrada.nextLine(), clienteSocket, salida);
            sendUserList();
            while (entrada.hasNextLine()) {
                procesarSolicitud(entrada.nextLine(), clienteSocket, salida);

                // Verificar si el mensaje es privado
                /*if (mensajeCliente.startsWith("/privado")) {
                    // Convertir el mensaje JSON a un objeto Java
                    MensajePrivado mensajePrivado = gson.fromJson(mensajeCliente.substring(9), MensajePrivado.class);

                    // Enviar el mensaje privado al destinatario
                    enviarMensajePrivado(client, mensajePrivado.getDestinatario(), mensajePrivado.getMensaje());
                } else {
                    // Si no es privado, enviar el mensaje a todos los clientes
                    enviarMensajeATodos(client, mensajeCliente);
                }*/
            }
            if (!clienteSocket.getKeepAlive()) clientesConectados.remove(getClientByOS(salida));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarMensajePrivado(Client remitente, String destinatario, String mensaje) {
        // Obtener el flujo de salida del destinatario
        PrintWriter destino = clientesConectados.get(new Client(destinatario, ClientState.ACTIVE));

        // Verificar si el destinatario existe
        if (destino != null) {
            // Enviar el mensaje privado al destinatario
            destino.println(new Gson().toJson(sendMessage(remitente.getUsername(), destinatario, mensaje)));
        }
        clientesConectados.get(remitente).println(new Gson().toJson(sendMessage(remitente.getUsername(), destinatario, mensaje)));
    }

    private static void enviarMensajeATodos(String remitente, String mensaje) {
        for (PrintWriter clienteSalida : clientesConectados.values()) {
            clienteSalida.println(new Gson().toJson(sendMessage(remitente, "General", mensaje)));
        }
    }

    private static JsonObject sendMessage(String sender, String recipient, String content) {
        JsonObject response = new JsonObject();
        response.addProperty("Type", "Message");
        response.addProperty("Sender", sender);
        response.addProperty("Recipient", recipient);
        response.addProperty("Content", content);
        return response;
    }

    public static void procesarSolicitud(String request, Socket clientSocket, PrintWriter clientOut) {
        JsonObject requestJson = JsonParser.parseString(request).getAsJsonObject();
        if (requestJson.has("Type")) {
            String tipoMensaje = requestJson.get("Type").getAsString().toUpperCase();
            switch (tipoMensaje) {
                case "DATA": {
                    if (requestJson.has("Username")) {
                        procesarUsername(requestJson.getAsJsonPrimitive("Username").getAsString(), clientOut);
                    } else if (requestJson.has("State")) {
                        procesarEstado(requestJson.getAsJsonPrimitive("State").getAsString());
                    }
                    break;
                }
                case "MESSAGE": {
                    if (requestJson.has("Sender") && requestJson.has("Recipient") && requestJson.has("Content")) {
                        String remitente = requestJson.getAsJsonPrimitive("Sender").getAsString();
                        String destinatario = requestJson.getAsJsonPrimitive("Recipient").getAsString();
                        String contenido = requestJson.getAsJsonPrimitive("Content").getAsString();

                        procesarMensaje(remitente, destinatario, contenido);
                    }
                    break;
                }
                case "LOGOUT": {
                    clientesConectados.remove(getClientByOS(clientOut));
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sendUserList();
                    break;
                }
                default:
                    System.out.println("Tipo de mensaje desconocido: " + tipoMensaje);
                    break;
            }
        } else {
            System.out.println("Campo 'Type' no encontrado en el mensaje JSON.");
        }
    }

    private static Client getClientByOS(PrintWriter clientOut) {
        return (Client) clientesConectados.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), clientOut))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet()).toArray()[0];
    }

    private static void sendUserNumber(int userNumber, PrintWriter clientOut) {
        JsonObject response = new JsonObject();
        response.addProperty("Type", "Data");
        response.addProperty("UserNumber", userNumber);
        clientOut.println(new Gson().toJson(response));
    }

    private static void procesarUsername(String username, PrintWriter clientOut) {
        System.out.println("Procesando mensaje de tipo Username. Username: " + username);
        System.out.println("Usuario conectado: " + username);
        Client client = new Client(username, ClientState.ACTIVE);
        clientesConectados.put(client, clientOut);
    }

    private static void procesarEstado(String estado) {
        System.out.println("Procesando mensaje de tipo Estado. Estado: " + estado);
        // Lógica para procesar la información asociada con el tipo Estado
    }

    private static void sendUserList() {
        JsonObject response = new JsonObject();
        JsonArray userlist = new JsonArray();
        for (Client client : clientesConectados.keySet()) {
            JsonObject clientJson = new JsonObject();
            clientJson.addProperty("Username", client.getUsername());
            clientJson.addProperty("State", client.getState().toString());
            userlist.add(clientJson);
        }
        response.addProperty("Type", "Data");
        response.add("UserList", userlist);
        for (PrintWriter out : clientesConectados.values()) {
            out.println(new Gson().toJson(response));
        }
    }

    private static void procesarMensaje(String remitente, String destinatario, String contenido) {
        System.out.println("Procesando mensaje. Remitente: " + remitente + ", Destinatario: " + destinatario + ", Contenido: " + contenido);
        if (destinatario.equals("General")) {
            enviarMensajeATodos(remitente, contenido);
        } else {
            Client sender = getClientByUsername(remitente);
            enviarMensajePrivado(sender, destinatario, contenido);
        }
    }

    private static Client getClientByUsername(String username) {
        for (Client client : clientesConectados.keySet()) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }
}
