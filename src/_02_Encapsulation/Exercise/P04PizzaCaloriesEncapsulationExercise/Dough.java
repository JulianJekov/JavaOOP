package _02_Encapsulation.Exercise.P04PizzaCaloriesEncapsulationExercise;

import java.util.Arrays;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        boolean flourTypeExist = Arrays.stream(FlourTypes.values()).anyMatch(t -> t.name().equals(flourType));
        if (flourTypeExist) {
            this.flourType = FlourTypes.valueOf(flourType).toString();
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        boolean bakingTechniqueExist = Arrays.stream(BakingTechniques.values()).anyMatch(t -> t.name().equals(bakingTechnique));
        if (bakingTechniqueExist) {
            this.bakingTechnique = BakingTechniques.valueOf(bakingTechnique).toString();
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return 2 * weight * getBakingTechniqueModifier() * getFlourTypeModifier();
    }

    private double getBakingTechniqueModifier() {

     return BakingTechniques.valueOf(bakingTechnique).getModifier();
    }

    private double getFlourTypeModifier() {

        return FlourTypes.valueOf(flourType).getModifier();
    }
}
