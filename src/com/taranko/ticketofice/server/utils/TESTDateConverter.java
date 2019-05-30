package com.taranko.ticketofice.server.utils;

import java.text.ParseException;
import java.util.Date;

public class TESTDateConverter {
    public static void main(String[] args) throws ParseException {
        DateUtils dateUtils = new DateUtils();
        System.out.println(dateUtils.buildDateFromString("72-05-2018"));
        System.out.println(dateUtils.buildTimeFromString("84:00"));
        System.out.println(dateUtils.buildStringFromDate(new Date()));
        System.out.println(dateUtils.buildStringFromTime(new Date()));

        System.out.println(dateUtils.buildTimeFromString("14:00").after(dateUtils.buildTimeFromString("10:00")));
    }
}
