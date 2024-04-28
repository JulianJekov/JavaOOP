package _05_Polymorphism.Lab.WildFarm;

import _05_Polymorphism.Lab.WildFarm.Animals.*;
import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;
import _05_Polymorphism.Lab.WildFarm.Food.Food;
import _05_Polymorphism.Lab.WildFarm.Enums.FoodType;
import _05_Polymorphism.Lab.WildFarm.Food.Meat;
import _05_Polymorphism.Lab.WildFarm.Food.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();
        while (!input.equals("End")) {

            String[] animalData = input.split("\\s+");
            Animal animal = createAnimal(animalData);

            String[] foodData = scanner.nextLine().split("\\s+");
            Food food = createFood(foodData);

            animal.makeSound();
            animal.eat(food);

            animals.add(animal);

            input = scanner.nextLine();
        }
        animals.forEach(System.out::println);
    }

    private static Food createFood(String[] foodData) {
        FoodType type = FoodType.valueOf(foodData[0]);
        Food currentFood = null;
        int quantity = Integer.parseInt(foodData[1]);
        switch (type) {
            case Meat:
                currentFood = new Meat(quantity);
                break;
            case Vegetable:
                currentFood = new Vegetable(quantity);
                break;
        }
        return currentFood;
    }

    private static Animal createAnimal(String[] animalData) {
        AnimalType type = AnimalType.valueOf(animalData[0]);
        Animal currentAnimal = null;
        double weight = Double.parseDouble(animalData[2]);
        switch (type) {
            case Cat:
                currentAnimal = new Cat(animalData[1], weight, animalData[3], animalData[4]);
                break;
            case Tiger:
                currentAnimal = new Tiger(animalData[1], weight, animalData[3]);
                break;
            case Zebra:
                currentAnimal = new Zebra(animalData[1], weight, animalData[3]);
                break;
            case Mouse:
                currentAnimal = new Mouse(animalData[1], weight, animalData[3]);
                break;
        }
        return currentAnimal;
    }
}