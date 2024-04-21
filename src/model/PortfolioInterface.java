package model;

/**
 * Interface to define the operations for a stock portfolio, allowing for creation, manipulation,
 * and analysis of a portfolio.
 */
public interface PortfolioInterface {

  /**
   * Provides a composition summary of the portfolio, listing stocks and their quantities.
   *
   * @return A string representation of the portfolio composition.
   */
  String composition();

  /**
   * Calculates the value of the portfolio on a given date.
   *
   * @param date The date for which the portfolio value is calculated.
   * @return A string representation of the portfolio's value on the specified date.
   */
  String value(String date);

  /**
   * Updates the portfolio, typically fetching the latest stock prices or making other adjustments.
   *
   * @return A string message indicating the result of the update.
   */
  String update();
}
