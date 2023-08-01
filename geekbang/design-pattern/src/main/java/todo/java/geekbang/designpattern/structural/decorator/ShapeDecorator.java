package todo.java.geekbang.designpattern.structural.decorator;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw(){
        decoratedShape.draw();
    }
}
