package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.Data;
import org.joda.time.Instant;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class ShiftInstance {
    LocalDateTime start;
    LocalDateTime end;
    Map<JobRole,Integer> numSlots = new HashMap<>();
    Map<JobRole,Set<Employee>> booked = new HashMap<>();

}
