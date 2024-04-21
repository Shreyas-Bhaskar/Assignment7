package model;

/**
 * This interface represents the Manager of the stock management model portal. It defines the
 * methods of all implementations of the class such as add stock, save portfolio and more.
 */
public interface ManagerInterface {

  /**
   * Retrieves a list of portfolio names managed by this manager.
   *
   * @return An array of strings representing the names of the portfolios.
   */
  String[] returnPortfolioList();

  /**
   * Creates a new portfolio with the given name.
   *
   * @param portfolioName The name of the new portfolio.
   * @return True if the portfolio was successfully created, false otherwise.
   * @throws IllegalArgumentException If the portfolio name is not valid.
   */
  boolean createPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Adds a stock to a portfolio.
   *
   * @param stockName     The name of the stock to add.
   * @param stockQuantity The quantity of the stock to add.
   * @return A string message indicating the result of the operation.
   */
  String addStock(String stockName, int stockQuantity);

  /**
   * Saves the current state of a portfolio to the specified path.
   * If the portfolio is empty, an error message is returned.
   *
   * @return A string message indicating the result of the save operation.
   */
  String savePortfolio();

  // Functions for view portfolio


  /**
   * Fetches the portfolio at the specified index.
   *
   * @param number The index of the portfolio to fetch.
   * @return The fetched portfolio.
   * @throws Exception If the portfolio cannot be fetched for any reason.
   */
  PortfolioInterface fetchPortfolio(int number) throws Exception;

  // Functions for remove portfolio

  /**
   * Deletes the portfolio at the specified index.
   *
   * @param number The index of the portfolio to delete.
   * @return A string message indicating the result of the delete operation.
   */
  String deletePortfolio(int number);

}
