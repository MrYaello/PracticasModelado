/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        /*
         * numero de puerto para que se conecte el Cliente
         */
        int puerto = 46810;
        
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Esperando conexión en el puerto " + puerto + "...");
            
            /* 
             * Cuando un cliente intenta conectarse al servidor, la llamada a serverSocket.accept() bloquea el programa hasta que se establezca una conexión con un cliente. 
             * Una vez que se acepta la conexión, se crea un objeto Socket que representa la conexión con el cliente.
            */
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());
            
            /*
             * Se obtienen flujos de entrada y salida a través del socket del cliente para comunicarse con él.
             */
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            
            /*
             * Se crean objetos BufferedReader y PrintWriter para facilitar la lectura y 
             * escritura de datos a través de los flujos de entrada y salida.
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);
            
            BufferedReader userInput = new BufferedReader(new InputSporttreamReader(System.in));
            
            /*
             * Se crea un hilo que escucha los mensajes del cliente en segundo plano mientras el servidor está esperando la 
             * entrada del usuario. Esto permite que el servidor y el cliente se comuniquen simultáneamente.
             */
            Thread serverThread = new Thread(() -> {
                try {
                    while (true) {
                        String clientMessage = reader.readLine();
                        if (clientMessage == null) {
                            break;
                        }
                        System.out.println("Cliente: " + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();
            
            /*
             * El servidor entra en un bucle donde espera la entrada del usuario (mensajes del servidor) desde la consola 
             * y luego envía esos mensajes al cliente a través del objeto writer.
             */
            while (true) {
                System.out.print("Servidor: ");
                String serverMessage = userInput.readLine();
                if (serverMessage == null || serverMessage.isEmpty()) {
                    break;
                }
                
                writer.println(serverMessage);
            }
            
            /*
             * Detiene el hilo del servidor
             */
            serverThread.interrupt(); 
            /*
             * Cuando el bucle de envío de mensajes del servidor se detiene, se cierran las conexiones y se liberan los recursos.
             */
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
