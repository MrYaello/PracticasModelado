package mx.unam.ciencias.chatApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerClient {

    public static void main(String[] args) {
        final String servidorIP = "localhost";
        final int puerto = 12345;

        try {
            Socket socket = new Socket(servidorIP, puerto);

            // Establecer la entrada y salida de datos
            Scanner entrada = new Scanner(socket.getInputStream());
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Obtener el nombre de usuario del cliente
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa tu nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            // Enviar el nombre de usuario al servidor
            salida.println(nombreUsuario);

            // Iniciar un hilo para recibir mensajes del servidor
            new Thread(() -> {
                while (entrada.hasNextLine()) {
                    String mensajeServidor = entrada.nextLine();
                    System.out.println(mensajeServidor);
                }
            }).start();

            // Enviar mensajes al servidor
            while (true) {
                System.out.print("Escribe un mensaje (/privado para mensaje privado): ");
                String mensajeCliente = scanner.nextLine();
                salida.println(mensajeCliente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

