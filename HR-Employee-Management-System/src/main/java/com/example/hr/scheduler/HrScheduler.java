package com.example.hr.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HrScheduler {

    private static final Logger log = LoggerFactory.getLogger(HrScheduler.class);

    @Scheduled(fixedDelayString = "#{@hrSchedulerProperties.employeeReportDelay}")
    public void generateEmployeeReport() {
        log.info("HR Employee report scheduler executed.");
    }
}