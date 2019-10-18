package cn.jianchengwang.todo.lib.flowable.example.springboot.web;

import cn.jianchengwang.todo.lib.flowable.example.entity.Holiday;
import cn.jianchengwang.todo.lib.flowable.example.simple.ProcessTool;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wjc on 2019/10/18
 **/
@RestController
@RequestMapping("holiday")
public class HolidayController {

    private final String PROCESS_ID = "holidayRequest";

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 请假申请
     * @param holiday
     * @return
     */
    @GetMapping("request")
    private String request(@Valid Holiday holiday) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", holiday.getEmployee());
        variables.put("nrOfHolidays", holiday.getNrOfHolidays());
        variables.put("description", holiday.getDescription());

        // 创建工作流实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_ID, variables);
        return "提交成功.流程Id为：" + processInstance.getId();
    }

    /**
     * 处理请假流程
     * @param taskId
     * @return
     */
    @RequestMapping(value = "deal")
    public String deal(@NotEmpty String taskId, @NotNull Boolean approved, String rejectReason) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("approved", approved);
        if(!approved) {
            map.put("rejectReason", rejectReason);
        }
        taskService.complete(taskId, map);
        return "processed ok!";
    }

}
