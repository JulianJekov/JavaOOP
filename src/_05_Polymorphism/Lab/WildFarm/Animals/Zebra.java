package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;
import _05_Polymorphism.Lab.WildFarm.Food.Food;
import _05_Polymorphism.Lab.WildFarm.Food.Vegetable;

public class Zebra extends Mammal {

    public Zebra(String name, double weight, String region) {
        super(name, weight, region, AnimalType.Zebra);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public boolean willEatFood(Food food) {
        return food instanceof Vegetable;
    }

}
