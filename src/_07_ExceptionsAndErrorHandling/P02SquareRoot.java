package _07_ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P02SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        try {
            int number = Integer.parseInt(input);
            System.out.printf("%.2f%n", sqrt(number));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid");
        }
        System.out.println("Goodbye");
    }

    public static double sqrt(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Invalid");
        }
        return Math.sqrt(number);
    }
}
