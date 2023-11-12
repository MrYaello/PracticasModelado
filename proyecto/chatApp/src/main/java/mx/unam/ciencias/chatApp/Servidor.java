package mx.unam.ciencias.chatApp;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {
        final int puerto = 12345;

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

    private static void manejarCliente(Socket clienteSocket) {
        try {
            Scanner entrada = new Scanner(clienteSocket.getInputStream());
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Obtener el nombre de usuario del cliente
            String nombreUsuario = entrada.nextLine();
            System.out.println("Usuario conectado: " + nombreUsuario);

            while (true) {
                String mensajeCliente = entrada.nextLine();

                // Verificar si el mensaje es privado
                if (mensajeCliente.startsWith("/privado")) {
                    enviarMensajePrivado(nombreUsuario, mensajeCliente);
                } else {
                    // Si no es privado, simplemente imprimir el mensaje
                    System.out.println(nombreUsuario + ": " + mensajeCliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarMensajePrivado(String remitente, String mensaje) {
        // Lógica para enviar mensajes privados (puedes adaptar según tus necesidades)
        // En este ejemplo, simplemente imprimimos el mensaje privado
        System.out.println("Mensaje privado de " + remitente + ": " + mensaje);
    }
}
