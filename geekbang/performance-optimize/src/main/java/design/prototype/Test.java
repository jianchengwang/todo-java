package design.prototype;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public class Test {

    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setName("test1");
        Student stu2 = stu1;
        stu2.setName("test2");
        Student stu3 = stu1.clone(); // clone create stu3
        stu3.setName("test3");
        System.out.println("stu1:" + stu1.getName());
        System.out.println("stu2:" + stu2.getName());
        System.out.println("stu3:" + stu3.getName());
    }
}
