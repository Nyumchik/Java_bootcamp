package edu.school21.sockets.app;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--server-port=")) {
            System.exit(-1);
        }

		try (Socket socket = new Socket("localhost", Integer.parseInt(args[0].substring(14)));
            Scanner in = new Scanner(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);) {
                while (true) {
                    if (in.hasNextLine()) {
                        String cmd = in.nextLine();
                        System.out.println(cmd);
            
                        if ("Successful!".equalsIgnoreCase(cmd)) {
                            try {
                                in.close();
                                out.close();
                                scanner.close();
                                socket.close();
                            } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }
                            System.exit(0);
                        }
                        if (scanner.hasNextLine()) {
                            String cmd2 = scanner.nextLine();
                            out.println(cmd2);
                        } 
			        }
			    }
        }
        catch (IOException e) {
			e.printStackTrace();
		}
	}
}
