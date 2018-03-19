package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Department;
import com.lojo.apps.scheduler.complexscheduler.model.Employee;
import com.lojo.apps.scheduler.complexscheduler.model.Schedule;
import com.lojo.apps.scheduler.complexscheduler.model.exception.DuplicateException;
import com.lojo.apps.scheduler.complexscheduler.model.exception.NotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
@Getter
public class DepartmentService {

    HashMap<Integer, Department> departments=new HashMap();

    @PostConstruct
    public void mockData() {
        try {
            this.createNewDepartment("Emergency","For 24 hour emergency care serving the greater Brampton area", "Brampton Civic Hospital");
            this.createNewDepartment("Call Center","Bell Helpdesk", "Bell Canada Inc.");
        } catch (DuplicateException e) {
            e.printStackTrace();
        }
    }

    public void createNewDepartment(String departmentName, String departmentDescription, String companyName) throws DuplicateException {

        synchronized (departments) {
            Set<Integer> keys = departments.keySet();
            Integer newId=1;
            if (keys.size()>0) {
                Integer maxId=Collections.max(keys);
                newId=maxId+1;
            }

            createNewDepartment(newId,departmentName,departmentDescription,companyName);
        }

    }

    public void createNewDepartment(Integer id, String departmentName, String departmentDescription, String companyName) throws DuplicateException {

        Department department=new Department(id, departmentName);

        if (departments.get(id) != null) {
            throw new DuplicateException("That department id already exists");
        }

        department.setDepartmentDescription(departmentDescription);
        department.setCompanyName(companyName);

        departments.put(id, department);

    }

    public void addEmployee(Integer departmentId, Employee employee) {

        try {
            Set<Employee> employees = departments.get(departmentId).getEmployees().get(employee.getJobRole());
            employees.add(employee);
        } catch (Exception e) {
            log.error("Error trying to add employee {}",e.getMessage());
            e.printStackTrace();
        }

    }

    public void addSchedule(Integer departmentId, Schedule schedule) throws NotFoundException, DuplicateException {
        try {
            departments.get(departmentId).getSchedules().add(schedule);
        } catch (Exception e) {
            log.error("Error trying to add schedule {}",e.getMessage());
            e.printStackTrace();
        }

    }




}
