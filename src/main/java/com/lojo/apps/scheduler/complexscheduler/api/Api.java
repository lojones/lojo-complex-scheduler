package com.lojo.apps.scheduler.complexscheduler.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class Api {

    @RequestMapping(value = "health")
    public String health() {
        return "Up!";
    }
}
