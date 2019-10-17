package cn.jianchengwang.to.lib.flowable.example.holiday;

import lombok.Builder;
import lombok.Data;

/**
 * Created by wjc on 2019/10/17
 **/
@Data
@Builder
public class Holiday {
    private String employee;
    private Integer nrOfHolidays;
    private String description;
}
