package cn.jianchengwang.todo.java8.growing.jdk7;

import cn.jianchengwang.todo.java8.growing.jdk5.EnumDemo;

public class SwitchString {

    public static void main(String[] args) {
        String bis = "java";
        switch (bis) {
            case "java":
                break;
            case "python":
                break;
            case "ruby":
                break;
            default:
                break;
        }

        EnumDemo enumDemo = EnumDemo.GREEN;

        switch (enumDemo) {
            case RED:
                break;
            case YELLOW:
                break;
            default:
                break;
        }

    }
}
