package cn.jianchengwang.todo.java8.growing.jdk7;

import cn.jianchengwang.todo.java8.growing.jdk6.ScriptEngineDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResource {

    public static void main(String[] args) {
        String path = ScriptEngineDemo.class.getResource("/test.js").getPath();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str = br.readLine();
            while (null != str) {
                System.out.println(str);
                str = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
