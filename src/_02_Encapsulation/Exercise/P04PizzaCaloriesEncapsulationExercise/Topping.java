package _02_Encapsulation.Exercise.P04PizzaCaloriesEncapsulationExercise;

import java.util.Arrays;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);

    }

    private void setToppingType(String toppingType) {
        boolean toppingExists = Arrays.stream(ToppingTypes.values()).anyMatch(t -> t.name().equals(toppingType));
        if(toppingExists) {
            this.toppingType = ToppingTypes.valueOf(toppingType).toString();
        }else {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", toppingType));
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return 2 * weight * getToppingModifier();
    }

    private double getToppingModifier() {
        return ToppingTypes.valueOf(toppingType).getModifire();
    }
}
