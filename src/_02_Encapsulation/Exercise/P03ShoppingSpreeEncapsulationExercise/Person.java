package _02_Encapsulation.Exercise.P03ShoppingSpreeEncapsulationExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return name;
    }

    private boolean enoughMoney(Product product){
        return this.money >= product.getCost();
    }

    public void buyProduct(Product product) {
        if(!enoughMoney(product)){
            throw new IllegalArgumentException(String.format
                    ("%s can't afford %s",this.name,product.getName()));
        }
        products.add(product);
        this.money -= product.getCost();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append(" - ");
        String productData = products.stream().map(Product::getName).collect(Collectors.joining(", "));
        if(productData.isEmpty()){
            sb.append("Nothing bought");
        }else {
            sb.append(productData);
        }
        return sb.toString();
    }
}
