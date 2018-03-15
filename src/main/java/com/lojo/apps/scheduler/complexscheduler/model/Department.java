package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Department {
    Integer id;
    Map<JobRole,Set<Employee>> employees = new HashMap<>();
    Set<Schedule> schedules = new HashSet<>();
    String departmentName;
    String companyName;
    String departmentDescription;

    public Department(Integer id, String departmentName) {
        this.id=id;
        this.departmentName=departmentName;
    }



}
