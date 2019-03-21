package cn.jianchengwang.todo.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Please note that it is not a true conversion. It’s just collecting the elements from the stream into a collection or array.
public class ConvertStream2ListOrArray {

    public static void main(String[] args) {

        // Convert Stream to List – Stream.collect( Collectors.toList() )
        List<Integer> list1 = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++){
            list1.add(i);
        }
        Stream<Integer> stream1 = list1.stream();
        List<Integer> evenNumbersList = stream1.filter(i -> i%2 == 0).collect(Collectors.toList());
        System.out.print(evenNumbersList);


        // Convert Stream to array – Stream.toArray( EntryType[]::new )
        List<Integer> list2 = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++){
            list2.add(i);
        }
        Stream<Integer> stream2 = list2.stream();
        Integer[] evenNumbersArr = stream2.filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
    }

}
