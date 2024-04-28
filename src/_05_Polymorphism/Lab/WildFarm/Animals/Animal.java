package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;
import _05_Polymorphism.Lab.WildFarm.Food.Food;

public abstract class Animal {
    private String name;
    private AnimalType type;
    private double weight;
    private int foodEaten;

    public Animal(String name, double weight, AnimalType type) {
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.foodEaten = 0;
    }

    protected String getName() {
        return name;
    }

    protected AnimalType getType() {
        return type;
    }

    protected double getWeight() {
        return weight;
    }

    protected int getFoodEaten() {
        return foodEaten;
    }

    public abstract void makeSound();
    public abstract boolean willEatFood(Food food);
    public void eat(Food food){
        if(!willEatFood(food)) {
            System.out.println(this.type != AnimalType.Mouse ? this.type + "s are not eating that type of food!" :
                    "Mice are not eating that type of food!");

            return;

        }
        this.foodEaten += food.getQuantity();
    }

}
