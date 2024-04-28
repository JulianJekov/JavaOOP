package _05_Polymorphism.Lab.WildFarm.Food;

public abstract class Food {
   private int quantity;

    protected Food(Integer quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
