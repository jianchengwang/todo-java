package todo.java.algo.algoexpert.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表求和，一个中括号表示乘以1
 * easy
 * link: https://www.algoexpert.io/questions/Product%20Sum
 *
 * simple input
 * array = [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
 *
 * simple output
 * 12 // calculated as: 5 + 2 + 2 * (7 - 1) + 3 + 2 * (6 + 3 * (-13 + 8) + 4)
 *
 */
public class ProductSum {
    public static void main(String[] args) {

    }

    // O(n) time | O(d) space
    public static int solution(List<Object> array) {
        return productSumHelper(array, 1);
    }

    private static int productSumHelper(List<Object> array, int multi) {
        int sum = 0;
        for (Object obj: array) {
            if(obj instanceof ArrayList) {
                List<Object> ls = (ArrayList<Object>) obj;
                sum += productSumHelper(ls, multi + 1);
            } else {
                sum += (int) obj;
            }
        }
        return sum * multi;
    }

}
