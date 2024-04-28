package _05_Polymorphism.Lab.Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.setRadius(radius);
        this.calculatePerimeter();
        this.calculateArea();
    }

    @Override
    protected double calculatePerimeter() {
       return setPerimeter(2 * Math.PI * radius);

    }

    @Override
    protected double calculateArea() {
      return setArea(Math.PI * radius * radius);

    }

    public final Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
