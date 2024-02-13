package ex03;

public class MyThread extends Thread{

    @Override
    public void run() {
        String downloderLink;
        while ((downloderLink = Downloader.getNextLink()) != null) {
            Downloader.downloadFile(downloderLink);
        }
    }
}
