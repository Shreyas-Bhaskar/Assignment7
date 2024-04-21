package model;

/**
 * Extends the ManagerInterface to provide functionalities specific to managing flexible portfolios,
 * including adding stocks and fetching flexible portfolios.
 */
public interface FlexibleManagerInterface extends ManagerInterface {

  /**
   * Adds a stock to the current portfolio based on the provided symbol, quantity, and date.
   * The method attempts to fetch current stock data and add it to the portfolio if successful.
   * Overrides he add stock method in {@link PortfolioInterface} to add transaction date to stock.
   *
   * @param symbol        the stock symbol to be added
   * @param stockQuantity the quantity of the stock to be added
   * @param date          the date of the transaction
   * @return a string message indicating the result of the operation.
   */
  String addStock(String symbol, int stockQuantity, String date);

  /**
   * Adds a stock to the portfolio with a specified symbol, stock price, and purchase date.
   * This method allows the user to include a new stock into their investment portfolio by
   * providing the stock's symbol, the price at which the stock is to be considered purchased,
   * and the date of this purchase.
   * @param symbol The stock symbol representing the company or financial instrument to be added.
   * @param stockPrice The price at which the stock is considered to have been purchased.
   * @param date The date on which the stock is considered to have been purchased, in the format
   *             "yyyy-MM-dd".
   * @return A String indicating the outcome of the operation, such as success or an error message.
   */
  String addStock(String symbol, float stockPrice, String date);

  /**
   * Fetches a flexible portfolio by its number in the list of portfolios. The method reads the
   * portfolio data from its XML representation and populates a FlexiblePortfolioInterface
   * object with the stock data. Overrides the fetchFlexiblePortfolio method in
   * {@link PortfolioInterface} to build and return a {@link FlexiblePortfolioInterface}.
   *
   * @param number the number of the portfolio in the list, used to fetch portfolio.
   * @return a FlexiblePortfolioInterface representing the fetched portfolio, fully populated with
   *         its stocks
   * @throws Exception if there's an error fetching the portfolio, such as if the portfolio number
   *                   does not exist
   */
  FlexiblePortfolioInterface fetchFlexiblePortfolio(int number) throws Exception;
}