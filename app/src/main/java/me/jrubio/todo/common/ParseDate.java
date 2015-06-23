package me.jrubio.todo.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Example To.Do list app using MVP architecture.
 * Using android-support-v7 to support old Android versions.
 *
 * @author Jose I. Rubio (@joseirs)
 *
 */
public class ParseDate {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String HOUR_PATTERN = "HH:mm:ss";

    public static String parseDate(Long timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
        Long now = (timestamp != null) ? timestamp : new Date().getTime();
        Date date = new Date(now);
        return format.format(date);
    }

}
