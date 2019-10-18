package cn.jianchengwang.todo.lib.flowable.example.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * Created by wjc on 2019/10/17
 **/
public class SendRejectionMail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("SendRejectionMail for employee "
                + execution.getVariable("employee") + " -> "
                + "rejectReason:" + execution.getVariable("rejectReason"));
    }
}
