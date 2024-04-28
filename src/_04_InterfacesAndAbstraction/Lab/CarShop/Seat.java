package _04_InterfacesAndAbstraction.Lab.CarShop;

public class Seat extends BaseCar implements Sellable{
    private Double price;
    public Seat(String model, String color, Integer horsePower, String countryProduced,Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    public String toString() {
        return super.toString() + String.format("%s sells for %f",getModel(),this.price);
    }
}
