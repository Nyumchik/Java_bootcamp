package ex02;

import java.util.Random;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.exit(-1);
        }

        if(!args[0].matches("--arraySize=\\d+") || !args[1].matches("--threadsCount=\\d+")) {
            System.err.println("Error arguments");
            System.exit(-1);
        }

        int arrLength = Integer.parseInt(args[0].substring(12));
        int numOfThreads = Integer.parseInt(args[1].substring(15));
        if (numOfThreads > arrLength) {
            System.err.println("Error: the number of threads must be less than the size of the array!\n");
            System.exit(-1);
        }
        if (numOfThreads <= 0 || arrLength > 2000000) {
            System.exit(-1);
        }

        int[] arrayInt = new int[arrLength];
        Random random = new Random();
        for (int i = 0; i < arrLength; i++) {
            arrayInt[i] = random.nextInt() % 1000;
        }

        int sum = 0;
        for (int i = 0; i < arrayInt.length; i++) {
            sum += arrayInt[i];
        }

        System.out.println("Sum: " + sum);

        int range;
        if (arrLength % numOfThreads != 0) {
            range = arrLength / numOfThreads + 1;
        } else {
            range = arrLength / numOfThreads;
        }

        Thread[] arrayThread = new Thread[numOfThreads];

        Calculator[] calc = new Calculator[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            if ((i + 1) * range > arrLength) {
                calc[i] = new Calculator(arrLength, i * range, arrayInt);
            } else {
                calc[i] = new Calculator((i + 1) * range, i * range, arrayInt);
            }
            arrayThread[i] = calc[i];
            arrayThread[i].start();
        }

        int result = 0;
        for (int i = 0; i < numOfThreads; i++) {
            try {
                arrayThread[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted tread");
            }
            result += calc[i].getResult();
        }
        System.out.println("Sum by threads: " + result);
    }
}
