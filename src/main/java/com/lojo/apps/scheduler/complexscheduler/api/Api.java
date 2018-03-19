package com.lojo.apps.scheduler.complexscheduler.api;

import com.lojo.apps.scheduler.complexscheduler.model.Department;
import com.lojo.apps.scheduler.complexscheduler.model.GenericResponse;
import com.lojo.apps.scheduler.complexscheduler.model.exception.DuplicateException;
import com.lojo.apps.scheduler.complexscheduler.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "api")
public class Api {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "health")
    public String health() {
        return "Up!";
    }

    @RequestMapping(value = "user")
    public String whoami() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @RequestMapping(value = "departments")
    public Map<Integer,Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @RequestMapping(value = "department", method = RequestMethod.PUT)
    public GenericResponse addDepartment(@RequestBody Map department) {
        log.info("Add this department {}",department);
        try {
            departmentService.createNewDepartment(department.get("departmentName").toString(),department.get("departmentDesc").toString(),department.get("companyName").toString());
        } catch (DuplicateException e) {
            return GenericResponse.builder().isError(true).details(e.getMessage()).summary("Duplicate error").build();
        }
        return GenericResponse.builder().isError(false).build();
    }

    @RequestMapping(value = "department/{id}", method = RequestMethod.GET)
    public Department getDepartment(@PathVariable Integer id) {
        Department department= departmentService.getDepartments().get(id);
        log.info("Returning {}",department);
        return department;
    }
}
