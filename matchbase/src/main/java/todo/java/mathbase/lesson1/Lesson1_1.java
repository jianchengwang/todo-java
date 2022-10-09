package todo.java.mathbase.lesson1;

import java.math.BigInteger;

/**
 * @author jianchengwang
 * @date 2022/10/9
 */
public class Lesson1_1 {

    /**
     * 十进制转换成二进制
     * @param decimalSource
     * @return
     */
    public static String decimalToBinary(int decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource)); // 转换成BigInteger，默认是十进制
        return bi.toString(2); // 转为二进制
    }

    /**
     * 二进制转换成十进制
     * @param binarySource
     * @return
     */
    public static int binaryToDecimal(String binarySource) {
        BigInteger bi = new BigInteger(binarySource, 2);
        return Integer.parseInt(bi.toString());
    }

    public static void main(String[] args) {
        int a = 53;
        String b = "110101";
        System.out.println(String.format("数字%d的二进制是%s", a, Lesson1_1.decimalToBinary(a))); //获取十进制数53的二进制数
        System.out.println(String.format("数字%s的十进制是%d", b, Lesson1_1.binaryToDecimal(b))); //获取二进制数110101的十进制数
    }

}
