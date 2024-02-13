package ex00;

public class Hen implements Runnable {
    private int count;
    public Hen(int i) {
        this.count = i;
    }
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
        }
    }
}
