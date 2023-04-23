package core.collection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * 这还得从“ArrayList 是基于数组实现“开始说起，由于 ArrayList 的数组是基于动态扩增的，所以并不是所有被分配的内存空间都存储了数据。
 * 如果采用外部序列化法实现数组的序列化，会序列化整个数组。
 * ArrayList 为了避免这些没有存储数据的内存空间被序列化，内部提供了两个私有方法 writeObject 以及 readObject 来自我完成序列化与反序列化，
 * 从而在序列化与反序列化数组时节省了空间和时间。因此使用 transient 修饰数组，是防止对象数组被其他外部方法序列化。
 * @author jianchengwang
 * @date 2023/4/17
 */
public class TestList {

    /**
     *  * 这还得从“ArrayList 是基于数组实现“开始说起，由于 ArrayList 的数组是基于动态扩增的，所以并不是所有被分配的内存空间都存储了数据。
     *  * 如果采用外部序列化法实现数组的序列化，会序列化整个数组。
     *  * ArrayList 为了避免这些没有存储数据的内存空间被序列化，内部提供了两个私有方法 writeObject 以及 readObject 来自我完成序列化与反序列化，
     *  * 从而在序列化与反序列化数组时节省了空间和时间。因此使用 transient 修饰数组，是防止对象数组被其他外部方法序列化。
     */
    public void test1() {
        ArrayList list = new ArrayList();
        list.add(1);
    }

    public void test2() {
        LinkedList list = new LinkedList();
        list.add(1);
    }

    public static void main(String[] args) {

    }
}
