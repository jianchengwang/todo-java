package todo.java.geekbang.designpattern.behavioral.chainofresponsibility;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class SexyWordFilter implements ISensitiveWordFilter {

    @Override
    public boolean doFilter(Content content) {
        System.out.println("SexyWordFilter");
        boolean legal = true;
        //...
        return legal;
    }
}
