package com.lojo.apps.scheduler.complexscheduler.model;

import org.joda.time.Instant;

public class SchedulingCondition {
    SchedulingConditionTypeEnum type;
    Boolean isRecurring;
    Boolean isOneTime;
    RecurranceEnum recurranceType;
    Integer recurrancePeriod;
    Instant specificStart;
    Instant specificEnd;



}
