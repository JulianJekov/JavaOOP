package _07_ExceptionsAndErrorHandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03EnterNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startIndex = 1;
        int endIndex = 100;
        List<Integer> correctNumbers = new ArrayList<>();
        while (correctNumbers.size() < 10) {
            int number;
            try {
                number = readNumber(scanner, startIndex, endIndex);
                startIndex = number;
                correctNumbers.add(number);
            } catch (IllegalStateException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(correctNumbers
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    private static int readNumber(Scanner scanner, int startIndex, int endIndex) {
        String input = scanner.nextLine();
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Invalid Number!");
        }
        if (number <= startIndex || number >= endIndex) {
            throw new IllegalStateException(String.format("Your number is not in range %d - 100!", startIndex));
        }
        return number;
    }
}
