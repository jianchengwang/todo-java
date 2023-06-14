package todo.java.geekbang.designpattern.creational.prototype;

import lombok.Data;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
@Data
public class SearchWord {
    private String keyword;
    private int count;
    private long lastUpdateTime;

    public SearchWord() {
    }

    public SearchWord(String keyword, int count, long lastUpdateTime) {
        this.keyword = keyword;
        this.count = count;
        this.lastUpdateTime = lastUpdateTime;
    }
}
