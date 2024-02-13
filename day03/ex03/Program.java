package ex03;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 1 || !args[0].matches("--threadsCount=\\d+")) {
            System.exit(-1);
        }

        int numOfThreads = Integer.parseInt(args[0].substring(15));
        if (numOfThreads <= 0) {
            System.err.println("Illegal argument");
            System.exit(-1);
        }

        BufferedReader reader = new BufferedReader(new FileReader("ex03/files_urls.txt"));
        Queue<String> links = new LinkedList<>();
        String link;

        while ((link = reader.readLine()) != null) {
            links.add(link);
        }

        reader.close();

        Downloader.createQueue(links);

        for (int i = 0 ; i < numOfThreads; i++) {
            new MyThread().start();
        }
    }
}

