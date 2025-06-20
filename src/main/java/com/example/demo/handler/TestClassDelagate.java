package com.example.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Date 2025/6/20 14:31
 */
@Slf4j
@Component
public class TestClassDelagate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
      log.info("id:{}", execution.getId());
      log.info("processDefinitionId:{}", execution.getProcessDefinitionId());
      log.info("businessStatus:{}", execution.getProcessInstanceBusinessStatus());
        execution.setVariable("day", 20);
    }
}
