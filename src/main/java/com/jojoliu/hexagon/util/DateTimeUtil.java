package com.jojoliu.hexagon.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Jojo on 27/05/2017.
 */
public class DateTimeUtil {
    /**
     * yyyy-MM-dd hh:mm:ss date format.
     */
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 按照"yyyy-MM-dd HH:mm:ss" 解析时间
     */
    public static Date parseTime(String dateString) {
        LocalDateTime localDateTime = parseDateTime(dateString, DATA_FORMAT);
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 按照"yyyy-MM-dd HH:mm:ss" 格式化时间
     */
    public static String formatDateTime(Date date) {
        return formatDateTime(date, DATA_FORMAT);
    }

    /**
     * 按照指定格式生成时间字符串
     */
    public static String formatDateTime(Date date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return toLocalDateTime(date).format(formatter);
    }

    /**
     * 按照指定格式指定时间
     */
    public static LocalDateTime parseDateTime(String dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date fromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 按照指定格式指定时间
     */
    public static Date parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return fromLocalDateTime(localDate.atStartOfDay());
    }

    /**
     * Calls {@link #asLocalDate(Date, ZoneId)} with the system default time zone.
     */
    public static LocalDate asLocalDate(Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDate asLocalDate(Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
    }

    /**
     * Calls {@link #asLocalDateTime(Date, ZoneId)} with the system default time zone.
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return asLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDateTime} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDateTime asLocalDateTime(Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Timestamp)
            return ((java.sql.Timestamp) date).toLocalDateTime();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
    }

    /**
     * Calls {@link #asUtilDate(Object, ZoneId)} with the system default time zone.
     */
    public static Date asUtilDate(Object date) {
        return asUtilDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates a {@link Date} from various date objects. Is null-safe. Currently supports:<ul>
     * <li>{@link Date}
     * <li>{@link java.sql.Date}
     * <li>{@link LocalDate}
     * <li>{@link LocalDateTime}
     * <li>{@link ZonedDateTime}
     * <li>{@link Instant}
     * </ul>
     *
     * @param zone Time zone, used only if the input object is LocalDate or LocalDateTime.
     * @return {@link Date} (exactly this class, not a subclass, such as java.sql.Date)
     */
    public static Date asUtilDate(Object date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp)
            return new Date(((Date) date).getTime());
        if (date instanceof Date)
            return (Date) date;
        if (date instanceof LocalDate)
            return Date.from(((LocalDate) date).atStartOfDay(zone).toInstant());
        if (date instanceof LocalDateTime)
            return Date.from(((LocalDateTime) date).atZone(zone).toInstant());
        if (date instanceof ZonedDateTime)
            return Date.from(((ZonedDateTime) date).toInstant());
        if (date instanceof Instant)
            return Date.from((Instant) date);

        throw new UnsupportedOperationException("Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
    }

    /**
     * Creates an {@link Instant} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static Instant asInstant(Date date) {
        if (date == null)
            return null;
        else
            return Instant.ofEpochMilli(date.getTime());
    }

    /**
     * Calls {@link #asZonedDateTime(Date, ZoneId)} with the system default time zone.
     */
    public static ZonedDateTime asZonedDateTime(Date date) {
        return asZonedDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link ZonedDateTime} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static ZonedDateTime asZonedDateTime(Date date, ZoneId zone) {
        if (date == null)
            return null;
        else
            return asInstant(date).atZone(zone);
    }

    /**
     * Get off days of day1 and day2.
     *
     * @param day1
     * @param day2
     * @return
     */
    public static long offDaysBetweenDates(Date day1, Date day2) {
        LocalDate localDate1 = asLocalDate(day1);
        LocalDate localDate2 = asLocalDate(day2);
        if (localDate1.isBefore(localDate2)) {
            return 0;
        } else {
            return localDate1.toEpochDay() - localDate2.toEpochDay();
        }

    }

    /**
     * 真实比较
     * @param day1
     * @param day2
     * @return
     */
    public static long offDaysBetweenDates2(Date day1, Date day2) {
        LocalDate localDate1 = asLocalDate(day1);
        LocalDate localDate2 = asLocalDate(day2);
        if (localDate1.isBefore(localDate2)) {
            return localDate2.toEpochDay() - localDate1.toEpochDay();
        } else {
            return localDate1.toEpochDay() - localDate2.toEpochDay();
        }

    }

    public static long dayAfterDays(Date after, Date before) {
        LocalDate localAfter = asLocalDate(after);
        LocalDate localBefore = asLocalDate(before);
        if (localAfter.isBefore(localBefore)) {
            return 0;
        } else {
            return localAfter.toEpochDay() - localBefore.toEpochDay();
        }
    }

    /**
     * 计算当前日期之后n天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, long days) {
        LocalDateTime localDateTime = asLocalDateTime(date);
        localDateTime = localDateTime.plusDays(days);
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 获取今天开始日期：2015-12-09 00:00:00
     *
     * @return
     */
    public static Date getTodayBeginTime() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        return fromLocalDateTime(todayStart);
    }

    public static Date getDateBeginTime(Date date) {
        LocalDateTime localDateTime = toLocalDateTime(date).toLocalDate().atStartOfDay();
        return fromLocalDateTime(localDateTime);
    }

    /**
     * 获取昨天开始日期：2015-12-09 00:00:00
     *
     * @return
     */
    public static Date getYesterdayBeginTime() {
        LocalDateTime todayStart = LocalDate.now().minusDays(1).atStartOfDay();
        return fromLocalDateTime(todayStart);
    }

    /**
     * 获取前一小时时间：2015-12-09 12:00:00
     *
     * @return
     */
    public static Date getPreviousHourTime() {
        LocalDateTime todayStart = LocalDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0);
        return fromLocalDateTime(todayStart);
    }

    /**
     * 获取本小时开始时间：2015-12-09 13:00:00
     *
     * @return
     */
    public static Date getHourTime() {
        LocalDateTime todayStart = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        return fromLocalDateTime(todayStart);
    }

    /**
     * 获取前一周开始时间：2015-11-29 00:00:00
     *
     * @return
     */
    public static Date getPreiousWeekTime() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime todayStart = localDate.minusDays(localDate.getDayOfWeek().getValue()).minusWeeks(1).atStartOfDay();
        return fromLocalDateTime(todayStart);
    }

    /**
     * 获取周开始时间：2015-12-06 00:00:00
     *
     * @return
     */
    public static Date getWeekTime() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime todayStart = localDate.minusDays(localDate.getDayOfWeek().getValue()).atStartOfDay();
        return fromLocalDateTime(todayStart);
    }
}
