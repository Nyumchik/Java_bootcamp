package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Server {
    private UsersService usersService;
    private ServerSocket server;
    private List<Client> clients = new ArrayList<>();

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void start(int port) {
        try {
            server = new ServerSocket(port);

            while(true) {
                Socket socket = server.accept();
                Client client = new Client(socket);
                clients.add(client);
                client.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            stop();
        }
    }

    private synchronized void sendMessageToAll(String username, String message) {
        usersService.saveMessage(username, message);
        clients.stream().filter(c -> c.active).forEach(c -> c.out.println(username + ":" + message));
    }

    private void removeClient(Client client) {
        clients.remove(client);
        if (clients.isEmpty()) {
            stop();
        }
    }

    private void stop() {
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }

    private class Client extends Thread {
        private PrintWriter out;
        private Scanner in;
        private Socket socket;
        private String username;
        private String password;
        private boolean active;

        public Client(Socket socket) {
            try {
                this.socket = socket;
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void run() {
            out.println("Hello from Server!");

            while (true) {
                try {
                    if (in.hasNextLine()) {
                        String cmd = in.nextLine().trim();

                        if ("signUp".equalsIgnoreCase(cmd)) {
                            if (!checkUser()) {
                                exitChat();
                                break;
                            }
                            usersService.signUp(new User(username, password));
                            out.println(username + " created!");
                        }
                        else if ("signIn".equalsIgnoreCase(cmd)) {
                            if (!checkUser()) {
                                exitChat();
                                break;
                            }

                            if (usersService.signIn(new User(username, password))) {
                                out.println("Start messaging");
                                chat();
                                break;
                            } else {
                                out.println("Try again! signIn, signUp, Exit");
                                System.out.println(username + "not authorized");
                            }
                        } else if ("".equals(cmd)) {
                            continue;
                        } else if ("Exit".equals(cmd)) {
                            exitChat();
                            break;
                        } else {
                            out.println("Unknown command!");
                        }
                    }
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                    out.println(e.getMessage());
                }
            }
        }

        private boolean checkUser() {
            out.println("Enter username: ");
            username = in.nextLine().trim();
            while(username.isEmpty()) {
                username = in.nextLine().trim();
            }
            if ("Exit".equals(username)) {
                return false;
            }
            out.println("Enter password: ");
            password = in.nextLine().trim();
            while(password.isEmpty()) {
                password = in.nextLine().trim();
            }
            if ("Exit".equals(password)) {
                return false;
            }
            return true;
        }

        private void chat() {
            while (true) {
                active = true;
                String message = in.nextLine().trim();

                if ("Exit".equals(message)) {
                    exitChat();
                    break;
                }
                sendMessageToAll(username, message);
            }
        }

        private void exitChat() {
            try {
                out.println("You have left the chat.");
                removeClient(this);
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}