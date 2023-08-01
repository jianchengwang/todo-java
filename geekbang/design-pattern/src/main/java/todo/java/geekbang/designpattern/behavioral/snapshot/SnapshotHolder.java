package todo.java.geekbang.designpattern.behavioral.snapshot;

import java.util.Stack;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class SnapshotHolder {
    private Stack<Snapshot> snapshots = new Stack<>();
    public Snapshot popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(Snapshot snapshot) {
        snapshots.push(snapshot);
    }
}
