package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.Data;
import org.joda.time.Instant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class ShiftInstance {
    Instant start;
    Instant end;
    Map<JobRole,Integer> numSlots = new HashMap<>();
    Map<JobRole,Set<Employee>> booked = new HashMap<>();

}
