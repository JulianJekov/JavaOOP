package _04_InterfacesAndAbstraction.Lab.CarShop;

public interface Car {
    Integer TIRES = 4;

    String getModel();
    String getColor();
    Integer getHorsePower();
    String countryProduced();
}
