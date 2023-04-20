package design.prototype;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */
class Teacher implements Cloneable{
    private String name;
    public String getName() {
        return name;
     }
     public void setName(String name) {
        this.name= name;
    }
    public Teacher clone() {
        Teacher teacher= null;
        try {
            teacher= (Teacher) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return teacher;
    }
}
