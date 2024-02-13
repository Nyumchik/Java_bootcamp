package day00.ex01;

import java.util.Scanner;

public class Program
{
    public static void main (String[] argc){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();
        boolean status = true;
        int i = 1;
        if (num <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        for (i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                status = false;
                System.out.println(status + " " + (i - 1));
                System.exit(0);
            }
        }
        System.out.println(status + " " + (i - 1));
        System.exit(0);
    }
}