package _05_Polymorphism.Lab.Shapes;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Rectangle(1.1,1.1);
        Shape shape1 = new Circle(5.5);
        System.out.println(shape.calculateArea());
        System.out.println(shape.calculatePerimeter());
    }
}
