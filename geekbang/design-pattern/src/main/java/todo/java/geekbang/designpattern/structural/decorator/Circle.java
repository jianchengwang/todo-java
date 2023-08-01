package todo.java.geekbang.designpattern.structural.decorator;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
