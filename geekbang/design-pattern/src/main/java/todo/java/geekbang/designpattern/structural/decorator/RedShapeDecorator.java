package todo.java.geekbang.designpattern.structural.decorator;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        addRedBorder(decoratedShape);
    }

    private void addRedBorder(Shape decoratedShape){
        System.out.println("Adding red border.");
    }
}
