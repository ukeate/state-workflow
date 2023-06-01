package com.statecore.gateway.net;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    public static void init() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/event", new ServerHandler());
            server.setExecutor(Executors.newFixedThreadPool(128));
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
