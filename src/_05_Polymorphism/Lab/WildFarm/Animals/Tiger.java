package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;
import _05_Polymorphism.Lab.WildFarm.Food.Food;
import _05_Polymorphism.Lab.WildFarm.Food.Meat;

public class Tiger extends Felime {

    public Tiger(String name, double weight, String region) {
        super(name, weight, region, AnimalType.Tiger);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public boolean willEatFood(Food food) {
        return food instanceof Meat;
    }

}
