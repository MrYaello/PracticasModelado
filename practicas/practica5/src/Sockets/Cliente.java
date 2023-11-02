import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 46810;

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Conectado al servidor en " + serverAddress + ":" + serverPort);
            
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);
            
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            
            Thread serverThread = new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = reader.readLine();
                        if (serverMessage == null) {
                            break;
                        }
                        System.out.println("Servidor: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
            
            // Bucle para enviar mensajes desde el cliente al servidor
            while (true) {
                System.out.print("Cliente: ");
                String clientMessage = userInput.readLine();
                if (clientMessage == null || clientMessage.isEmpty()) {
                    break;
                }
                
                writer.println(clientMessage);
            }
            
            serverThread.interrupt(); // Detener el hilo del servidor
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
