package todo.java.bigdata.flink.demo.java.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wjc
 * @date 2020/11/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Integer userId;
    private Integer score;
}
