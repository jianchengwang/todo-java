package todo.java.algo.algoexpert.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 给定一个学生成绩数组，无序的，老师需要给学生发放奖励，
 * 规则：
 * 1.所有学生都能拿到奖励
 * 2.相邻的学生，成绩高的奖励多
 * 计算老师发放奖励总数最少是多少
 * hard
 * link: https://www.algoexpert.io/questions/Min%20Rewards
 *
 * simple input
 * scores = [8, 4, 2, 1, 3, 6, 7, 9, 5]
 *
 * simple output
 * 25 // you would give out the following rewards: [4, 3, 2, 1, 2, 3, 4, 5, 1]
 *
 */
public class MinRewards {
    public static void main(String[] args) {
        int[] scores = new int[]{8, 4, 2, 1, 3, 6, 7, 9, 5};
        System.out.println(solution(scores));
    }

    // O(n) time | O(n) space
    public static int solution(int[] scores) {
        int[] rewards = new int[scores.length];
        Arrays.fill(rewards, 1);
        for(int i=1; i<scores.length; i++) {
            if(scores[i] > scores[i-1]) {
                rewards[i] = rewards[i-1] + 1;
            }
        }
        Arrays.stream(rewards).forEach(System.out::println);
        for(int i=scores.length-2; i>=0; i--) {
            if(scores[i] > scores[i+1]) {
                rewards[i] = Math.max(rewards[i], rewards[i+1] + 1);
            }
        }
        Arrays.stream(rewards).forEach(System.out::println);
        return IntStream.of(rewards).sum();
    }
}
