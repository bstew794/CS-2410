package CS_2410_Stewart_Braeden_HW2;

import java.util.ArrayList;

public class TestShape {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();

        Shape square = new Square(2);
        Shape square1 = new Square(8);
        Shape triangle = new Triangle(2);
        Shape triangle1 = new Triangle(8);
        Shape cube = new Cube(2);
        Shape cube1 = new Cube(8);
        Shape pyramid = new Pyramid(2);
        Shape pyramid1 = new Pyramid(8);

        shapes.add(square);
        shapes.add(square1);
        shapes.add(triangle);
        shapes.add(triangle1);
        shapes.add(cube);
        shapes.add(cube1);
        shapes.add(pyramid);
        shapes.add(pyramid1);

        String message = "";

        for (int i = 0; i < shapes.size(); i++){
            String title = "shape " + (i + 1) + " area : ";

            message += title + shapes.get(i).getArea() + "\n";
        }
        System.out.println(message);
    }
}
