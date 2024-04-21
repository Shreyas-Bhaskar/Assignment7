package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Provides a foundational framework for controller classes, including common utility methods
 * applicable across different controllers.
 */
public abstract class AbstractController {

  /**
   * Validates if the given date string is in the correct format and is not a future date.
   * The method checks against the system's current date to ensure the input date is today
   * or in the past.
   *
   * @param date The date string to validate, expected to be in the "yyyy-MM-dd" format.
   * @return {@code true} if the date is valid and not in the future, {@code false} otherwise.
   */
  protected boolean checkDate(String date) {
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    parser.setLenient(false);

    try {
      Date input = parser.parse(date);
      LocalDate current = LocalDate.now();
      LocalDate set = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      return set.isBefore(current) || set.isEqual(current);
    } catch (ParseException e) {
      return false;
    }
  }
}
