package core.concurrency.design.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */

public class SafeDateFormat {
    //定义ThreadLocal变量
    static final ThreadLocal<DateFormat>
            tl=ThreadLocal.withInitial(
            ()-> new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss"));

    static DateFormat get(){
        return tl.get();
    }
}
