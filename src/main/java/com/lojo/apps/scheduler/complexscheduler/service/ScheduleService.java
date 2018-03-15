package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Schedule;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftSeries;
import lombok.NonNull;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;

import java.util.Set;
import java.util.TimeZone;

public class ScheduleService {

    public Schedule getSchedule(@NonNull String name, @NonNull Instant startTime, @NonNull Instant endTime, Set<ShiftSeries> shiftSeries) {
//        DateTimeZone.setDefault(TimeZone.);
        Schedule schedule = new Schedule();
        schedule.setName(name);
        schedule.setStart(startTime);
        schedule.setEnd(endTime);
        schedule.setShiftSeries(shiftSeries);
        return schedule;
    }
}
