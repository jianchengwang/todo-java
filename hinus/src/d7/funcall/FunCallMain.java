package d7.funcall;

public class FunCallMain {
    public static void main(String[] args) {
//        int c = 1 + add(2, 3);
//        System.out.println(c);

//        int a = 1, b = 2;
//        swap(a, b);
//        System.out.println("a is " + a + ", b is " + b);

        int n = 10;
        int t = fact(n);
        System.out.println(t);

    }

    public static int add(int a, int b){
        return a + b;
    }

    public static void swap(int a, int b){
        int t = a;
        a = b;
        b = t;
    }

    public static int fact(int n){
        if (n == 0)
            return 1;
        else
            return n * fact(n - 1);
    }
}
