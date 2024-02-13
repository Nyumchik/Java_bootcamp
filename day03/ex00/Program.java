package ex00;

public class Program {
    public static void main(String[] args) throws InterruptedException {

        if(args.length != 1) {
            System.exit(-1);
        }

        int repeatThread = 0;

        if (args[0].startsWith("--count=")) {
            try {
                Integer.parseInt(args[0].substring(8));
            } catch (NumberFormatException e) {
                System.out.println("Error args");
                System.exit(-1);
            }
            repeatThread = Integer.parseInt(args[0].substring(8));
            if (repeatThread < 0) {
                System.out.println("Error args");
                System.exit(-1);
            }
        }
        else {
            System.out.println("Error args");
            System.exit(-1);
        }

        Egg eggRunnable = new Egg(repeatThread);
        Hen henRunnable = new Hen(repeatThread);
        Thread eggThread = new Thread(eggRunnable);
        Thread henThread = new Thread(henRunnable);
        eggThread.start();
        henThread.start();
        try {
            eggThread.join();
            henThread.join();
        } 
        catch (InterruptedException interruptedException) {
                System.out.println("Interrupted tread");
        }
        
        for (int i = 0; i < repeatThread; ++i) {
            System.out.println("Human");
        }

    }
}