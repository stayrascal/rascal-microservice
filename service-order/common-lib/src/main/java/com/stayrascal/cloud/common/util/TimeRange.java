package com.stayrascal.cloud.common.util;

import java.util.Date;

public class TimeRange {
    private Date startTime;
    private Date endTime;

    public TimeRange(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeRange() {
    }

    public static TimeRange from(Long startTime, Long endTime) {
        return new TimeRange(getDate(startTime), getDate(endTime));
    }

    public static Date getDate(Long fromDate) {
        return fromDate != null ? new Date(fromDate.longValue()) : null;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
