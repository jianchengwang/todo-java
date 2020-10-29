package todo.lib.flowable.example.simple;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * Created by wjc on 2019/10/17
 **/
public class ProcessTool {

    public static ProcessEngine buildProcessEngineThenDeploy() {
        ProcessEngine processEngine = buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 发布工作流
        Deployment deployment = buildDeployment(repositoryService);
        return processEngine;
    }

    public static ProcessEngine buildProcessEngine() {
        // 配置创建工作流
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://120.27.100.228:3306/flowable-test")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();

        return processEngine;
    }

    public static Deployment buildDeployment(RepositoryService repositoryService) {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/holiday-request.bpmn20.xml")
                .deploy();

        // 简单查询实例
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        return deployment;

    }

    public static ProcessInstance buildProcessInstance(ProcessEngine processEngine, String processId, Map<String, Object> variables) {
        // RuntimeService 启动流程实例
        // When a process instance is started,
        // there will be one database transaction from the start of the process instance to the next wait state
        // 每一个任务都是事务安全的
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(processId, variables);

        return processInstance;

    }
}
