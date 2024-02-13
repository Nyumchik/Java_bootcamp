package day00.ex04;

import java.util.Scanner;

public class Program {

    public static void printResult(char[] symbols, int[] maxArray){
        float level = (float) 10 / maxArray[0];
        int[] table = new int[10];
        for (int j = 0; j < 10; j++) {
            table[j] = (int) (level * maxArray[j]);
        }

        for (int i = 10 + 1; i >= -1; i--) {
            for (int j = 0; j < 10; j++) {
                if (maxArray[j] == 0) {
                    break ;
                }
                if (i == table[j]) {
                    System.out.printf("%4d", maxArray[j]);
                }
                else if (i < 0) {
                    System.out.printf("%4c", symbols[j]);
                }
                else if (i < table[j]) {
                    System.out.printf("%4c", '#');
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[]symbolsArray = scanner.nextLine().toCharArray();
        char[]array = new char[65535 + 1];

        for (char c : symbolsArray) {
            if (array[c] < 999) {
                array[c]++;
            }
        }
        char[] maxArraySymbol = new char[10];
        int[] maxArray = new int[10];

        for (int i = 0; i <= 65535; i++) {
            if (array[i] > 0) {
                for (int j = 0; j < 10; j++) {
                    if (array[i] > maxArray[j]) {
                        for (int k = 10 - 1; k > j; k--) {
                            maxArray[k] = maxArray[k - 1];
                            maxArraySymbol[k] = maxArraySymbol[k - 1];
                        }
                        maxArray[j] = array[i];
                        maxArraySymbol[j] = (char) i;
                        break;
                    }
                }
            }
        }

        if (maxArray[0] == 0) {
            scanner.close();
            System.exit(0);
        }
        printResult(maxArraySymbol, maxArray);
    }
}
