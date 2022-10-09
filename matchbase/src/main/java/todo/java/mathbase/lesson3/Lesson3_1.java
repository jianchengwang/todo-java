package todo.java.mathbase.lesson3;

/**
 * @author jianchengwang
 * @date 2022/10/9
 */
public class Lesson3_1 {

    public static long getNumberOfWheat(int grid) {
        long sum = 0; // 麦粒总数
        long numberOfWheatInGrid = 0; // 当前格子里麦粒的数量
        numberOfWheatInGrid = 1; // 第一个格子里麦粒的数量
        sum += numberOfWheatInGrid;
        for (int i = 2; i <= grid; i ++) {
            numberOfWheatInGrid *= 2; // 当前格子里麦粒的数量是前一格的2倍
            sum += numberOfWheatInGrid; // 累计麦粒总数
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getNumberOfWheat(10));
    }
}
