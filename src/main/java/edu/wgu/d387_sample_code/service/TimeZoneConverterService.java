package edu.wgu.d387_sample_code.service;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class TimeZoneConverterService {
    

    public String convertTimes(ZonedDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a z");

        // Convert to Eastern Time (ET)
        ZonedDateTime easternTime = dateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        String easternTimeFormatted = easternTime.format(formatter);

        // Convert to Mountain Time (MT)
        ZonedDateTime mountainTime = dateTime.withZoneSameInstant(ZoneId.of("America/Denver"));
        String mountainTimeFormatted = mountainTime.format(formatter);

        // Convert to Coordinated Universal Time (UTC)
        ZonedDateTime utcTime = dateTime.withZoneSameInstant(ZoneId.of("UTC"));
        String utcTimeFormatted = utcTime.format(formatter);

        // Return the formatted times as a string
        return String.format("ET: %s, MT: %s, UTC: %s", 
                             easternTimeFormatted, mountainTimeFormatted, utcTimeFormatted);
    }

}
