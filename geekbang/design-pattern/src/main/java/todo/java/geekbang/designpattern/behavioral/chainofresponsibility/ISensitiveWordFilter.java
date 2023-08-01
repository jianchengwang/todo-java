package todo.java.geekbang.designpattern.behavioral.chainofresponsibility;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public interface ISensitiveWordFilter {
    boolean doFilter(Content content);
}
