package _01_WorkingWithAbstraction.Lab.HotelReservation;

public class PriceCalculator {

    public static double calculateFinalPrice(double pricePerDay, int numberOfDays, Season season, Discount discount) {

        return (pricePerDay * numberOfDays) * season.getMultiplier() * ((100 - discount.getDiscount()) / 100.0);
    }

}
