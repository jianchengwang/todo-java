package cn.jianchengwang.todo.lib.netty.todo.web.server.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Action {
    String value() default "";
    String method() default "GET";
}
