package mx.unam.ciencias.chatApp;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.lang.StringBuilder;

public class ClientProxy {
    ArrayList<JsonObject> messages = new ArrayList<>();
    final String loginPath = "src/main/web/index.html";
    final String appPath = "src/main/web/app.html";
    static Client client;

    public static void main(String[] args) throws IOException, InterruptedException, NullPointerException {
        int port = 8080;
        ClientProxy cp = new ClientProxy();
        client = new Client();
        cp.openBrowser("http://localhost:" + port);
        HttpServer server = cp.launchServer(port);
        //cp.stopServer(server);
        System.out.println(client.getUsername());
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

    public HttpServer launchServer(int port) throws IOException, InterruptedException {
        HttpServer server = HttpServer.create();
        String htmlLoginResponse = fileToString(new File(loginPath));
        server.bind(new InetSocketAddress(port), 0);

        server.start();
        System.out.println("[HTTP SERVER] Running on port " + port);

        server.createContext("/",
                exchange -> {
                    exchange.getResponseHeaders().set("Content-Type","text/html");
                    exchange.sendResponseHeaders(200, htmlLoginResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(htmlLoginResponse.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    os.close();

                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("name"))
                        client.setUsername(query.substring(5));
                    System.out.println("[HTTP SERVER:8080] Query: " + query);
                }
        );
        while (client.getUsername().equals("")) {
            Thread.sleep(10);
        }

        String htmlAppResponse = fileToString(new File(appPath)).replace("{username}", client.getUsername());
        server.removeContext("/");
        server.createContext("/",
                exchange -> {
                    exchange.getResponseHeaders().set("Content-Type","text/html");
                    exchange.sendResponseHeaders(200, htmlAppResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(htmlAppResponse.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    os.close();

                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("name"))
                        client.setUsername(query.substring(5));
                    System.out.println("[HTTP SERVER:8080] Query: " + query);
                }
        );
        return server;
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
    public void stopServer(HttpServer server) {
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
}