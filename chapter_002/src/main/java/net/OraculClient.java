package net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class OraculClient {

    private final Socket socket;
    private final InputStream input;
    private final PrintStream output;

    OraculClient(Socket socket, InputStream input, PrintStream output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    void init() throws Exception {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Scanner console = new Scanner(input);
            String command;
            do {
                command = console.nextLine();
                out.println(command);
                var str = in.readLine();
                while ((str != null) && !str.isEmpty()) {
                    this.output.println(str);
                    str = in.readLine();
                }
            } while (!"exit".equals(command));
        }
    }

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 31234)) {
            new OraculClient(socket, System.in, System.out).init();
        }
    }

}


