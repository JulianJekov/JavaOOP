package _02_Encapsulation.Exercise.P04PizzaCaloriesEncapsulationExercise;

public enum ToppingTypes {
    Meat(1.2), Veggies(0.8), Cheese(1.1), Sauce(0.9);
    private double modifire;

    ToppingTypes(double modifire) {
        this.modifire = modifire;
    }

    public double getModifire() {
        return modifire;
    }
}
