package d6.stacks;

import java.util.ArrayList;

/**
 * @author jianchengwang
 * @date 2023/9/20
 */
public class MyStack<T> {
    private ArrayList<T> stack;

    public MyStack(int size) {
        this.stack = new ArrayList<>(size);
    }

    public void push(T t) {
        stack.add(t);
    }

    public T pop() {
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
