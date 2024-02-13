package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Component
public class Server {
    private UsersService usersService;
    private Scanner in;
    private PrintWriter out;
    private ServerSocket server;
    private Socket client;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void start(int port) {
        String username = null;
        String password = null;
        try {
            server = new ServerSocket(port);
            client = server.accept();
            in = new Scanner(client.getInputStream());
            out = new PrintWriter(client.getOutputStream(), true);
            out.println("Hello from Server!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            stop();
        }

        while (true) {
            try {
                if (in.hasNextLine()) {
                    String cmd = in.nextLine().trim();
                    if (!"signUp".equalsIgnoreCase(cmd)) {
                        throw new RuntimeException("Expected: signUp");
                    }
                }
                out.println("Enter username:");

                if (in.hasNextLine()) {
                    username = in.nextLine().trim();
                    if (username.isEmpty()) {
                        throw new RuntimeException("Username cannot be empty");
                    }
                }
                out.println("Enter password:");

                if (in.hasNextLine()) {
                    password = in.nextLine().trim();
                    if (password.isEmpty()) {
                        throw new RuntimeException("Password cannot be empty");
                    }
                }
                usersService.signUp(new User(username, password));
                out.println("Successful!");
                stop();
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                out.println(e.getMessage());
            }
        }
    }

    private void stop() {
        try {
            if (in != null) {
                in.close();
            }

            if (out != null) {
                out.close();
            }

            if (client != null) {
                client.close();
            }

            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }
}