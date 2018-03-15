package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.Data;
import org.joda.time.*;

import java.util.HashMap;
import java.util.Map;

@Data
public class ShiftSeries {

    Boolean doesRecur;
    Boolean instantiated;

    Integer shiftLengthHours;

    Instant start;
    Instant end;

    Map<JobRole,Integer> numSlots = new HashMap<>();

}
