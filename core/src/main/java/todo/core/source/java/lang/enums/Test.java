package todo.core.source.java.lang.enums;

public class Test {
    public static void main(String[] args) {
        System.out.println(Color.Red);
        for (Color value : Color.values()) {
            System.out.println(value);
        }
    }
}
