package mx.unam.ciencias.chatApp;

import com.google.gson.*;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class ClientProxy {
    ArrayList<String> messages = new ArrayList<>();
    final String loginPath = "src/main/web/index.html";
    final String appPath = "src/main/web/app.html";
    public static HttpServer server;
    String htmlLoginResponse = fileToString(new File(loginPath));
    String htmlAppResponse = fileToString(new File(appPath));
    Client client = new Client();
    ArrayList<Client> onlineClients = new ArrayList<>();
    static Scanner in;
    static PrintWriter out;
    static int online;
    String selected = "General";

    public ClientProxy() throws IOException {}

    public static void main(String[] args) throws IOException, InterruptedException, NullPointerException {
        ClientProxy cp = new ClientProxy();
        String serverIP = "127.0.0.1";
        int serverPort = 5050;
        try {
            Socket clientSocket = new Socket(serverIP, serverPort);
            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            cp.procesarMensaje(in.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int webPort = 8080 + online - 1;
        new Thread(() -> cp.handleServerMessages(in)).start();
        cp.launchServer(webPort);
        cp.openBrowser("http://localhost:" + webPort);
    }

    private void handleServerMessages(Scanner in) {
        while (in.hasNextLine()) {
            procesarMensaje(in.nextLine());
        }
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

        server.createContext("/",
                exchange -> {
                    handleResponse(exchange, htmlLoginResponse);

                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("name")) {
                        client.setUsername(query.substring(5));
                        out.println(new Gson().toJson(sendUsername(client.getUsername())));
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
        handleRequests(exchange.getRequestMethod(), exchange.getRequestURI().toString());
    }

    private void handleRequests(String method, String uri) {
        switch (method) {
            case "GET":
                if (uri.contains("receive")) {
                    selected = uri.substring(10);
                }
                break;
            case "POST":
                if (uri.contains("state")) {
                    System.out.println(uri.substring(8));
                    out.println(new Gson().toJson(updateState(uri.substring(8))));
                } else if (uri.contains("logout")) {
                    stopServer();
                } else if (uri.contains("send")) {
                    out.println(new Gson().toJson(sendMessage(client.getUsername(), uri.substring(7).split(":")[0], uri.substring(7).split(":")[1])));
                }
                break;
        }
    }

    public void refresh() throws IOException {
        String response = htmlAppResponse;
        String users = "";
        for (Client client : onlineClients) {
            if (client.equals(this.client)) continue;
            users += addUser(client.getUsername(), client.getState());
        }
        String msgs = "";
        for (String message : messages) {
            if (message.split("_")[1].equals(selected)) {
                msgs += addMessage(message.split("_")[0], message.split("_")[2], message.split("_")[0].equals(client.getUsername()));
            }
            if (message.split("_")[0].equals(selected) && !message.split("_")[1].equals("General")) {
                msgs += addMessage(message.split("_")[0], message.split("_")[2], message.split("_")[0].equals(client.getUsername()));
            }
        }
        response = (htmlAppResponse.substring(0, htmlAppResponse.indexOf("{user-start}")) + users
                + htmlAppResponse.substring(htmlAppResponse.indexOf("{user-end}")));
        response = (response.substring(0, response.indexOf("{messages-start}")) + msgs
                + response.substring(response.indexOf("messages-end}"))).replace("{username}", client.getUsername());
        String finalResponse = response;

        server.removeContext("/");
        server.createContext("/",
                exchange -> {
                    handleResponse(exchange, finalResponse);
                }
        );
    }
    
    private String addUser(String username, ClientState state) {
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

    private String addMessage(String remitente, String contenido, boolean sent) {
        return "<div class=\"message " + (sent ? "messageR" : "messageL") +"\">\n" +
                "                <div class=\"author " + (sent ? "left" : "rigth") + "\">\n" +
                "                    " + (sent ? "{username}" : remitente) + "\n" +
                "                </div>\n" +
                "                <div class=\"content " + (sent ? "left" : "rigth") + "\">\n" +
                "                    " + contenido + "\n" +
                "                </div>\n" +
                "            </div>";
    }

    public void stopServer() {
        server.stop(1);
        System.out.println("[HTTP SERVER] Stoping Server.");
        out.println(new Gson().toJson(sendLogout()));
        System.out.println("a");
        System.exit(1);
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

    private static JsonObject sendUsername(String username) {
        JsonObject response = new JsonObject();
        response.addProperty("Type", "Data");
        response.addProperty("Username", username);
        return response;
    }

    private static JsonObject sendLogout() {
        JsonObject response = new JsonObject();
        response.addProperty("Type", "Logout");
        return response;
    }

    private static JsonObject sendMessage(String sender, String recipient, String content) {
        JsonObject response = new JsonObject();
        response.addProperty("Type", "Message");
        response.addProperty("Sender", sender);
        response.addProperty("Recipient", recipient);
        response.addProperty("Content", content);
        return response;
    }

    private static JsonObject updateState(String state) {
        JsonObject response = new JsonObject();
        response.addProperty("Type", "Data");
        response.addProperty("State", state);
        return response;
    }

    public void procesarMensaje(String request) {
        JsonObject requestJson = JsonParser.parseString(request).getAsJsonObject();
        if (requestJson.has("Type")) {
            String tipoMensaje = requestJson.get("Type").getAsString().toUpperCase();

            switch (tipoMensaje) {
                case "DATA": {
                    if (requestJson.has("UserList")) {
                        procesarListaUsuarios(requestJson.getAsJsonArray("UserList"));
                    } else if (requestJson.has("UserNumber")) {
                        procesarNumeroUsuarios(requestJson.getAsJsonPrimitive("UserNumber").getAsInt());
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
                default:
                    System.out.println("Tipo de mensaje desconocido: " + tipoMensaje);
                    break;
            }
        } else {
            System.out.println("Campo 'Type' no encontrado en el mensaje JSON.");
        }
    }

    private void procesarListaUsuarios(JsonArray listaUsuarios) {
        ArrayList<Client> temp = new ArrayList<>();
        System.out.println("Recibiendo ListaUsuarios: " + listaUsuarios);
        for (JsonElement element: listaUsuarios) {
            JsonObject jo = element.getAsJsonObject();
            temp.add(new Client(jo.getAsJsonPrimitive("Username").getAsString(), jo.getAsJsonPrimitive("State").getAsString()));
        }
        onlineClients = temp;
    }

    private void procesarMensaje(String remitente, String destinatario, String contenido) {
        System.out.println("Procesando mensaje. Remitente: " + remitente + ", Destinatario: " + destinatario + ", Contenido: " + contenido);
        messages.add(remitente + "_" + destinatario + "_" + contenido);
    }

    private void procesarNumeroUsuarios(int userNumber) {
        System.out.println("Recibiendo userNumber: " + userNumber);
        online = userNumber;
    }
}
