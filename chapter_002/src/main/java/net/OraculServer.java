package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OraculServer {

    private final Socket socket;

    OraculServer(Socket socket) {
        this.socket = socket;
    }

    void init() {
        String ask;
        try (
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if ("counting".equals(ask)) {
                    out.println("Start counting:");
                    out.println("1");
                    out.println("2");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    out.println("Unknown command");
                    out.println();
                }
            } while (!"exit".equals(ask));
        } catch (Exception e) {
              System.out.println(e.getLocalizedMessage());
        }
    }


    public static void main(String[] args) throws Exception {
        try (Socket socket = new ServerSocket(31234).accept()) {
            new OraculServer(socket).init();
        }
    }



}
