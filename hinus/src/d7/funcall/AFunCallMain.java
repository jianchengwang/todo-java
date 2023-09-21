package d7.funcall;

public class AFunCallMain {
    public static void main(String[] args) {
        A a = new A(1);
        A b = new A(2);
        swap(a, b);
        System.out.println("a's value is " + a.value +", b's value is " + b.value);
    }

    public static void swap(A a, A b) {
        int t = a.value;
        a.value = b.value;
        b.value = t;
    }
}
