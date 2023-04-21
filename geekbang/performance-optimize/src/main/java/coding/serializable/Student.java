package coding.serializable;


import java.io.Serializable;

/**
 * @author jianchengwang
 * @date 2023/4/18
 */
public class Student implements Serializable {
    private Integer height;
    private String sex;

    public Student() {
    }

    public Student(Integer height, String sex) {
        this.height = height;
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
