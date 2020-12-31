package com.phish.search.model.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PhishTankDateConverter extends AbstractBeanField {
  @Override
  protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss+00:00");
    // UTCなので日本時間に変換
    return LocalDateTime.parse(s.replace("T", " "), dtf).plusHours(9);
  }
}
