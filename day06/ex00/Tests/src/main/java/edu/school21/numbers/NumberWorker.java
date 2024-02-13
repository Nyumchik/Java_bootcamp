package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        int i = 2;

        if (number < 2) {
            throw new IllegalNumberException();
        } else if (number == 2) {
            return true;
        }

        while (i * i <= number) {
            if (number % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;

        number = Math.abs(number);
        if(number == -2147483648) {
            return 47;
        }
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    class IllegalNumberException extends RuntimeException {
        public IllegalNumberException() {
            super("IllegalArgument");
        }
    }
}