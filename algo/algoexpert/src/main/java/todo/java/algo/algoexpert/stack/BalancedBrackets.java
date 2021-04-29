package todo.java.algo.algoexpert.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 括号匹配
 * medium
 * link: https://www.algoexpert.io/questions/Balanced%20Brackets
 *
 * simple input
 * string = "([])(){}(())()()"
 *
 * simple output
 * true
 *
 */
public class BalancedBrackets {

    public static void main(String[] args) {
        String input = "([])(){}(())()()";
        System.out.println(solution(input));
    }

    public static boolean solution(String str) {

        final String openingBrackets = "([{";
        final String closingBrackets = ")]}";
        Map<Character, Character> matchBracketMap = new HashMap<>();
        matchBracketMap.put(')', '(');
        matchBracketMap.put(']', '[');
        matchBracketMap.put('}', '{');

        Stack<Character> stack = new Stack();
        for(int i=0; i<str.length(); i++) {
            char letter = str.charAt(i);
            if(openingBrackets.indexOf(letter) != -1) {
                stack.add(letter);
            } else if(closingBrackets.indexOf(letter) != -1) {
                if(stack.size() == 0) {
                    return false;
                }
                if(stack.peek() == matchBracketMap.get(letter)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
