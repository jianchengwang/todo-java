package todo.java.geekbang.designpattern.behavioral.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/8/1
 */
public class SensitiveWordFilterChain {
    private List<ISensitiveWordFilter> filters = new ArrayList<>();

    public void addFilter(ISensitiveWordFilter filter) {
        this.filters.add(filter);
    }

    // return true if content doesn't contain sensitive words.
    public boolean filter(Content content) {
        for (ISensitiveWordFilter filter : filters) {
            if (!filter.doFilter(content)) {
                return false;
            }
        }
        return true;
    }
}
