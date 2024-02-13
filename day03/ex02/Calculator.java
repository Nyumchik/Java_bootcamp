package ex02;

public class Calculator extends Thread {
    private int last;
    private int first;
    private int[] array;
    private int result = 0;
    private int id;

    public Calculator(int last, int first, int[] array) {
        this.last = last;
        this.first = first;
        this.array = array;
        this.id = IdGenerator.getInstance().generateId();
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        int tmp = first;
        while (tmp != last){
            result += array[tmp];
            tmp++;
        }
        System.out.println(this.getName().substring(0, 6) + " " + id + ": from " + first + " to " + (last - 1) + " sum is " + result);
    }
}

