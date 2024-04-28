package _05_Polymorphism.Lab.Shapes;

public class Rectangle extends Shape{
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.setHeight(height);
        this.setWidth(width);
        this.calculatePerimeter();
        this.calculateArea();
    }
    @Override
    protected double calculatePerimeter(){
       return setPerimeter(this.height * 2 + this.width * 2);

    }
    @Override
    protected double calculateArea(){
       return setArea(this.height * this.width);

    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}
