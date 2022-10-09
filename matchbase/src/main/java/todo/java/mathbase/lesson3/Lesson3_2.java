package todo.java.mathbase.lesson3;

/**
 * @author jianchengwang
 * @date 2022/10/9
 */
public class Lesson3_2 {

    /**
     * @Description: 计算大于1的正整数之平方根
     * @param n-待求的数, deltaThreshold-误差的阈值, maxTry-二分查找的最大次数
     * @return double-平方根的解
     */
    public static double getSqureRoot(int n, double deltaThreshold, int maxTry) {

        if (n <= 1) {
            return -1.0;
        }

        double min = 1.0, max = (double)n;
        for (int i = 0; i < maxTry; i++) {
            double middle = (min + max) / 2;
            double square = middle * middle;
            double delta = Math.abs((square / n) - 1);
            if (delta <= deltaThreshold) {
                return middle;
            } else {
                if (square > n) {
                    max = middle;
                } else {
                    min = middle;
                }
            }
        }

        return -2.0;

    }


    public static void main(String[] args) {

        int number = 10;
        double squareRoot = Lesson3_2.getSqureRoot(number, 0.000001, 10000);
        if (squareRoot == -1.0) {
            System.out.println("请输入大于1的整数");
        } else if (squareRoot == -2.0) {
            System.out.println("未能找到解");
        } else {
            System.out.println(String.format("%d的平方根是%f", number, squareRoot));
        }

    }
}


import java.util.Arrays;

public class Lesson3_3 {

    /**
     * @Description: 查找某个单词是否在字典里出现
     * @param dictionary-排序后的字典, wordToFind-待查的单词
     * @return boolean-是否发现待查的单词
     */
    public static boolean search(String[] dictionary, String wordToFind) {

        if (dictionary == null) {
            return false;
        }

        if (dictionary.length == 0) {
            return false;
        }

        int left = 0, right = dictionary.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (dictionary[middle].equals(wordToFind)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(wordToFind) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return false;

    }

    /**
     * @Description: 查找某个单词是否在字典里出现
     * @param dictionary-排序后的字典, wordToFind-待查的单词
     * @return boolean-是否发现待查的单词
     */
    public static boolean search2(String[] dictionary, String wordToFind) {

        if (dictionary == null) {
            return false;
        }

        if (dictionary.length == 0) {
            return false;
        }

        int left = 0, right = dictionary.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (dictionary[middle].equals(wordToFind)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(wordToFind) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return false;

    }

}



