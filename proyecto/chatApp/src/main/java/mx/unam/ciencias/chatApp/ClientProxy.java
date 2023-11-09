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
    final String appPath = "../../../../../web/app.html";
    static Client client;

    public static void main(String[] args) throws IOException, InterruptedException, NullPointerException {
        ClientProxy cp = new ClientProxy();
        client = new Client();
        HttpServer server = cp.launchServer(8080);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        cp.stopServer(server);

        client.setUsername("");
    }

    public HttpServer launchServer(int port) throws IOException, InterruptedException {
        HttpServer server = HttpServer.create();
        StringBuilder sb = new StringBuilder();
        FileInputStream is = new FileInputStream("src/main/web/index.html");
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine().trim());
        }
        is.close();
        String htmlResponse = sb.toString();
        //String htmlResponse = "asd";
        System.out.println(htmlResponse);
        server.bind(new InetSocketAddress(port), 0);

        server.start();

        server.createContext("/",
                exchange -> {
                    exchange.getResponseHeaders().set("Content-Type","text/html");
                    exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(htmlResponse.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    os.close();
                }
        );



        System.out.println("a");
        return server;
    }

    public void stopServer(HttpServer server) {
        server.stop(1);
    }

    public static void printErrorMessage(JsonObject error) {
        System.out.println(error.get("error").getAsJsonObject().get("message").getAsString());
    }
}