package com.taranko.ticketofice.server.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_TIME_FORMAT = "HH:mm";

    public Date buildDateFromString(String dateStr) throws ParseException {
        return buildDateFromString(dateStr, DEFAULT_DATE_FORMAT);
    }

    public Date buildDateFromString(String dateStr, String dateFormat) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date date = formatter.parse(dateStr);

        return date;
    }

    public String buildStringFromDate(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String dateString = dateFormat.format(date);

        dateFormat.applyPattern("dd-MM-yyyy");

        return dateString;
    }

    public Date buildTimeFromString(String timeStr) throws ParseException {
        return buildTimeFromString(timeStr, DEFAULT_TIME_FORMAT);
    }

    public Date buildTimeFromString(String timeStr, String timeFormat) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(timeFormat);
        Date time = formatter.parse(timeStr);

        return time;
    }

    public String buildStringFromTime(Date time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        String timeString = dateFormat.format(time);

        return timeString;
    }
}