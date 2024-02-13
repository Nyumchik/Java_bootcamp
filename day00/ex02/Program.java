package day00.ex02;

import java.util.Scanner;

public class Program {
    public static void main (String[] argc){

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int sum = 0;
        int cups = 0;
        boolean status = true;

        while(num != 42) {
            status = true;
            sum = 0;
            if (num < 2) {
                System.err.print("IllegalArgument");
                scanner.close();
                System.exit(-1);
            }
            while(num != 0) {
                sum += (num % 10);
                num /= 10;
            }
            for (int i = 2; i * i <= sum; i++) {
                if (sum % i == 0) {
                    status = false;
                    break;
                }
            }
            if (status)
                cups++;
            num = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Count of coffee-request - " + cups);
    }
}
