package _05_Polymorphism.Lab.WildFarm.Animals;

import _05_Polymorphism.Lab.WildFarm.Enums.AnimalType;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {

    private String livingRegion;

    public Mammal(String name, double weight, String region, AnimalType type) {
        super(name, weight, type);
        this.livingRegion = region;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        String formattedWeight = df.format(this.getWeight());
        return String.format("%s[%s, %s, %s, %d]"
                , this.getType(), this.getName(), formattedWeight, this.livingRegion, this.getFoodEaten());
    }
}
