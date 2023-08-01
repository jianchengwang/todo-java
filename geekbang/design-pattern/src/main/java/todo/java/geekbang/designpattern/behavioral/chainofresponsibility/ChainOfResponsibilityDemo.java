package todo.java.geekbang.designpattern.behavioral.chainofresponsibility;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        Content content = new Content();

        SensitiveWordFilterChain filterChain = new SensitiveWordFilterChain();
        filterChain.addFilter(new PoliticalWordFilter());
        filterChain.addFilter(new SexyWordFilter());
        filterChain.addFilter(new AdsWordFilter());

        boolean legal = filterChain.filter(content);
        if (!legal) {
            // reject
        } else {
            // pass
        }
    }
}
