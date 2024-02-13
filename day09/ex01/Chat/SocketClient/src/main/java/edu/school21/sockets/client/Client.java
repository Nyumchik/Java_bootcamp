package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        ServerOut serverOut = new ServerOut(out);
        ServerIn serverIn = new ServerIn(in, serverOut);
        serverIn.start();
        serverOut.start();

        try {
            serverIn.join();
            serverOut.join();
            stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }

    private class ServerIn extends Thread {
        private Scanner reader;
        public ServerOut serverOut;

        public ServerIn(Scanner reader, ServerOut serverOut) {
            this.reader = reader;
            this.serverOut = serverOut;
        }

        @Override
        public void run() {
            try {
                receiveMessage();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        private void receiveMessage() throws IOException {
            while (reader.hasNextLine()) {
                String message = reader.nextLine();
                System.out.println(message);

                if ("Exit".equals(message)) {
                    reader.close();
                    serverOut.active = false;
                    break;
                }
            }
        }
    }

    private class ServerOut extends Thread {
        private PrintWriter out;
        private Scanner scanner = new Scanner(System.in);
        private boolean active = true;

        public ServerOut(PrintWriter out) {
            this.out = out;
        }

        @Override
        public void run() {
            try {
                sendMessage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        private void sendMessage() {
            while (active) {
                String message = scanner.nextLine();
                out.println(message);

                if ("Exit".equals(message)) {
                    System.exit(0);
                }
            }
            out.close();
        }
    }
}
