package todo.java.geekbang.designpattern.behavioral.command;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class GotDiamondCommand implements ICommand {

    public GotDiamondCommand() {
    }

    @Override
    public void execute() {
        System.out.println("Got Diamond");
    }
}
