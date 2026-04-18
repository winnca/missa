package practice_3.task_4_2;

import java.util.*;

public class ShapeTest {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle("red", 5));
        shapes.add(new Rectangle("blue", 4, 6));
        shapes.add(new Square("green", 5));
        shapes.add(new Circle("yellow", 3));

        System.out.println("=== All Shapes ===");
        for (Shape s : shapes) {
            s.draw();
            System.out.println("  " + s);
        }

        System.out.println("\n=== Sort Area ===");
        shapes.sort(Shape::compareArea);
        shapes.forEach(System.out::println);

        System.out.println("\n=== Only Circle ===");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {
                System.out.printf("Circle with radius: check by toString: %s%n", c);
            }
        }

        double totalArea = shapes.stream().mapToDouble(Shape::area).sum();
        System.out.printf("%n public area: %.2f%n", totalArea);
    }
}
