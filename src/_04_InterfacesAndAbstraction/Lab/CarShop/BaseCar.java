package _04_InterfacesAndAbstraction.Lab.CarShop;

public class BaseCar implements Car{
    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduced;

    public BaseCar(String model, String color, Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires%n",getModel(),countryProduced(),Car.TIRES);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.countryProduced;
    }
}
