package todo.core.java8.streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args) {

        // Stream.of(val1, val2, val3….)
        Stream<Integer> stream1 = Stream.of(1,2,3,4,5,6,7,8,9);
        stream1.forEach(p -> System.out.println(p));

        // Stream.of(arrayOfElements)
        Stream<Integer> stream2 = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
        stream2.forEach(p -> System.out.println(p));

        // List.stream()
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++){
            list.add(i);
        }
        Stream<Integer> stream3 = list.stream();
        stream3.forEach(p -> System.out.println(p));

        // String chars or String tokens
        IntStream stream5 = "12345_abcdefg".chars();
        stream5.forEach(p -> System.out.println(p));
        //OR
        Stream<String> stream6 = Stream.of("A$B$C".split("\\$"));
        stream6.forEach(p -> System.out.println(p));

        // file
        try(Stream lines = Files.lines(Paths.get("/home/wjc/ext/workspace/IdeaProjects/todo-java/java8/README.md"), Charset.defaultCharset())){
            //可对lines做一些操作
            lines.forEach(p -> System.out.println(p));
        }catch(IOException e){
            e.printStackTrace();
        }

        // 创建无限流，一般要加limit进行限制
        // Stream.generate() or Stream.iterate()
        Stream<Date> stream4 = Stream.generate(() -> { return new Date(); }).limit(10);
        stream4.forEach(p -> System.out.println(p));
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

    }
}
