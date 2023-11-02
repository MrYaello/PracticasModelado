/**
 * Práctica 4 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        /*
         * se establece la dirección del servidor ("localhost" en este caso, lo que significa que el servidor 
         * se ejecuta en la misma máquina) y el puerto al que el cliente se conectará.
         */
        String serverAddress = "localhost";
        int serverPort = 46810;

        try {
            /*
             * El cliente crea un socket para conectarse al servidor utilizando la dirección y el puerto especificados.
             */
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Conectado al servidor en " + serverAddress + ":" + serverPort);
            
            /*
             * Al igual que en el código del servidor, se obtienen flujos de entrada y salida a través del socket para comunicarse con el servidor.
             */
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            /*
             * Se crean objetos BufferedReader y PrintWriter para leer y escribir datos a través de los flujos de entrada y salida.
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);
            
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            
            /*
             * el cliente crea un hilo para escuchar los mensajes del servidor en segundo plano.
             */
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
            
            /*
             * El cliente entra en un bucle donde espera la entrada del usuario (mensajes del cliente) desde la consola y luego 
             * envía esos mensajes al servidor a través del objeto writer.
             */
            while (true) {
                System.out.print("Cliente: ");
                String clientMessage = userInput.readLine();
                if (clientMessage == null || clientMessage.isEmpty()) {
                    break;
                }
                
                writer.println(clientMessage);
            }
            
            /*
             *Detiene el hilo del servidor
             */
            serverThread.interrupt(); 
            /*
             * cuando el bucle de envío de mensajes del cliente se detiene, se cierran las conexiones y se liberan los recursos.
             */
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
