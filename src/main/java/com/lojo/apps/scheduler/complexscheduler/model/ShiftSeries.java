package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.Data;
import org.joda.time.*;

import java.util.HashMap;
import java.util.Map;

@Data
public class ShiftSeries {

    String name;

    Boolean doesRecur;
    Boolean instantiated;

    Integer shiftLengthHours;

    LocalDateTime start;
    LocalDateTime end;

    Map<JobRole,Integer> numSlots = new HashMap<>();

}
