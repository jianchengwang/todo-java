package todo.java.geekbang.designpattern.behavioral.snapshot;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class SnapshotDemo {
    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotHolder = new SnapshotHolder();

        inputText.append("hello");
        inputText.append(" world");

        snapshotHolder.pushSnapshot(inputText.createSnapshot());

        inputText.append("!");

        System.out.println(inputText.getText());

        inputText.restoreSnapshot(snapshotHolder.popSnapshot());

        System.out.println(inputText.getText());
    }
}
