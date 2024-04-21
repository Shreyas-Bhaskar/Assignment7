package model;

import java.time.LocalDate;

/**
 * A mock stock class for testing, simulating stock operations and logging calls for verification.
 * It uses a unique code for return values and a log for tracking method invocations.
 */
public class MockStock implements StockInterface {
  private final int uniqueCode;
  private final StringBuilder log;

  /**
   * This constructor initializes the mock stock with a log to record method calls
   * and a unique code to identify the mock stock instance.
   * This setup aids in tracking interactions and outcomes during tests.
   *
   * @param log        a string builder object to log all functions
   * @param uniqueCode a unique code to be returned by functions
   */
  public MockStock(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public float calculateDailyGainOrLoss(String date) throws Exception {
    log.append("Reached calculateDailyGainOrLoss").append(date);
    return (float) uniqueCode;
  }

  @Override
  public float calculatePeriodGainOrLoss(String startDate, String endDate) throws Exception {
    log.append("Reached calculatePeriodGainOrLoss with startDate: ").append(startDate)
            .append(", endDate: ").append(endDate).append("\n");
    if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
      throw new IllegalArgumentException("StartDate should not be greater than EndDate");
    }
    return (float) uniqueCode;
  }

  @Override
  public float calculateMovingAverage(String date, int x) throws Exception {
    log.append("Reached calculateMovingAverage").append(date).append(x);
    return (float) uniqueCode;
  }

  @Override
  public String findCrossovers(String startDate, String endDate) throws Exception {
    log.append("Reached findCrossovers with startDate: ").append(startDate).append(", endDate: ")
            .append(endDate).append("\n");
    if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
      throw new IllegalArgumentException("StartDate should not be greater than EndDate");
    }
    return String.valueOf(uniqueCode);
  }

  @Override
  public String findMovingCrossovers(String startDate, String endDate, int x, int y) throws
          Exception {
    log.append("Reached findMovingCrossovers with startDate: ").append(startDate).
            append(", endDate: ").append(endDate).append(", x: ").append(x).append(", y: ")
            .append(y).append("\n");
    if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
      throw new IllegalArgumentException("StartDate should not be greater than EndDate");
    }
    return String.valueOf(uniqueCode);
  }
}
