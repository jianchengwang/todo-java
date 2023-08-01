package todo.java.geekbang.designpattern.behavioral.state;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class SmallMario implements IMario {

    private static final SmallMario instance = new SmallMario();
    private SmallMario() {}

    public static SmallMario getInstance() {
        return instance;
    }

    @Override
    public MarioState getName() {
        return MarioState.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine stateMachine) {
//        stateMachine.setCurrentState(SuperMario.getInstance());
//        stateMachine.setScore(stateMachine.getScore() + 100);
    }

    @Override
    public void obtainCape(MarioStateMachine stateMachine) {
//        stateMachine.setCurrentState(CapeMario.getInstance());
//        stateMachine.setScore(stateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine stateMachine) {
//        stateMachine.setCurrentState(FireMario.getInstance());
//        stateMachine.setScore(stateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine stateMachine) {

    }
}
