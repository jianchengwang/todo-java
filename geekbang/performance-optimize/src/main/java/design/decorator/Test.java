package design.decorator;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public class Test {

    public static void main( String[] args )
    {
        IDecorator decorator = new Decorator();
        IDecorator curtainDecorator = new CurtainDecorator(decorator);
        curtainDecorator.decorate();

    }
}
