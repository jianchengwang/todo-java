package core.concurrency.juc.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/4/26
 */
public class Test {
    public static void main(String[] args) {

        List list = Collections.
                synchronizedList(new ArrayList());
        // 锁住list
        synchronized (list) {
            Iterator i = list.iterator();
            while (i.hasNext())
                foo(i.next());
        }
    }

    public static void foo(Object o) {
        System.out.println(o);
    }
}
