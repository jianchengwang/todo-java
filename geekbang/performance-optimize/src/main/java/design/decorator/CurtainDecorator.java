package design.decorator;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */

/**
 * 窗帘装饰类
 * @author admin
 *
 */
public class CurtainDecorator extends BaseDecorator{

    public CurtainDecorator(IDecorator decorator) {
        super(decorator);
    }

    /**
     * 窗帘具体装饰方法
     */
    @Override
    public void decorate() {
        System.out.println("decorate curtain");
        super.decorate();
    }

}
