package todo.java.geekbang.designpattern.behavioral.state;

public enum MarioState {
    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3);

    private int value;

    private MarioState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
