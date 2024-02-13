package ex01;

public class Program {
    static Object key = new Object();
    public static boolean flag = false;
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

    }

    public static synchronized void printEgg() {
        if (flag) {
            try {
                Program.class.wait();
            }
            catch (InterruptedException interruptedException) {
                System.out.println("Interrupted tread");
            }
        }
        System.out.println("Egg");;
        flag = true;
        Program.class.notify();
    }

    public static synchronized void printHen() {
        if (flag == false) {
            try {
                Program.class.wait();
            } catch (InterruptedException interruptedException) {
                System.out.println("Interrupted tread");
            }
        }
        System.out.println("Hen");
        flag = false;
        Program.class.notify();
    }
}
