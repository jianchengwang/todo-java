package coding;

import lombok.Data;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author jianchengwang
 * @date 2023/4/18
 */
public class TestStream {

    private List<Student> studentsList;

    @BeforeEach
    public void init() {
        studentsList = List.of(
                new Student(168,"man"),
                new Student(159, "women")
        );
    }

    @Test
    public void test1() {

        Map<String, List<Student>> stuMap = new HashMap<>();
        for (Student stu: studentsList) {
            if (stu.getHeight() > 160) {
                if (stuMap.get(stu.getSex()) == null) {
                    List<Student> list = new ArrayList<Student>();
                    list.add(stu);
                    stuMap.put(stu.getSex(), list);
                } else {
                    stuMap.get(stu.getSex()).add(stu);
                }
            }
        }

    }

    @Test
    public void test2() {

        Map<String, List<Student>> stuMap = studentsList
                .parallelStream()
                .filter((Student s) -> s.getHeight() > 160)
                .collect(Collectors.groupingBy(Student ::getSex));
    }

    @Test
    public void test3() {


        List<String> names = Arrays.asList("张三", "李四", "王老五", "李三", "刘老四", "王小二", "张四", "张五六七");

        String maxLenStartWithZ = names.stream()
                .filter(name -> name.startsWith("张"))
                .mapToInt(String::length)
                .max()
                .toString();
    }

    @Test
    public void test4() {

//使用一个容器装载100个数字，通过Stream并行处理的方式将容器中为单数的数字转移到容器parallelList
        List<Integer> integerList= new ArrayList<Integer>();

        for (int i = 0; i <10000; i++) {
            integerList.add(i);
        }

//        List<Integer> parallelList = new ArrayList<>();
        List<Integer> parallelList = new CopyOnWriteArrayList<>();
        integerList.stream()
                .parallel()
                .filter(i->i%2==1)
                .forEach(i->parallelList.add(i));
        System.out.println(parallelList.size());
    }
}
