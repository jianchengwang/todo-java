package todo.java.geekbang.designpattern.structural.flyweight;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class TreeImpl implements Tree {
    private String type;

    public TreeImpl(String type) {
        this.type = type;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Tree type: " + type + ", Position: (" + x + ", " + y + "), Pointer: " + this);
    }
}
