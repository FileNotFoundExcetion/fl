package com.example.demo.controller;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessFixController {

    @Autowired
    private RepositoryService repositoryService;


    //在 ACT_RE_PROCDEF 找到id 传入
    @PostMapping("/fix-listeners")
    public String fixProcessDefinition(@RequestParam String processDefinitionId) {
        // 1. 获取流程定义模型
        BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
        
        // 2. 修改所有任务监听器
        model.getProcesses().forEach(process -> {
            process.getFlowElements().forEach(element -> {
                if (element instanceof UserTask) {
                    UserTask userTask = (UserTask) element;
                    userTask.getTaskListeners().forEach(listener -> {
                        if ("pers.zhang.listener.ManagerTaskHandler".equals(listener.getImplementation())) {
                            listener.setImplementation("com.example.demo.handler.ManagerTaskHandler");
                        }
                    });
                }
            });
        });

        // 3. 重新部署
        Deployment deployment = repositoryService.createDeployment()
            .addBpmnModel("fixed.bpmn", model)
            .deploy();

        return "修正成功，新部署ID: " + deployment.getId();
    }
}