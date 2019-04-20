package cn.jianchengwang.todo.core.nio.example;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {

    public static void main(String[] args) {

        try {

            // create the Path instance
            Path file = Paths.get(PathExample.class.getResource("/data.txt").getPath());

            Path currentDir = Paths.get(".");
            System.out.println(currentDir.toAbsolutePath());

            currentDir.normalize();
            System.out.println(currentDir.toAbsolutePath());



        } catch (Exception e) {

            e.printStackTrace();
        }



    }
}
