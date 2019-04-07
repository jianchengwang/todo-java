package cn.jianchengwang.todo.core.currency.example.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author sss
 * @date 2019-02-20
 */
@Data
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;
}
