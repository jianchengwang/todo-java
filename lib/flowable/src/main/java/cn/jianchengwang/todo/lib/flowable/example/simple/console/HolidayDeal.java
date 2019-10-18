package cn.jianchengwang.todo.lib.flowable.example.simple.console;

import cn.jianchengwang.todo.lib.flowable.example.simple.ProcessTool;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wjc on 2019/10/17
 **/
public class HolidayDeal {

    private static Scanner scanner= new Scanner(System.in);

    public static void main(String[] args) {

        // 创建发布工作流引擎
        ProcessEngine processEngine = ProcessTool.buildProcessEngineThenDeploy();
        ProcessInstance processInstance = ProcessTool.buildProcessInstance(processEngine, "holidayRequest", null);

        while (true) {
            System.out.println("holiday deal system:1->deal holiday task; 2.show task detail; other quite system");
            String opt = scanner.nextLine();
            if(opt.equals("1")) {
                TaskService taskService = processEngine.getTaskService();
                // TaskService -> TaskQuery 查询指定委托组的任务 candidateGroups="managers"
                List<Task> taskList = getAssigneeTaskList(taskService, processEngine, "managers");
                // 处理任务
                dealTaskList(taskService, taskList);
            } else if(opt.equals("2")) {
                // 显示历史记录
                showHistoryData(processEngine, processInstance);
            } else {
                System.exit(0);
            }
        }

    }

    public static List<Task> getAssigneeTaskList(TaskService taskService, ProcessEngine processEngine, String assignee) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }
        return tasks;
    }

    public static void dealTaskList(TaskService taskService, List<Task> taskList) {
        System.out.println("Which task would you like to complete?");
        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = taskList.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");

        boolean approved = scanner.nextLine().toLowerCase().equals("y");
        Map<String,Object> variables = new HashMap<String, Object>();
        variables.put("approved", approved);
        if(!approved) {
            System.out.println("Why your employee " + processVariables.get("employee") + " reject holiday request?");
            String rejectReason = scanner.nextLine();
            variables.put("rejectReason", rejectReason);
        }

        taskService.complete(task.getId(), variables);
    }

    private static void showHistoryData(ProcessEngine processEngine, ProcessInstance processInstance) {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();

        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
}
