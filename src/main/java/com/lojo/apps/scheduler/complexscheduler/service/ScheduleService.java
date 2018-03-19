package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Schedule;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftSeries;
import lombok.NonNull;
import org.joda.time.LocalDateTime;

import java.util.Set;

public class ScheduleService {

    public Schedule getSchedule(@NonNull String name, @NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime, Set<ShiftSeries> shiftSeries) {

        Schedule schedule = new Schedule();
        schedule.setName(name);
        schedule.setShiftSeries(shiftSeries);
        return schedule;
    }
}
