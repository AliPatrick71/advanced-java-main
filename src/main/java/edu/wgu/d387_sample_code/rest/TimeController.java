package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.service.TimeZoneConverterService;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class TimeController {

    private final TimeZoneConverterService timeZoneConverterService;

    public TimeController(TimeZoneConverterService timeZoneConverterService) {
        this.timeZoneConverterService = timeZoneConverterService;
    }

    @GetMapping("/convert-times")
    public String convertPresentationTimes() {
        // Use the current time or a specific time for conversion
        ZonedDateTime now = ZonedDateTime.now();
        return timeZoneConverterService.convertTimes(now);
    }
    
}
