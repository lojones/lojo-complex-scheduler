package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Schedule;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftSeries;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftInstance;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class ShiftGenerator {

    public Schedule generateShifts(Schedule schedule) {

        Set<ShiftSeries> shiftSeriesSet = schedule.getShiftSeries();

        for (ShiftSeries shiftSeries : shiftSeriesSet) {

            if (shiftSeries.getDoesRecur()) {

                Integer shiftLengthHours = shiftSeries.getShiftLengthHours();

                LocalDateTime shiftStart = shiftSeries.getStart();
                LocalDateTime shiftEnd = shiftStart.plus(new Duration(DateTimeConstants.MILLIS_PER_HOUR * shiftLengthHours));
                ReadableDuration a=new Duration(null);
                while (shiftEnd.isBefore(shiftSeries.getEnd())) {
                    ShiftInstance shiftInstance = new ShiftInstance();
                    shiftInstance.setStart(shiftStart);
                    shiftInstance.setEnd(shiftEnd);
                    shiftInstance.setNumSlots(shiftSeries.getNumSlots());

                    schedule.getShifts().put(shiftStart,shiftInstance);

                    shiftStart=shiftEnd;
                    shiftEnd=shiftStart.plus(new Duration(DateTimeConstants.MILLIS_PER_HOUR * shiftLengthHours));
                }


            } else {

                ShiftInstance shiftInstance= new ShiftInstance();
                shiftInstance.setNumSlots(shiftSeries.getNumSlots());
                shiftInstance.setStart(shiftSeries.getStart());
                shiftInstance.setEnd(shiftSeries.getEnd());

                schedule.getShifts().put(shiftSeries.getStart(),shiftInstance);

            }
        }

        return schedule;
    }


}
