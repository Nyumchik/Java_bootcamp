package ex02;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.exit(0);
        }

        Path path = null;

        if (args[0].startsWith("--current-folder=")) {
            path = Paths.get(args[0].substring(17));
        } 
        else {
            System.out.println("Path not found");
            System.exit(0);
        }
        if (!Files.isDirectory(path)) {
            System.out.println("Directory not found");
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {

            input = scanner.nextLine();
            String[] cmd = input.split(" ");
            switch (cmd[0]) {
                case "ls":
                    List<Path> listPath = Files.walk(path, 1).collect(Collectors.toList());
                    for (Path p : listPath) {
                        if (!p.equals(path) && !p.getFileName().toString().startsWith("."))
                            System.out.println(p.getFileName() + " " + Files.size(p) + " KB");
                    }
                    break;

                case "cd":
                    try {
                        if (cmd[1] == null || cmd[1].isEmpty()) {
                            break;
                        }
                    }
                    catch (java.lang.ArrayIndexOutOfBoundsException e) {
                        break;
                    }

                    Path tmpPath = path;
                    path = Paths.get(path.toString() + "/" + cmd[1]);
                    if (Files.exists(path) && Files.isDirectory(path)) {
                        path = path.normalize();
                        System.out.println(Paths.get(path.toAbsolutePath() + "/"));
                    } else {
                        path = tmpPath;
                        System.out.println("Error path");
                    }
                    break;
                case "mv":
                try {
                    if (cmd[1] == null || cmd[1].isEmpty() || cmd[2] == null || cmd[2].isEmpty()) {
                        break;
                    }
                }
                catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    break;
                }    
                    Path source = Paths.get(path + "/" + cmd[1]).normalize();
                    Path destination = Paths.get(path + "/" + cmd[2]).normalize();
                    try {
                        if (Files.isRegularFile(source)) {
                            if (Files.isDirectory(destination)) {
                                destination = Paths.get(destination + "/" + source.getFileName());
                            }
                            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                        } else
                            System.out.println("File not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        scanner.close();
    }
}
