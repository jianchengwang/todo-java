package jvm;

/**
 *
 * 方法区不是堆的一部分，方法区和堆存在交集。方法区的静态变量和运行时常量池存放在堆中，但类的元信息等还是存放在了本地内存中。
 * @author jianchengwang
 * @date 2023/4/19
 */

public class JVMCase {

    // 常量
    public final static String MAN_SEX_TYPE = "man";

    // 静态变量
    public static String WOMAN_SEX_TYPE = "woman";

    public static void main(String[] args) {

        Student stu = new Student();
        stu.setName("nick");
        stu.setSexType(MAN_SEX_TYPE);
        stu.setAge(20);

        JVMCase jvmcase = new JVMCase();

        // 调用静态方法
        print(stu);
        // 调用非静态方法
        jvmcase.sayHello(stu);
    }


    // 常规静态方法
    public static void print(Student stu) {
        System.out.println("name: " + stu.getName() + "; sex:" + stu.getSexType() + "; age:" + stu.getAge());
    }


    // 非静态方法
    public void sayHello(Student stu) {
        System.out.println(stu.getName() + "say: hello");
    }
}

class Student{
    String name;
    String sexType;
    int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSexType() {
        return sexType;
    }
    public void setSexType(String sexType) {
        this.sexType = sexType;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
