package com.example.braulio.userapi.util;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {
    private static final ZoneId MADAGASCAR_ZONE = ZoneId.of("Indian/Antananarivo");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
 
    public static String getMadagascarTimestamp() {
        return ZonedDateTime.now(MADAGASCAR_ZONE).format(FORMATTER); 
    }
}