package coding;

import model.Singleton;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jianchengwang
 * @date 2023/4/18
 */
public class TestSerializable {
    @Test
    public void test1() {
        Set root = new HashSet();
        Set s1 = root;
        Set s2 = new HashSet();
        for (int i = 0; i < 100; i++) {
            Set t1 = new HashSet();
            Set t2 = new HashSet();
            t1.add("foo"); //使t2不等于t1
            s1.add(t1);
            s1.add(t2);
            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
    }

    @Test
    public void test2() throws IOException {
        Student student = new Student();
        student.setHeight(178);
        student.setSex("man");
        ByteArrayOutputStream os =new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(student);
        byte[] testByte = os.toByteArray();
        System.out.print("ObjectOutputStream 字节编码长度：" + testByte.length + "\n");

        ByteBuffer byteBuffer = ByteBuffer.allocate( 2048);
        byte[] userName = student.getHeight().toString().getBytes();
        byte[] password = student.getSex().getBytes();
        byteBuffer.putInt(userName.length);
        byteBuffer.put(userName);
        byteBuffer.putInt(password.length);
        byteBuffer.put(password);
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        System.out.print("ByteBuffer 字节编码长度：" + bytes.length+ "\n");

    }

    @Test
    public void test3() throws IOException {
        Student student = new Student();
        student.setHeight(178);
        student.setSex("man");
        long startTime = System.currentTimeMillis();
        for(int i=0; i<10000; i++) {
            ByteArrayOutputStream os =new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(student);
            out.flush();
            out.close();
            byte[] testByte = os.toByteArray();
            os.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.print("ObjectOutputStream 序列化时间：" + (endTime - startTime) + "\n");


        long startTime1 = System.currentTimeMillis();
        for(int i=0; i<10000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate( 2048);

            byte[] userName = student.getHeight().toString().getBytes();
            byte[] password = student.getSex().getBytes();
            byteBuffer.putInt(userName.length);
            byteBuffer.put(userName);
            byteBuffer.putInt(password.length);
            byteBuffer.put(password);

            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
        }
        long endTime1 = System.currentTimeMillis();
        System.out.print("ByteBuffer 序列化时间：" + (endTime1 - startTime1)+ "\n");
    }

    @Test
    public void test4() {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
    }
}
