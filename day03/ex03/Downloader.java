package ex03;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;

public class Downloader {

    private static Queue<String> urls;
    private static int fileNumber = 1;

    public static void createQueue(Queue<String> queue) {
        urls = queue;
    }

    public static synchronized String getNextLink() {
        String link;
        if ((link = urls.poll()) != null)
            return link;
        return null;
    }

    public static void downloadFile(String link) {
        try {
            URL url = new URL(link);
            Path path = Paths.get(link);
            if (Files.exists(path.getFileName())) {
                System.out.println("File " + path.getFileName() + " already exist! Won't be downloaded");
                return;
            }
            int fileNum;
            synchronized (Downloader.class) {
                fileNum = fileNumber++;
            }
            System.out.println(Thread.currentThread().getName() + " start download file number " + fileNum);
            InputStream input = url.openStream();
            Files.copy(input, path.getFileName());
            System.out.println(Thread.currentThread().getName() + " finish download file number " + fileNum);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
