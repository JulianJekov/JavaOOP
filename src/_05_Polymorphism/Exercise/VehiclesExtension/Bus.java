package _05_Polymorphism.Exercise.VehiclesExtension;

public class Bus extends Vehicle{
    private static final double  AC_ADDITIONAL_CONSUMPTION = 1.4;
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, AC_ADDITIONAL_CONSUMPTION);
    }

}
