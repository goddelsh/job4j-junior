package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class OraculClient {

    private final Socket socket;

    public OraculClient(Socket socket) {
        this.socket = socket;
    }

    public void init() {
        try (
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Scanner console = new Scanner(System.in);
            String str, command = "";
            do {
                command = console.nextLine();
                out.println(command);

                while (!(str = in.readLine()).isEmpty()) {
                    System.out.println(str);
                }
            }  while (!"exit".equals(command));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 31234)) {
                new OraculClient(socket).init();
        } catch (Exception ex) {

        }
    }

}


