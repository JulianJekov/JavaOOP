package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;

public abstract class Felime extends Mammal {

    public Felime(String name, double weight, String region, AnimalType type) {
        super(name, weight, region, type);
    }
}
