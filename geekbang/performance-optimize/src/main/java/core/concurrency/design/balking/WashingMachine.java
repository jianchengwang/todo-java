package core.concurrency.design.balking;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class WashingMachine {

    private boolean isOpen;

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public void wash() {
        if (!isOpen) {
            throw new IllegalStateException("The washing machine is not open");
        }
        // Do the washing
    }
}

