package ex01;

public class Egg implements Runnable {
    private int count;
    public Egg(int i) {
        this.count = i;
    }
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            Program.printHen();
        }
    }
}

