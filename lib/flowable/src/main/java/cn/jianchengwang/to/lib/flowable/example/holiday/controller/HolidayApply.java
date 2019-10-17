package cn.jianchengwang.to.lib.flowable.example.holiday.controller;

import cn.jianchengwang.to.lib.flowable.example.holiday.Holiday;
import cn.jianchengwang.to.lib.flowable.example.holiday.ProcessTool;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wjc on 2019/10/17
 **/
public class HolidayApply {

    private static Scanner scanner= new Scanner(System.in);

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessTool.buildProcessEngineThenDeploy();
        while (true) {
            System.out.println("begin apply holiday? y->enter; n->quite");
            boolean enter = scanner.nextLine().toLowerCase().equals("y");
            if(enter) {
                Holiday holiday = buildHoliday();
                Map<String, Object> variables = new HashMap<String, Object>();
                variables.put("employee", holiday.getEmployee());
                variables.put("nrOfHolidays", holiday.getNrOfHolidays());
                variables.put("description", holiday.getDescription());

                // 创建工作流实例
                ProcessInstance processInstance = ProcessTool.buildProcessInstance(processEngine, "holidayRequest", variables);
            } else {
                System.exit(0);
            }
        }
    }

    public static Holiday buildHoliday() {
        System.out.println("Who are you?");
        String employee = scanner.nextLine();
        System.out.println("How many holidays do you want to request?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());
        System.out.println("Why do you need them?");
        String description = scanner.nextLine();

        return Holiday.builder()
                .employee(employee)
                .nrOfHolidays(nrOfHolidays)
                .description(description).build();
    }
}
