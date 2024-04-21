package model;

/**
 * The MockManager class is a mock implementation of the {@link ManagerInterface},
 * designed for testing purposes. It simulates the behavior of a portfolio manager
 * without performing real operations or API calls
 */
public class MockFlexibleManager extends MockManager implements FlexibleManagerInterface {


  /**
   * This constructor sets up the mock manager with logging and a unique ID,
   * enabling tests to check method calls and simulate scenarios
   * by analyzing the log and returned values.
   *
   * @param log        a string builder object to log all functions
   * @param uniqueCode a unique code to be returned by functions
   */
  public MockFlexibleManager(StringBuilder log, int uniqueCode) {
    super(log, uniqueCode);
  }

  @Override
  public String addStock(String symbol, int stockQuantity, String date) {
    log.append("Reached addStock ").append(symbol).append(" ").append(stockQuantity).append(" ")
            .append(date).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String addStock(String symbol, float stockPrice, String date) {
    log.append("Reached addStock ").append(symbol).append(" ").append(stockPrice).append(" ")
            .append(date).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public FlexiblePortfolioInterface fetchFlexiblePortfolio(int number) throws Exception {
    log.append("Reached fetchPortfolio ").append(number).append(" ");
    return new MockFlexiblePortfolio(log, uniqueCode);
  }
}
