package model;


/**
 * Defines the operations that can be performed on a stock within the financial portfolio
 * management system.
 * This interface includes methods for calculating daily and periodic gains or losses,
 * computing moving averages, and identifying crossovers in stock price movement relative
 * to its moving average.
 */
public interface StockInterface {

  /**
   * Calculates the daily gain or loss for the stock on a given date.
   *
   * @param date the date in "yyyy-MM-dd" format.
   * @return the difference between closing and opening price.
   * @throws Exception if data for the date is not found.
   */
  float calculateDailyGainOrLoss(String date) throws Exception;

  /**
   * Calculates the overall gain or loss between two dates.
   *
   * @param startDate the start date in "yyyy-MM-dd" format.
   * @param endDate   the end date in "yyyy-MM-dd" format.
   * @return the difference between closing prices of the two dates.
   * @throws Exception if data for either date is not found.
   */
  float calculatePeriodGainOrLoss(String startDate, String endDate) throws Exception;

  /**
   * Calculates the x-day moving average for the stock on a given date.
   *
   * @param date The target date in "yyyy-MM-dd" format.
   * @param x    The number of days to include in the moving average calculation.
   * @return The x-day moving average as a float.
   * @throws Exception If there's an error reading the data, or if insufficient data is available.
   */
  float calculateMovingAverage(String date, int x) throws Exception;

  /**
   * Finds crossovers relative to the 30-day moving average within a specified period.
   *
   * @param startDate The start date of the period in "yyyy-MM-dd" format.
   * @param endDate   The end date of the period in "yyyy-MM-dd" format.
   * @return A list of strings describing the crossovers.
   * @throws Exception If there's an error in fetching or parsing the data.
   */
  String findCrossovers(String startDate, String endDate) throws Exception;

  /**
   * Identifies moving crossovers between two moving averages over a specified period.
   *
   * @param startDate The start date of the period in "yyyy-MM-dd" format.
   * @param endDate   The end date of the period in "yyyy-MM-dd" format.
   * @param x         The number of days for the smaller moving average.
   * @param y         The number of days for the larger moving average.
   * @return A list of strings describing the moving crossovers.
   * @throws Exception If there's an error in fetching or parsing the data.
   */
  String findMovingCrossovers(String startDate, String endDate, int x, int y) throws Exception;
}
