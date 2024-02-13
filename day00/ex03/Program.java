package day00.ex03;

import java.util.Scanner;

public class Program {

    public static void printResult(long storage, int num_week) {
        long len;
        int i = 1;

        while (num_week >= 1) {
            System.out.print("Week ");
            System.out.print(i);
            System.out.print(" ");
            len = storage % 10;
            while (len > 0) {
                System.out.print("=");
                len--;
            }
            System.out.println(">");
            storage /= 10;
            num_week--;
            i++;
        }
    }
    
    public static void main(String[] args) {
        String week;
        int weekNumber = 0;
        int gradeMinimum;
        int gradeCurrent;
        int i;
        long storage = 0;
        int check = 1;
        long freeSpace;
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            i = 0;
            gradeMinimum = 9;
            freeSpace = 1;
            week = scanner.next();
            if (week.equals("42")) {
                printResult(storage, weekNumber);
                scanner.close();
                System.exit(0);
            }
            if (!week.equals("Week")) {
                System.err.println("Illegal Argument");
                scanner.close();
                System.exit(-1);
            }
            weekNumber = scanner.nextInt();
            if (weekNumber < 1 || weekNumber > 18 || weekNumber != check){
                System.err.println("Illegal Argument");
                scanner.close();
                System.exit(-1);
            }
            while (i < 5) {
                gradeCurrent = scanner.nextInt();
                if (gradeCurrent < 1 || gradeCurrent > 9) {
                    System.err.println("Illegal Argument");
                    scanner.close();
                    System.exit(-1);
                }
                if (gradeMinimum > gradeCurrent)
                    gradeMinimum = gradeCurrent;
                i++;
            }
            i = weekNumber;
            while (i > 1)
            {
                freeSpace *= 10;
                i--;
            }
            storage += freeSpace * gradeMinimum;
            check++;
        }
    }
}
