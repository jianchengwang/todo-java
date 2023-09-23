package d6.stacks;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author jianchengwang
 * @date 2023/9/20
 */
public class SymbolMatchMain {
    public static void main(String[] args) throws IOException {
        ArrayList<Byte> leftList = new ArrayList() {
            {
                add((byte) '(');
                add((byte) '[');
                add((byte) '{');
            }
        };
        byte[] buf = new byte[128];
        int length = System.in.read(buf) - 1;
        MyStack<Byte> myStack = new MyStack<>(length);
        for(int i=0; i<length; i++) {
            if(leftList.contains(buf[i])) {
                myStack.push(buf[i]);
            } else {
                byte left = myStack.peek();
                if(left == '(' && buf[i] == ')') {
                    myStack.pop();
                } else if(left == '[' && buf[i] == ']') {
                    myStack.pop();
                } else if(left == '{' && buf[i] == '}') {
                    myStack.pop();
                } else {
                    System.out.println("not match error");
                    return;
                }
            }
        }
    }
}
