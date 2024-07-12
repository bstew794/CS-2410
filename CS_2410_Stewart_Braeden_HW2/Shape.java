package CS_2410_Stewart_Braeden_HW2;

public class Shape {
    private double side;

    public Shape(double sideLength){
        setSide(sideLength);
    }
    public double getSide() {
        return side;
    }
    public void setSide(double sideLength){
        side = sideLength;
    }
    public double getArea(){
        return 0;
    }
}
class Square extends Shape{
    private double area;

    public Square(double sideLength){
        super(sideLength);
        setArea();
    }
    public void setArea(){
        area = getSide() * getSide();
    }
    public double getArea(){
        return area;
    }
}
class Triangle extends Shape{
    private double area;

    public Triangle(double sideLength){
        super(sideLength);
        setArea();
    }
    public void setArea(){
        area = (getSide() * getSide()) / 2;
    }
    public double getArea(){
        return area;
    }
}
class Cube extends Shape{
    private double area;

    public Cube(double sideLength){
        super(sideLength);
        setArea();
    }
    public void setArea(){
        area = 6 * (getSide() * getSide());
    }
    public double getArea(){
        return area;
    }
}
class Pyramid extends Shape{
    private double area;

    public Pyramid(double sideLength){
        super(sideLength);
        setArea();
    }
    public void setArea(){
        area = 4 * ((getSide() * getSide()) / 2);
    }
    public double getArea(){
        return area;
    }
}