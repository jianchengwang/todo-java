package cn.jianchengwang.todo.lib.flowable.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by wjc on 2019/10/17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Holiday {
    @NotEmpty
    private String employee;
    @Min(0)
    private Integer nrOfHolidays;
    @NotEmpty
    private String description;
}
