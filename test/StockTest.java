import org.junit.Before;
import org.junit.Test;

import model.Stock;
import model.StockInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains J test cases for the Stock class. It aims to verify the functionality
 * of calculating daily and period gains or losses, computing moving averages, and identifying
 * crossovers, including handling exceptions for invalid date ranges or missing data.
 */
public class StockTest {
  StockInterface stock;

  @Before
  public void setUp() {
    stock = new Stock("AAPL", 100);
  }


  @Test
  public void testCalculateDailyGainOrLoss() {
    try {
      float value = stock.calculateDailyGainOrLoss("2024-03-15");
      assertEquals(1.45, value, 0.01);
    } catch (Exception e) {
      fail("The test should have passed");
    }
  }

  @Test
  public void testCalculateDailyGainOrLossException() {
    try {
      float value = stock.calculateDailyGainOrLoss("1998-03-15");
      fail("The method should have failed");
    } catch (Exception e) {
      assertEquals("No Data found for given Date", e.getMessage());
    }
  }

  @Test
  public void testCalculatePeriodGainOrLoss() {
    try {
      float calculatePeriodGainOrLoss = stock.calculatePeriodGainOrLoss("2023-02-02", "2023-03-02");
      assertEquals(-4.910003662109375f, calculatePeriodGainOrLoss, 0.01);
    } catch (Exception e) {
      fail("The method should have passed");
    }
  }

  @Test
  public void testCalculatePeriodGainOrLossException() {
    try {
      float calculatePeriodGainOrLoss = stock.calculatePeriodGainOrLoss("2023-03-02", "2023-02-02");
      fail("The method should have failed");
    } catch (Exception e) {
      assertEquals("StartDate should not be greater than EndDate", e.getMessage());
    }
  }

  @Test
  public void testCalculateMovingAverage() {
    try {
      float calculateMovingAverage = stock.calculateMovingAverage("2023-02-02", 10);
      assertEquals(143.67999267578125, calculateMovingAverage, 0.01);
    } catch (Exception e) {
      fail("The method should have passed");
    }
  }

  @Test
  public void testCalculateMovingAverageException() {
    try {
      float value = stock.calculateMovingAverage("1998-03-15", 10);
      fail("The method should have failed");
    } catch (Exception e) {
      assertEquals("No Data found for given Date", e.getMessage());
    }
  }

  @Test
  public void testFindCrossovers() {
    try {
      String crossovers = stock.findCrossovers("2024-03-01", "2024-03-15");
      assertTrue(crossovers.contains("Negative crossover on 2024-03-02"));
      assertTrue(crossovers.contains("Negative crossover on 2024-03-03"));
      assertTrue(crossovers.contains("Negative crossover on 2024-03-15"));
    } catch (Exception e) {
      fail("The method should have passed");
    }
  }

  @Test
  public void testFindCrossoversException() {
    try {
      String crossovers = stock.findCrossovers("2024-03-15", "2024-03-01");
      fail("The method should have failed");
    } catch (Exception e) {
      assertEquals("StartDate should not be greater than EndDate", e.getMessage());
    }
  }

  @Test
  public void testFindMovingCrossovers() {
    try {
      String movingCrossovers = stock.findMovingCrossovers("2024-03-01", "2024-03-15", 5, 10);
      assertTrue(movingCrossovers.contains("Positive crossover on 2024-03-15"));
    } catch (Exception e) {
      fail("The method should have passed");
    }
  }

  @Test
  public void testFindMovingCrossoversException() {
    try {
      String movingCrossovers = stock.findMovingCrossovers("2024-03-15", "2024-03-01", 5, 10);
      fail("The method should have failed");
    } catch (Exception e) {
      assertEquals("StartDate should not be greater than EndDate", e.getMessage());
    }
  }
}