package cn.jianchengwang.todo.core.java8.growing.jdk6;

public class Console {

    public static void main(String[] args) {

        // 在ide 里面调用获取的console为null,真实命令行可以取值
        java.io.Console console = System.console();

        if (console != null) {
            String user = new String(console.readLine(" Enter User: ", new Object[0]));
            String pwd  = new String(console.readPassword(" Enter Password: ", new Object[0]));
            console.printf(" User name is:%s ", new Object[]{user});
            console.printf(" Password is:%s ", new Object[]{pwd});
        } else {
            System.out.println(" No Console! ");
        }
    }

}
