package com.lojo.apps.scheduler.complexscheduler.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "name")
public class Schedule {

    Map<LocalDateTime,ShiftInstance> shifts=new HashMap<>();
    String name;
    Set<ShiftSeries> shiftSeries=new HashSet<>();

}
