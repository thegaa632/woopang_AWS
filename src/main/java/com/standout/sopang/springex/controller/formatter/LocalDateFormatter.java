//package com.standout.sopang.springex.controller.formatter;
//
//import org.springframework.format.Formatter;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//
//public class LocalDateFormatter implements Formatter<LocalDate> {
//
//    @Override
//    public LocalDate parse(String text, Locale locale) {
//        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//
//    @Override
//    public String print(LocalDate object, Locale locale) {
//        // LocalDate를 LocalDateTime으로 변환
//        LocalDateTime localDateTime = object.atStartOfDay();
//
//        // 지정된 포맷으로 출력
//        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
//    }
//}
