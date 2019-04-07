package cn.jianchengwang.todo.core.java8.streams;

import cn.jianchengwang.todo.core.java8.streams.example.Project;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamCollection {

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

        // Convert -> Map
        // name:stars
        List<Project> projects = Project.buildData();
        Map<String, Integer> collect1 = projects.stream()
                .collect(toMap(Project::getName, Project::getStars));
        System.out.println(collect1);

        // name
        Map<String, Project> collect2 = projects.stream()
                .collect(toMap(Project::getName, Function.identity()));
        System.out.println(collect2);

        // 根据作者名分组，然后根据编程语言做前后端分离
        Map<String, List<Project>> collect3 = projects.stream()
                .collect(groupingBy(Project::getAuthor));
        System.out.println(collect3);

        Map<String, Map<String, Long>> collect4 = projects.stream()
                .collect(groupingBy(Project::getAuthor,
                        Collectors.groupingBy(p -> {
                            if ("java".equalsIgnoreCase(p.getLanguage()) ||
                                    "python".equalsIgnoreCase(p.getLanguage())) {
                                return "后端";
                            }
                            return "前端";
                        }, counting())
                ));
        System.out.println(collect4);

        // /**
        // * 数据分区
        // * <p>
        // * Collectors.partitioningBy
        // * <p>
        // * 根据前后端将项目分为两组
        // *
        Map<Boolean, List<Project>> collect5 = projects.stream()
                .collect(partitioningBy(StreamCollection::isBackEnd));
        System.out.println(collect5);

        /**
         * 转换类型
         * <p>
         * Collectors.toCollection
         * <p>
         * Collectors.collectingAndThen
         * <p>
         * Collectors.maxBy
         * <p>
         * Collectors.minBy
         * <p>
         * 按照作者名称筛选出每组star最高的项目
         *
         */
        Collection<Project> collect6 = projects.stream()
                .collect(toCollection(CopyOnWriteArrayList::new));
        System.out.println(collect6);

        Map<String, Project> collect7 = projects.stream()
                .collect(groupingBy(Project::getAuthor, collectingAndThen(
                        maxBy(Comparator.comparingInt(Project::getStars)),
                        Optional::get
                )));
        System.out.println(collect7);
    }

    public static boolean isBackEnd(Project project){
        return "java".equalsIgnoreCase(project.getLanguage()) || "python".equalsIgnoreCase(project.getLanguage());
    }
}
