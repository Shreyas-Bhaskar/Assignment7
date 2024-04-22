package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class represents a stock in the portfolio of the user and has data symbol and quantity.
 * Its functions are able to read the data from the filesystem and return prices as requested.
 */
public class Stock implements StockInterface {
  private final String symbol;
  private final List<String> transactionDates;
  private final List<Float> transactionQuantity;
  private float quantity;

  /**
   * Constructs a Stock instance with specified details.
   *
   * @param symbol   the stock's symbol in the market
   * @param quantity the amount of stock owned
   */
  public Stock(String symbol, float quantity) {
    this.symbol = symbol;
    this.quantity = quantity;
    this.transactionDates = new ArrayList<>();
    this.transactionQuantity = new ArrayList<>();
  }

  /**
   * Returns the stock symbol.
   *
   * @return the symbol of the stock
   */
  protected String getSymbol() {
    return symbol;
  }

  /**
   * Returns the quantity of the stock owned.
   *
   * @return the number of shares owned
   */
  protected float getQuantity() {
    return quantity;
  }

  protected void setQuantity(float quantity) {
    this.quantity += quantity;
  }

  public String getDates() {

    return transactionDates.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(",", "", ""));
  }

  protected String getQuantities() {
    return transactionQuantity.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(",", "", ""));
  }

  protected void transact(String date, float quantity) {
    try{
      if (this.getQuantityOnDate(date) + quantity < 0) {
        throw new IllegalArgumentException("Insufficient quantity to sell");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Insufficient quantity to sell");
    }
    transactionQuantity.add(quantity);
    transactionDates.add(date);
  }

  /**
   * Retrieves the data file associated with the stock symbol.
   * The file is in a directory named 'data' within the user's current working directory.
   *
   * @return a File object pointing to the stock's data file
   */
  private File getData() {
    return new File(System.getProperty("user.dir") + "/data/" + symbol.toUpperCase()
            + ".csv");
  }

  /**
   * Retrieves the data row from data for the date closest to input date occurring before
   * the input date.
   *
   * @param date date for which to search data.
   * @return a File object pointing to the stock's data file
   */
  private List<String> getRow(String date) throws Exception {
    try {
      Scanner s = new Scanner(getData());
      s.next();
      List<String> line;
      while (s.hasNext()) {
        line = Arrays.asList(s.next().split(","));
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Boolean equals = parser.parse(date).equals(parser.parse(line.get(0)));
        Boolean after = parser.parse(date).after(parser.parse(line.get(0)));
        if (equals || after) {
          return line;
        }
      }
    } catch (FileNotFoundException e) {
      throw new Exception("No Data found for given stock symbol");
    }
    throw new IllegalArgumentException("No Data found for given Date");
  }

  /**
   * Retrieves the stock price on a specific date.
   * The method searches through the data file for the closest
   * date before or equal to the specified date.
   *
   * @param date the target date in "yyyy-MM-dd" format (e.g., "2023-01-01")
   * @return the stock price on the specified date as a float
   * @throws RuntimeException if the price is not found for the given date
   */
  protected float getPriceOnDate(String date) throws Exception {
    return Float.parseFloat(getRow(date).get(4));
  }

  protected float getQuantityOnDate(String date) throws Exception {
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date targetDate = parser.parse(date);
    float totalQuantity = 0;

    for (int i = 0; i < transactionDates.size(); i++) {
      Date transactionDate = parser.parse(transactionDates.get(i));
      if (!transactionDate.after(targetDate)) {
        totalQuantity += transactionQuantity.get(i);
      }
    }
    return totalQuantity;
  }

  /**
   * Retrieves the date closest occurring before input date in stock date.
   * The method searches through the data file for the closest date before or
   * equal to the specified date.
   *
   * @param date the target date in "yyyy-MM-dd" format (e.g., "2023-01-01")
   * @return the date as string
   * @throws RuntimeException if the price is not found for the given date
   */
  protected String getDate(String date) throws Exception {
    return getRow(date).get(0);
  }

  /**
   * Calculates the cost basis of transactions up to a specified date.
   * The cost basis is the total amount
   * spent on purchases, including the quantity of shares bought multiplied by the price at
   * each transaction date,for transactions that occurred on or before the specified date.
   *
   * @param date The cutoff date (inclusive) for calculating the cost basis, in the format
   *             "yyyy-MM-dd".
   * @return The calculated cost basis as a float.
   * @throws Exception if there's an issue parsing the date or fetching the price for a
   *                   transaction date.
   */
  protected float getCostBasis(String date) throws Exception {
    float costBasis = 0f;
    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    Date targetDate = parser.parse(date);

    for (int i = 0; i < transactionDates.size(); i++) {
      Date transactionDate = parser.parse(transactionDates.get(i));
      if (!transactionDate.after(targetDate)) {
        float quantity = transactionQuantity.get(i);
        float priceOnDate = getBuyPriceOnDate(transactionDates.get(i));
        costBasis += quantity * priceOnDate;
      }
    }
    return costBasis;
  }

  /**
   * Calculates the daily gain or loss for the stock.
   *
   * @param date the date in "yyyy-MM-dd" format
   * @return the difference between closing and opening price
   * @throws Exception if data for the date is not found
   */
  @Override
  public float calculateDailyGainOrLoss(String date) throws Exception {
    List<String> data = getRow(date);
    float openingPrice = Float.parseFloat(data.get(1));
    float closingPrice = Float.parseFloat(data.get(4));
    return closingPrice - openingPrice;
  }

  /**
   * Calculates the overall gain or loss between two dates.
   *
   * @param startDate the start date in "yyyy-MM-dd" format
   * @param endDate   the end date in "yyyy-MM-dd" format
   * @return the difference between closing prices of the two dates
   * @throws Exception if data for either date is not found
   */
  @Override
  public float calculatePeriodGainOrLoss(String startDate, String endDate) throws Exception {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("StartDate should not be greater than EndDate");
    }
    float startPrice = getPriceOnDate(startDate);
    float endPrice = getPriceOnDate(endDate);
    return endPrice - startPrice;
  }


  /**
   * Calculates the x-day moving average for the stock on a given date.
   *
   * @param date The target date in "yyyy-MM-dd" format (e.g., "2023-01-01").
   * @param x    The number of days to include in the moving average calculation.
   * @return The x-day moving average as a float.
   * @throws Exception If there's an error reading the data, or if insufficient data is available.
   */
  @Override
  public float calculateMovingAverage(String date, int x) throws Exception {
    try {
      Scanner s = new Scanner(getData());
      s.next();
      List<String> line;
      while (s.hasNext()) {
        line = Arrays.asList(s.next().split(","));
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Boolean equals = parser.parse(date).equals(parser.parse(line.get(0)));
        Boolean after = parser.parse(date).after(parser.parse(line.get(0)));
        if (equals || after) {
          float value = Float.parseFloat(line.get(4));
          for (int i = 0; i < x - 1; i++) {
            try {
              line = Arrays.asList(s.next().split(","));
              value += Float.parseFloat(line.get(4));
            } catch (NoSuchElementException e) {
              throw new Exception("Insufficient Data, Cannot Calculate Moving Average");
            }
          }
          return value / x;
        }
      }
    } catch (FileNotFoundException e) {
      throw new Exception("No Data found for given stock symbol");
    }
    throw new IllegalArgumentException("No Data found for given Date");
  }

  /**
   * Finds crossovers relative to the 30-day moving average within a specified period.
   *
   * @param startDate The start date of the period in "yyyy-MM-dd" format.
   * @param endDate   The end date of the period in "yyyy-MM-dd" format.
   * @return A list of strings describing the crossovers.
   * @throws Exception If there's an error in fetching or parsing the data.
   */
  @Override
  public String findCrossovers(String startDate, String endDate) throws Exception {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("StartDate should not be greater than EndDate");
    }
    float difference = 0.00f;
    StringBuilder result = new StringBuilder();
    while (!start.isAfter(end)) {
      float closingPrice = getPriceOnDate(start.toString());
      float movingAverage = calculateMovingAverage(start.toString(), 30);
      if (closingPrice > movingAverage && difference > 0) {
        result.append(String.format("Positive crossover on %s\n", start));
      } else if (closingPrice < movingAverage && difference < 0) {
        result.append(String.format("Negative crossover on %s\n", start));
      }
      difference = movingAverage - closingPrice;
      start = start.plusDays(1);
    }
    return String.valueOf(result);
  }

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
  @Override
  public String findMovingCrossovers(String startDate, String endDate, int x, int y) throws
          Exception {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);

    if (start.isAfter(end)) {
      throw new IllegalArgumentException("StartDate should not be greater than EndDate");
    }

    float difference = 0.00f;

    StringBuilder result = new StringBuilder();

    while (!start.isAfter(end)) {
      float movingAverageX = calculateMovingAverage(start.toString(), x);
      float movingAverageY = calculateMovingAverage(start.toString(), y);
      if (movingAverageX > movingAverageY && difference > 0) {
        result.append(String.format("Positive crossover on %s\n", start));
      } else if (movingAverageX < movingAverageY && difference < 0) {
        result.append(String.format("Negative crossover on %s\n", start));
      }
      difference = movingAverageY - movingAverageX;
      start = start.plusDays(1);
    }

    return String.valueOf(result);
  }

  //  @Override
  protected float getBuyPriceOnDate(String date) throws Exception {
    try {
      Scanner s = new Scanner(getData());
      s.next();
      List<String> prevLine = Arrays.asList(s.next().split(","));
      List<String> line;
      while (s.hasNext()) {
        line = Arrays.asList(s.next().split(","));
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Boolean equals = parser.parse(date).equals(parser.parse(line.get(0)));
        Boolean after = parser.parse(date).after(parser.parse(line.get(0)));
        if (equals || after) {
          return Float.parseFloat(prevLine.get(4));
        }
        prevLine = line;
      }
    } catch (FileNotFoundException e) {
      throw new Exception("No Data found for given stock symbol");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException("No Data found for given Date");
  }
}

