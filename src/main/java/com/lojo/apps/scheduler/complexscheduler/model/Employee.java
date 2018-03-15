package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.Data;

@Data
public class Employee {
    String name;
    JobRole jobRole;

    public Employee(String name, String jobRole) {
        JobRole j=new JobRole(jobRole);
        this.name=name;
        this.jobRole=j;
    }
}
