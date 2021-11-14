package utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateUtils {

    public static final String DATE_FORMATTER = "yyyy-MM-DD";
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd'T'hh:mm:ss";
    public static final String DATETIME_FORMATTER_SPC = "yyyy-MM-dd HH:mm:ss";

    public static String getDateTimeString() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER));
    }

    public static String getDateTimeSpcString() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER_SPC));
    }

    public static String getDateString() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
    }
}
