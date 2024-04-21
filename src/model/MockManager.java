package model;

/**
 * The MockManager class is a mock implementation of the {@link ManagerInterface},
 * designed for testing purposes. It simulates the behavior of a portfolio manager
 * without performing real operations or API calls
 */
public class MockManager implements ManagerInterface {

  protected final StringBuilder log;
  protected final int uniqueCode;

  /**
   * This constructor sets up the mock manager with logging and a unique ID,
   * enabling tests to check method calls and simulate scenarios
   * by analyzing the log and returned values.
   *
   * @param log        a string builder object to log all functions
   * @param uniqueCode a unique code to be returned by functions
   */
  public MockManager(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }


  @Override
  public String[] returnPortfolioList() {
    log.append("Reached portfolioList ");
    String[] s = {String.valueOf(uniqueCode)};
    return s;
  }

  @Override
  public boolean createPortfolio(String portfolioName) throws IllegalArgumentException {
    log.append("Reached createPortfolio ");
    return true;
  }

  @Override
  public String addStock(String stockName, int stockQuantity) {
    log.append("Reached addStock ").append(stockName).append(" ").append(stockQuantity).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String savePortfolio() {
    log.append("Reached savePortfolio");
    return String.valueOf(uniqueCode);
  }

  @Override
  public PortfolioInterface fetchPortfolio(int number) throws Exception {
    log.append("Reached fetchPortfolio ").append(number).append(" ");
    return new MockPortfolio(log, uniqueCode);
  }

  @Override
  public String deletePortfolio(int number) {
    log.append("Reached deletePortfolio ").append(number);
    return String.valueOf(uniqueCode);
  }
}
