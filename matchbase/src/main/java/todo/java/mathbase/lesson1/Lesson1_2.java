package todo.java.mathbase.lesson1;

/**
 * @author jianchengwang
 * @date 2022/10/9
 */
public class Lesson1_2 {

    public static int leftShift(int num, int shift) {
        return num << shift;
    }

    public static int rightShift(int num, int shift) {
        return num >>> shift;
    }

    public static int rightShift1(int num, int shift) {
        return num >> shift;
    }

    public static void main(String[] args) {
        int num = 53; int m = 1;
        System.out.println(String.format("数字%d的二进制向左移%d位是%d", num, m, Lesson1_2.leftShift(num, m))); //测试向左移位
        System.out.println(String.format("数字%d的二进制向右移%d位是%d", num, m, Lesson1_2.rightShift(num, m))); // 测试逻辑向右移位
        System.out.println();
        num = 53;
        m = 3;
        System.out.println(String.format("数字%d的二进制向左移%d位是%d", num, m, Lesson1_2.leftShift(num, m))); // 测试向左移位
        System.out.println(String.format("数字%d的二进制向右移%d位是%d", num, m, Lesson1_2.rightShift(num, m))); //测试逻辑向右移位
        System.out.println(String.format("数字%d的二进制向右移%d位是%d", num, m, Lesson1_2.rightShift1(num, m))); //测试算数向右移位
    }
}
