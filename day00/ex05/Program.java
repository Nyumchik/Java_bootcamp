package day00.ex05;

import java.util.Scanner;

public class Program {
    public static void main(String[] argc) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int inputNum;

        String[] studentNames = new String[10];
        int numOfStudents = 0;
        do {
            input = scanner.nextLine();
            if (input.length() > 10) {
                System.err.println("IllegalArgument");
                scanner.close();
                System.exit(-1);
            }
            if (input.equals(".")) {
                break ;
            }
            studentNames[numOfStudents++] = input;
            if (numOfStudents > 10) {
                System.err.println("IllegalArgument");
                scanner.close();
                System.exit(-1);
            }
        } while (true);

        int[] classTime = new int[10];
        String[] classDays = new String[10];
        int countOfDays = 0;
        do {
            if (scanner.hasNextInt()) {
                inputNum = scanner.nextInt();
                if (inputNum < 1 || inputNum > 6) {
                    System.err.println("IllegalArgument");
                    scanner.close();
                    System.exit(-1);
                }
                classTime[countOfDays] = inputNum;
            }
            input = scanner.nextLine();
            if (input.equals(".")) {
                break ;
            }
            char[] array = input.toCharArray();
            classDays[countOfDays++] = new String(array, 1, 2);
        } while (true);

        String[] daysOfWeek = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        int[] numOfMonth = new int[10];
        int[] scheduleIndex = new int[10];
        int countClasses = 0;
        for (int i = 1; i <= 30; i++) {
            for (int k = 1; k <= 6; k++) {
                for (int j = 0; j < countOfDays; j++) {
                    if (classDays[j].equals(daysOfWeek[i % 7]) && classTime[j] == k) {
                        scheduleIndex[countClasses] = j;
                        numOfMonth[countClasses++] = i;
                    }
                }
            }
        }

        int[][] attendance = new int[numOfStudents][countClasses];
        do {
            input = scanner.nextLine();
            if (input.equals(".")) {
                break ;
            }

            char[] array = input.toCharArray();
            String[] str = new String[4];
            int start = 0, len = 0, index = 0;
            for (char x : array) {
                if (x == ' ') {
                    str[index++] = new String(array, start, len);
                    start += len + 1;
                    len = 0;
                }
                else {
                    len++;
                }
            }
            str[index] = new String(array, start, len);

            array = str[1].toCharArray();
            int time = array[0] - '0';

            int day = 0;
            array = str[2].toCharArray();
            for (char x : array) {
                day += x - '0';
                day *= 10;
            }
            day /= 10;

            for (int i = 0; i < numOfStudents; i++) {
                for (int j = 0; j < countClasses; j++) {
                    if (numOfMonth[j] == day && classTime[scheduleIndex[j]] == time
                            && studentNames[i].equals(str[0])) {
                                if (str[3].equals("HERE")) {
                                    attendance[i][j] = 1;
                                }
                                else {
                                    attendance[i][j] = -1;
                                }
                                break ;
                            }
                }
            }
        } while(true);
        scanner.close();

        for (int j = -1; j < numOfStudents; j++) {
            if (j < 0) {
                System.out.printf("%10s", "");
            }
            else {
                System.out.printf("%10s", studentNames[j]);
            }

            for (int i = 0; i < countClasses; i++) {
                if (j < 0) {
                    System.out.printf("%1d:00%3s%3d|", classTime[scheduleIndex[i]],
                        classDays[scheduleIndex[i]], numOfMonth[i]);
                }
                else if (attendance[j][i] != 0) {
                    System.out.printf("%10d|", attendance[j][i]);
                }
                else {
                    System.out.printf("%10s|", "");
                }
            }
            System.out.println();
        }
    }
}
