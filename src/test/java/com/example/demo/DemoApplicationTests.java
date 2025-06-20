package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("day", 5);
        map.put("studentName", "zhangsan");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("wpgj_common_v3", map);
        log.info("流程实例ID:" + processInstance.getId());
    }
   //7839c2e9-4da1-11f0-9a57-782b46680fdd

    @Test
    void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("auditStatus",2);
        taskService.complete("8faed3f3-4da4-11f0-9464-782b46680fdd",map);
    }
}
