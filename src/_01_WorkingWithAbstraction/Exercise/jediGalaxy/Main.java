package _01_WorkingWithAbstraction.Exercise.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readPosition(scanner);
        int rows = dimensions[0];
        int cols = dimensions[1];

        Field field = new Field(rows, cols);
        Galaxy galaxy = new Galaxy(field);

        String command = scanner.nextLine();
        long starsCollected = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] jediPositions = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilPositions = readPosition(scanner);

            int rowEil = evilPositions[0];
            int colEvil = evilPositions[1];

            galaxy.moveEvil(rowEil, colEvil);

            int jediRow = jediPositions[0];
            int jediCol = jediPositions[1];

            starsCollected += galaxy.moveJedi(jediRow, jediCol);
            command = scanner.nextLine();
        }
        System.out.println(starsCollected);

    }

    private static int[] readPosition(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }


}
