package com.lojo.apps.scheduler.complexscheduler.model.exception;

import lombok.NonNull;

public class NotFoundException extends Exception {
    public NotFoundException(@NonNull String thingNotFoundType, @NonNull String thingNotFoundName, @NonNull String message) {

        super("Could not find "+thingNotFoundType+" called "+thingNotFoundName+": "+message);
    }
}
