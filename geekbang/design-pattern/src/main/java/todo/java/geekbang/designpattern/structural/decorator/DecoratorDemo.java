package todo.java.geekbang.designpattern.structural.decorator;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());

        circle.draw();      // 输出: Drawing a circle.
        redCircle.draw();   // 输出: Drawing a circle. Adding red border.
    }
}
