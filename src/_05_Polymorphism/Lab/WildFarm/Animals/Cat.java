package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;
import _05_Polymorphism.Lab.WildFarm.Food.Food;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String name, double weight, String region, String breed) {
        super(name, weight, region, AnimalType.Cat);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public boolean willEatFood(Food food) {
        return true;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        String formattedWeight = df.format(this.getWeight());
        return String.format("%s[%s, %s, %s, %s, %d]"
                , this.getType(), this.getName(),this.breed, formattedWeight, this.getLivingRegion(), this.getFoodEaten());
    }
}
