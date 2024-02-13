package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }

        if (args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Wrong characters for image");
            System.exit(-1);
        }

        if (args[0].charAt(0) == args[1].charAt(0)) {
            System.err.println("Equal Arguments. If you want to continue write YES.");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if (!s.equals("YES")) {
                scanner.close();
                System.exit(-1);
            }
            scanner.close();
        }

        Logic.print_img("./src/resources/it.bmp", args[0].charAt(0), args[1].charAt(0));
    }   
}
