package _01_WorkingWithAbstraction.Lab.HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] line = scanner.nextLine().split(" ");

        double pricePerDay = Double.parseDouble(line[0]);
        int numberOfDays = Integer.parseInt(line[1]);
        Season season = Season.valueOf(line[2]);
        Discount discount = Discount.valueOf(line[3]);
        double price = PriceCalculator.calculateFinalPrice(pricePerDay, numberOfDays, season, discount);
        System.out.printf("%.2f", price);

    }
}
