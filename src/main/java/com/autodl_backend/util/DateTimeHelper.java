package com.autodl_backend.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间辅助工具
 */
public class DateTimeHelper {

    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * 当前时间字符串
     */
    public static String nowStr() {
        return ZonedDateTime.now(ZONE_ID).format(FORMATTER);
    }

    /**
     * 解析日期时间字符串
     */
    public static LocalDateTime parseDateTime(String dtStr) {
        try {
            return ZonedDateTime.parse(dtStr, FORMATTER).toLocalDateTime();
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(dtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (Exception ex) {
                try {
                    return LocalDate.parse(dtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
                } catch (Exception exc) {
                    return null;
                }
            }
        }
    }

    /**
     * 计算时间差(秒)
     */
    public static Integer calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return (int) Duration.between(startTime, endTime).getSeconds();
    }
}