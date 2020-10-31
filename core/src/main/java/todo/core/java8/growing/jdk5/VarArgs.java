package todo.core.java8.growing.jdk5;

import java.util.Arrays;
import java.util.List;

public class VarArgs {

    public static List<String> asList(String[] names){
        return Arrays.asList(names);
    }

    public static void main(String[] args) {
        List<String> hello = Arrays.asList("王爵nice", "Gay冰", "A*熊");

    }
}
