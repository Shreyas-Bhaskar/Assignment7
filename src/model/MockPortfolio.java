package model;

/**
 * The MockPortfolio class implements {@link PortfolioInterface} and simulates
 * a portfolio for testing purposes without requiring access to the actual data or API calls.
 */
public class MockPortfolio implements PortfolioInterface {

  protected final int uniqueCode;
  protected final StringBuilder log;

  /**
   * This constructor initializes the mock portfolio with a log to record method calls
   * and a unique code to identify the mock portfolio instance.
   * This setup aids in tracking interactions and outcomes during tests.
   *
   * @param log        a string builder object to log all functions
   * @param uniqueCode a unique code to be returned by functions
   */
  public MockPortfolio(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String composition() {
    log.append("Reached Composition");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String value(String date) {
    log.append("Reached Value");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String update() {
    log.append("Reached Update");
    return String.valueOf(uniqueCode);
  }
}
