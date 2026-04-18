package practice_3.task_4_2;

public class Rectangle extends Shape{
    protected double width;
    protected double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() { return width * height; }

    @Override
    public double perimeter() { return 2 * (width + height); }

    @Override
    public void draw() {
        System.out.printf("Drawing %s rectangle %.1f x %.1f%n", color, width, height);
    }
}
