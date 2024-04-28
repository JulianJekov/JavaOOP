package _01_WorkingWithAbstraction.Lab.PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] rectangleCoordinates = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int commandCount = Integer.parseInt(scanner.nextLine());
        Point bottomLeft = new Point(rectangleCoordinates[0], rectangleCoordinates[1]);
        Point topRight = new Point(rectangleCoordinates[2], rectangleCoordinates[3]);

        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        for (int i = 0; i < commandCount; i++) {
            int[] pointCoordinates = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Point currentPoint = new Point(pointCoordinates[0], pointCoordinates[1]);
            System.out.println(rectangle.contains(currentPoint));
        }

    }
}

