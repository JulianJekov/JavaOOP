package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;
import _05_Polymorphism.Lab.WildFarm.Food.Food;
import _05_Polymorphism.Lab.WildFarm.Food.Vegetable;


public class Mouse extends Mammal {


    public Mouse(String name, double weight, String region) {
        super(name, weight, region, AnimalType.Mouse);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public boolean willEatFood(Food food) {
        return food instanceof Vegetable;
    }

}
