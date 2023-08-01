package todo.java.geekbang.designpattern.structural.composite;
/**
 * @author jianchengwang
 * @date 2023/6/15
 */

public abstract class HumanResource {
    protected long id;
    protected double salary;

    public HumanResource(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public abstract double calculateSalary();
}
