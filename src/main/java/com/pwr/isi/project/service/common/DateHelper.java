package com.pwr.isi.project.service.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHelper {

  private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

  public static String getAnyDateFromCurrentDay(int numberOfDaysFromCurrentDay) {
    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_YEAR, numberOfDaysFromCurrentDay);

    return new SimpleDateFormat(DEFAULT_FORMAT).format(calendar.getTime());
  }
}
