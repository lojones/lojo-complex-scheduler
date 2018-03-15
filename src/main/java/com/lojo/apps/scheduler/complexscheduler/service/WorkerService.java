package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Employee;
import com.lojo.apps.scheduler.complexscheduler.model.JobRole;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WorkerService {

    Set<Employee> employees = new HashSet<>();

    public void addEmployee(@NonNull String name, @NonNull String jobRole) {
        Employee employee = new Employee(name, jobRole);
        employees.add(employee);
    }

}
