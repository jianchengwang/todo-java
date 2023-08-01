package todo.java.geekbang.designpattern.behavioral.state;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public interface IMario {
    MarioState getName();
    void obtainMushRoom(MarioStateMachine stateMachine);
    void obtainCape(MarioStateMachine stateMachine);
    void obtainFireFlower(MarioStateMachine stateMachine);
    void meetMonster(MarioStateMachine stateMachine);
}
