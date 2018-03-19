package com.lojo.apps.scheduler.complexscheduler.service;

import com.lojo.apps.scheduler.complexscheduler.model.Schedule;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftInstance;
import com.lojo.apps.scheduler.complexscheduler.model.ShiftSeries;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
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

        DateTimeZone.setDefault(DateTimeZone.forTimeZone(TimeZone.getTimeZone("America/Toronto")));

        DateTimeFormatter dtf=DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime jan=dtf.parseLocalDateTime("2020-01-01 00:00:00");
        LocalDateTime feb=dtf.parseLocalDateTime("2020-02-01 00:00:00");


        ShiftSeries shiftSeries = new ShiftSeries();
        shiftSeries.setDoesRecur(true);
        shiftSeries.setShiftLengthHours(8);
        shiftSeries.setStart(jan);
        shiftSeries.setEnd(feb);
        shiftSeries.setInstantiated(false);

        Set<ShiftSeries> shiftSeriesSet = new HashSet<>();
        shiftSeriesSet.add(shiftSeries);

        schedule.setShiftSeries(shiftSeriesSet);

        Schedule s = shiftGenerator.generateShifts(schedule);

        List<LocalDateTime> keys=new ArrayList<>(s.getShifts().keySet());
        Collections.sort(keys);

        LocalDateTime expectedStart =new LocalDateTime(dtf.parseLocalDateTime("2020-01-01 00:00:00"));
        LocalDateTime expectedEnd =new LocalDateTime(dtf.parseLocalDateTime("2020-01-01 08:00:00"));

        for (LocalDateTime key : keys) {
            ShiftInstance si=s.getShifts().get(key);
            log.info("Shift[{}] - ({})----({}), testing...",key,si.getStart(), si.getEnd());
            assertEquals(si.getStart(),expectedStart);
            assertEquals(si.getEnd(),expectedEnd);
            expectedStart=expectedEnd;
            expectedEnd=expectedEnd.plus(new Duration(8 * DateTimeConstants.MILLIS_PER_HOUR));
            log.info("...actual=expected");
        }


    }
}