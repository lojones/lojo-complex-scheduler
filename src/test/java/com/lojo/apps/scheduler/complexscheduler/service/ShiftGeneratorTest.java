package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Schedule;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftInstance;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftSeries;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Slf4j
public class ShiftGeneratorTest {

    ShiftGenerator shiftGenerator = new ShiftGenerator();

    @Test
    public void test_generateShifts() {
        Schedule schedule =  new Schedule();
        schedule.setName("Test Schedule");

        Instant j1 = Instant.parse("2020-01-01 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Instant f1 = Instant.parse("2020-02-01 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

        schedule.setStart(j1);
        schedule.setEnd(f1);

        ShiftSeries shiftSeries = new ShiftSeries();
        shiftSeries.setDoesRecur(true);
        shiftSeries.setShiftLengthHours(8);
        shiftSeries.setStart(j1);
        shiftSeries.setEnd(f1);
        shiftSeries.setInstantiated(false);

        Set<ShiftSeries> shiftSeriesSet = new HashSet<>();
        shiftSeriesSet.add(shiftSeries);

        schedule.setShiftSeries(shiftSeriesSet);

        Schedule s = shiftGenerator.generateShifts(schedule);

        List<Instant> keys=new ArrayList<>(s.getShifts().keySet());
        Collections.sort(keys);

        for (Instant key : keys) {
            ShiftInstance si=s.getShifts().get(key);
            log.info("Shift {} - {} {} {}",key,si.getStart(), si.getEnd());
        }


    }
}