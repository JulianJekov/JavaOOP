package _02_Encapsulation.Exercise.P04PizzaCaloriesEncapsulationExercise;

public enum FlourTypes {
    White(1.5),Wholegrain(1.0);
    private double modifier;

    FlourTypes(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}
