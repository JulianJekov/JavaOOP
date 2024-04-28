package _01_WorkingWithAbstraction.Lab;

import java.util.Scanner;

public class RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        printTopPart(size);
        printBottomPart(size);

    }

    private static void printBottomPart(int size) {
        for (int i = size - 1; i >= 1 ; i--) {
            printRow(size, i);
        }
    }

    private static void printTopPart(int size) {
        for (int i = 1; i <= size; i++) {
            printRow(size, i);
        }
    }

    private static void printRow(int size, int i) {
        for (int j = 1; j <= size - i; j++) {
            System.out.print(" ");
        }
        for (int j = 1; j <= i; j++) {
            System.out.print("* ");
        }
        System.out.println();
    }
}
