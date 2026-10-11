package com.movieticket;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8081),
                        0
                );

        server.createContext("/", exchange -> {

            Path file =
                    Path.of(
                            "src",
                            "main",
                            "resources",
                            "index.html"
                    );

            byte[] response =
                    Files.readAllBytes(file);

            exchange.getResponseHeaders()
                    .set(
                            "Content-Type",
                            "text/html"
                    );

            exchange.sendResponseHeaders(
                    200,
                    response.length
            );

            try (OutputStream output =
                         exchange.getResponseBody()) {

                output.write(response);
            }
        });

        server.setExecutor(null);

        System.out.println(
                "Movie Ticket Booking System running at:"
        );

        System.out.println(
                "http://localhost:8081"
        );

        server.start();
    }
}