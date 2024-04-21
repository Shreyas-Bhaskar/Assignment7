package model;

import java.util.List;

/**
 * Extends PortfolioInterface to provide advanced portfolio management capabilities. This interface
 * supports operations for generating performance graphs, calculating cost basis, managing stock
 * transactions, and evaluating portfolio value.
 */
public interface FlexiblePortfolioInterface extends PortfolioInterface {

  /**
   * Generates a graph representing the portfolio's performance over a specified period.
   *
   * @param startDate The start date for the graph (inclusive), in the format "yyyy-MM-dd".
   * @param endDate   The end date for the graph (inclusive), in the format "yyyy-MM-dd".
   * @param type      The type of graph to generate, such as "daily", "weekly", or "monthly".
   * @return A String representation of the graph.
   */
  String graph(String startDate, String endDate, String type) throws Exception;

  /**
   * Calculates the cost basis of the portfolio as of a specific date.
   *
   * @param date The date for which the cost basis is calculated, in the format "yyyy-MM-dd".
   * @return A String indicating the total cost basis by the specified date.
   */
  String costBasis(String date);

  /**
   * Lists the stock symbols currently held in the portfolio.
   *
   * @return A List of Strings, each representing a stock symbol in the portfolio.
   */
  List<String> listStocks();

  /**
   * Buys an additional quantity of an existing stock in the portfolio on a specified date.
   *
   * @param number   The index of the stock in the portfolio's list.
   * @param date     The date of the purchase, in the format "yyyy-MM-dd".
   * @param quantity The quantity of the stock to buy.
   * @return A String message indicating the result of the operation.
   */
  String buyExisting(int number, String date, int quantity);

  /**
   * Sells a quantity of an existing stock in the portfolio on a specified date.
   *
   * @param number   The index of the stock in the portfolio's list.
   * @param date     The date of the sale, in the format "yyyy-MM-dd".
   * @param quantity The quantity of the stock to sell.
   * @return A String message indicating the result of the operation.
   */
  String sellExisting(int number, String date, int quantity);

  /**
   * Inspects a specific stock in the portfolio, providing detailed information crossovers,
   * moving average etc.
   *
   * @param number The index of the stock to inspect in the portfolio's list.
   * @return A StockInterface instance representing the inspected stock.
   */
  StockInterface inspectStock(int number);

  // new functions

  /**
   * Buys an additional quantity of an existing stock in the portfolio at a specific price on a
   * specified date.
   *
   * @param number   The index of the stock in the portfolio's list.
   * @param date     The date of the purchase, in the format "yyyy-MM-dd".
   * @param price    The price at which to buy the stock.
   * @return A String message indicating the result of the operation.
   * @throws Exception If there's an issue executing the buy operation, such as invalid date or
   * insufficient funds.
   */
  String buyExistingPrice(int number, String date, float price) throws Exception;

  void useStrategy();

  /**
   * Verifies whether the specified date matches or precedes the start date of the current investment strategy.
   * This is important for ensuring that strategy actions are not applied retroactively beyond their intended scope.
   *
   * @param date The date to check, in the format "yyyy-MM-dd".
   * @return true if the date is valid within the context of the strategy's timeline, false otherwise.
   */
  boolean checkStartDate(String date);
}
