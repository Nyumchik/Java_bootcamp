package day00.ex00;

public class Program 
{
	public static void main(String[] args) {
		int number = 479598;
		int num = number % 10;
		int num2 = number / 10 % 10;
		int num3 = number / 100 % 10;
		int num4 = number / 1000 % 10;
		int num5 = number / 10000 % 10;
		int num6 = number / 100000;
		int result = num + num2 + num3 + num4 + num5 + num6;
		System.out.println(result);
	}
}