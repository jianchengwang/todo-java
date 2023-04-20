package design.prototype;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
public class Student implements Cloneable{
    private String name;
    private Teacher teacher;
    public String getName() { return name; }
    public void setName(String name) { this.name= name; }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    //重写clone方法
    public Student clone() {
        Student student = null;
        try {
            student = (Student) super.clone();
            Teacher teacher = this.teacher.clone(); // deep copy
            student.setTeacher(teacher);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }
}
