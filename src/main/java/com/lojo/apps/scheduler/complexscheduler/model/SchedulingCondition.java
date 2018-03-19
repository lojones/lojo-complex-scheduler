package com.lojo.apps.scheduler.complexscheduler.model;

import org.joda.time.Instant;
import org.joda.time.LocalDateTime;

public class SchedulingCondition {
    SchedulingConditionTypeEnum type;
    Boolean isRecurring;
    Boolean isOneTime;
    RecurranceEnum recurranceType;
    Integer recurrancePeriod;
    LocalDateTime specificStart;
    LocalDateTime specificEnd;



}
