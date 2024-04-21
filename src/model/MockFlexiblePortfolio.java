package model;

import java.util.Collections;
import java.util.List;

/**
 * The MockPortfolio class implements {@link PortfolioInterface} and simulates
 * a portfolio for testing purposes without requiring access to the actual data or API calls.
 */
public class MockFlexiblePortfolio extends MockPortfolio implements FlexiblePortfolioInterface {

  /**
   * This constructor initializes the mock portfolio with a log to record method calls
   * and a unique code to identify the mock portfolio instance.
   * This setup aids in tracking interactions and outcomes during tests.
   *
   * @param log        a string builder object to log all functions
   * @param uniqueCode a unique code to be returned by functions
   */
  public MockFlexiblePortfolio(StringBuilder log, int uniqueCode) {
    super(log, uniqueCode);
  }

  @Override
  public String graph(String startDate, String endDate, String type) {
    log.append("Reached graph ").append(startDate).append(" ").append(endDate).append(" ")
            .append(type).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String costBasis(String date) {
    log.append("Reached costBasis ").append(date).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public List<String> listStocks() {
    log.append("Reached listStocks");
    return Collections.singletonList(String.valueOf(uniqueCode));
  }

  @Override
  public String buyExisting(int number, String date, int quantity) {
    log.append("Reached buyExisting ").append(number).append(" ").append(date).append(" ")
            .append(quantity).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public String sellExisting(int number, String date, int quantity) {
    log.append("Reached sellExisting ").append(number).append(" ").append(date).append(" ")
            .append(quantity).append(" ");
    return String.valueOf(uniqueCode);
  }

  @Override
  public StockInterface inspectStock(int number) {
    log.append("Reached inspectStock").append(number);
    return new MockStock(log, uniqueCode);
  }

  @Override
  public String buyExistingPrice(int number, String date, float price) {
    return null;
  }

  @Override
  public void useStrategy() {
    log.append("Reached useStrategy");
  }

  @Override
  public boolean checkStartDate(String date) {
    return false;
  }
}
