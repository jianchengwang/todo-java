package todo.java.geekbang.designpattern.structural.composite;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class Employee extends HumanResource {
    public Employee(long id, double salary) {
        super(id);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}
