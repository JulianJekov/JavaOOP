package _05_Polymorphism.Lab.Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    public Double getPerimeter() {
        return perimeter;
    }

    protected double setPerimeter(Double perimeter) {
       return this.perimeter = perimeter;

    }

    public Double getArea() {
        return area;
    }

    protected double setArea(Double area) {
      return this.area = area;
    }

    protected abstract double calculatePerimeter();
    protected abstract double calculateArea();
}
