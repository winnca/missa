package practice_3.task_4_2;

public class Square extends Rectangle{
    public Square(String color, double side){
        super(color, side, side); // Передача в конструктор Rectangle
    }
    @Override
    public void draw(){
        System.out.println("Square");
    }
}
