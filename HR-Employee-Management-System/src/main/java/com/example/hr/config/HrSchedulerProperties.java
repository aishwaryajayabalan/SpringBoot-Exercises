package com.example.hr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hr.scheduler")
public class HrSchedulerProperties {

    private long employeeReportDelay;

    public long getEmployeeReportDelay() {
        return employeeReportDelay;
    }

    public void setEmployeeReportDelay(long employeeReportDelay) {
        this.employeeReportDelay = employeeReportDelay;
    }
}
