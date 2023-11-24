package mx.unam.ciencias.chatApp;

<<<<<<< HEAD
=======
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
>>>>>>> aaf878b43af4f27934e159cb7f233621c9766809
import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class ClientProxy {
    ArrayList<JsonObject> messages = new ArrayList<>();
    final String loginPath = "src/main/web/index.html";
    final String appPath = "src/main/web/app.html";
    public static HttpServer server;
    String htmlLoginResponse = fileToString(new File(loginPath));
    String htmlAppResponse = fileToString(new File(appPath));
    Client client = new Client();
    static BufferedInputStream in;
    static PrintWriter out;

    public ClientProxy() throws IOException {}

    public static void main(String[] args) throws IOException, InterruptedException, NullPointerException {
        int webPort = 8080 + Servidor.getClientNumber();
        String serverIP = "127.0.0.1";
        int serverPort = 5050;
        try {
            Socket clientSocket = new Socket(serverIP, serverPort);
            in = new BufferedInputStream(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientProxy cp = new ClientProxy();
        cp.launchServer(webPort);
        //cp.openBrowser("http://localhost:" + port);
        //cp.stopServer(server);
    }

    public String fileToString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileInputStream is = new FileInputStream(file);
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        is.close();
        sc.close();
        return sb.toString();
    }

    public void launchServer(int port) throws IOException, InterruptedException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 0);

        server.start();
        System.out.println("[HTTP SERVER] Running on port " + port);

        String username = "";
        server.createContext("/",
                exchange -> {
                    handleResponse(exchange, htmlLoginResponse);

                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("name")) {
                        client.setUsername(query.substring(5));
                        out.println(client.getUsername());
                    }
                }
        );
        while (client.getUsername().equals("")) {
            Thread.sleep(10);
        }
        htmlAppResponse = htmlAppResponse.replace("{username}", client.getUsername());
        server.removeContext("/");
        server.createContext("/",
                exchange -> {
                    handleResponse(exchange, htmlAppResponse);
                }
        );
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        while (true) {
            Thread.sleep(100);
            refresh();
        }
    }

    private void handleResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type","text/html");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        System.out.println(exchange.getRequestMethod() + ":" + exchange.getRequestURI().toString());
    }

    public void refresh() throws IOException {
        
        String response = htmlAppResponse;
        String users = "";
        for (Client client : Servidor.getClients()) {
            users += addUser(client.getUsername(), client.getState());
        }
        users += addUser("Yael", ClientState.ACTIVE);
        users += addUser("Jacqui", ClientState.AWAY);
        users += addUser("Leslie", ClientState.BUSY);
        response = htmlAppResponse.substring(0, htmlAppResponse.indexOf("{user-start}")) + users + htmlAppResponse.substring(htmlAppResponse.indexOf("{user-end}"));
        String finalResponse = response;

        server.removeContext("/");
        server.createContext("/",
                exchange -> {
                    handleResponse(exchange, finalResponse);
                }
        );
    }

    
    public String addUser(String username, ClientState state) {
        return "<div class=\"user\">\n" +
                "                <div class=\"img\">\n" +
                "                    <svg>\n" +
                "                        <circle class=\"state "+ state.toString().toLowerCase() +"\"></circle>\n" +
                "                        <circle class=\"pic\"></circle>\n" +
                "                    </svg>\n" +
                "                </div>\n" +
                "                <div class=\"user-name\">\n" +
                "                    " + username + "\n" +
                "                </div>\n" +
                "            </div>";
    }
/**
    public HttpServer launchAppServer(int port) throws IOException, InterruptedException {
        HttpServer server = HttpServer.create();
        StringBuilder sb = new StringBuilder();
        FileInputStream is = new FileInputStream("src/main/web/app.html");
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        is.close();
        String htmlResponse = sb.toString();
        server.bind(new InetSocketAddress(port), 0);

        server.start();

        server.createContext("/",
                exchange -> {
                    exchange.getResponseHeaders().set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    os.close();

                    String query = exchange.getRequestURI().getQuery();
                    System.out.println("[HTTP SERVER:8080] Query: " + query);
                }
        );
        System.out.println("[HTTP SERVER] Running on port " + port);
        return server;
    }
*/
    public void stopServer() {
        server.stop(1);
        System.out.println("[HTTP SERVER] Stoping Server.");
    }

    public void openBrowser(String url) throws IOException{
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows"))
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        else if (osName.contains("Linux"))
            Runtime.getRuntime().exec("xdg-open " + url);
        else if (osName.contains("Mac OS X"))
            Runtime.getRuntime().exec("open " + url);
        else
            System.out.println("Ingrese a la siguiente URL: " + url);
    }

    public static void printErrorMessage(JsonObject error) {
        System.out.println(error.get("error").getAsJsonObject().get("message").getAsString());
    }

    public void procesarMensaje(JsonObject requestJson) {
        if (requestJson.has("Type")) {
            String tipoMensaje = requestJson.get("Type").getAsString().toUpperCase();

            switch (tipoMensaje) {
                case "DATA": {
                    if (requestJson.has("Data")) {
                        JsonObject data = requestJson.getAsJsonObject("Data");

                        if (data.has("Username")) {
                            procesarUsername(data.getAsJsonPrimitive("Username").getAsString());
                        } else if (data.has("State")) {
                            procesarEstado(data.getAsJsonPrimitive("State").getAsString());
                        } else if (data.has("UserList")) {
                            procesarListaUsuarios(data.getAsJsonArray("UserList"));
                        }
                    } else {
                        System.out.println("Campo 'Data' no encontrado en el mensaje de tipo Data.");
                    }
                    break;
                }
                case "MESSAGE": {
                    if (requestJson.has("Remitente") && requestJson.has("Destinatario") && requestJson.has("Contenido")) {
                        String remitente = requestJson.getAsJsonPrimitive("Remitente").getAsString();
                        String destinatario = requestJson.getAsJsonPrimitive("Destinatario").getAsString();
                        String contenido = requestJson.getAsJsonPrimitive("Contenido").getAsString();

                        procesarMensajeGeneral(remitente, destinatario, contenido);
                    } else {
                        System.out.println("Campos necesarios no encontrados en el mensaje de tipo Message.");
                    }
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

    private void procesarUsername(String username) {
        System.out.println("Procesando mensaje de tipo Username. Username: " + username);
        // Lógica para procesar la información asociada con el tipo Username
    }

    private void procesarEstado(String estado) {
        System.out.println("Procesando mensaje de tipo Estado. Estado: " + estado);
        // Lógica para procesar la información asociada con el tipo Estado
    }

    private void procesarListaUsuarios(JsonArray listaUsuarios) {
        System.out.println("Procesando mensaje de tipo ListaUsuarios. Lista de usuarios: " + listaUsuarios);
        // Lógica para procesar la información asociada con el tipo ListaUsuarios
    }

    private void procesarMensajeGeneral(String remitente, String destinatario, String contenido) {
        System.out.println("Procesando mensaje general. Remitente: " + remitente + ", Destinatario: " + destinatario + ", Contenido: " + contenido);
        // Lógica para procesar mensajes generales
    }
  
}
