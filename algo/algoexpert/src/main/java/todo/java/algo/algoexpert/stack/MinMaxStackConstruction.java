package todo.java.algo.algoexpert.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现简单的栈，并且可以在任意时刻返回最大跟最小值
 * medium
 * link: https://www.algoexpert.io/questions/Min%20Max%20Stack%20Construction
 *
 */
public class MinMaxStackConstruction {
    static class MinMaxStack {
        List<Map<String, Integer>> minMaxStack = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();

        public int peek() {
            return stack.get(stack.size() - 1);
        }

        public int pop() {
            minMaxStack.remove(minMaxStack.size() - 1);
            return stack.remove(stack.size() - 1);
        }

        public void push(Integer number) {
            Map<String, Integer> newMinMax = new HashMap<>();
            newMinMax.put("min", number);
            newMinMax.put("max", number);
            if(minMaxStack.size() > 0) {
                Map<String, Integer> lastMinMaxMap = minMaxStack.get(minMaxStack.size() - 1);
                newMinMax.replace("min", Math.min(lastMinMaxMap.get("min"), number));
                newMinMax.replace("max", Math.max(lastMinMaxMap.get("max"), number));
            }
            minMaxStack.add(newMinMax);
            stack.add(number);
        }

        public int getMin() {
            return minMaxStack.get(minMaxStack.size() - 1).get("min");
        }

        public int getMax() {
            return minMaxStack.get(minMaxStack.size() - 1).get("max");
        }
    }
}
