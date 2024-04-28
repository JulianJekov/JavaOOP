package _07_ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P01NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbersRange = scanner.nextLine().split("\\s+");
        int startIndex = Integer.parseInt(numbersRange[0]);
        int endIndex = Integer.parseInt(numbersRange[1]);

        System.out.printf("Range: [%d...%d]%n", startIndex, endIndex);

        int number = readNumberInRange(scanner, startIndex, endIndex);
        System.out.println("Valid number: " + number);

    }

    private static int readNumberInRange(Scanner scanner, int startIndex, int endIndex) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= startIndex && number <= endIndex) {
                    return number;
                }
            } catch (NumberFormatException e) {

            }
            System.out.println("Invalid number: " + input);
        }
    }
}
