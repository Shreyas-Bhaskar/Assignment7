import org.junit.Before;
import org.junit.Test;

import model.FlexibleManager;
import model.FlexibleManagerInterface;
import model.FlexiblePortfolioInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the functionality of the FlexiblePortfolio and FlexibleManager classes,
 * including portfolio creation, stock transactions, value calculation, and exception handling.
 * It aims to ensure accurate portfolio management, including buying, selling, and assessing
 * stock positions.
 */
public class FlexiblePortfolioTest {
  FlexibleManagerInterface manager;
  FlexiblePortfolioInterface test04;

  @Before
  public void setUp() {
    manager = new FlexibleManager();
    try {
      test04 = manager.fetchFlexiblePortfolio(4);
    } catch (Exception ignore) {
      fail("Failed to fetch portfolios");
    }
  }

  @Test
  public void testComposition() {
    String output = "Portfolio Name: 04test\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Transaction Dates: 2024-01-02\r\n" +
            "Transaction Quantities: 100.0\r\n" +
            "Quantity: 100.0\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Transaction Dates: 2024-01-02\r\n" +
            "Transaction Quantities: 100.0\r\n" +
            "Quantity: 100.0\r\n" +
            "--------------------\r\n" +
            "Symbol: MSFT\r\n" +
            "Transaction Dates: 2024-01-02\r\n" +
            "Transaction Quantities: 100.0\r\n" +
            "Quantity: 100.0\r\n";
    assertEquals(output, test04.composition());
  }

  @Test
  public void testValueCorrect() {
    String output = "Portfolio Name: 04test\r\n" +
            "-- Value Displayed for closest available date in data\r\n" +
            "-- Value will be displayed for last closing price if market close on date\r\n" +
            "-- Update portfolio from main menu if date not current\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Quantity: 100\r\n" +
            "Date: 2024-03-01\r\n" +
            "Price: $179.66\r\n" +
            "Value: $17966.0\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Quantity: 100\r\n" +
            "Date: 2024-03-01\r\n" +
            "Price: $138.08\r\n" +
            "Value: $13808.0\r\n" +
            "--------------------\r\n" +
            "Symbol: MSFT\r\n" +
            "Quantity: 100\r\n" +
            "Date: 2024-03-01\r\n" +
            "Price: $415.5\r\n" +
            "Value: $41550.0\r\n" +
            "Total value of portfolio is $73324.0";
    assertEquals(output, test04.value("2024-03-03"));
  }

  @Test
  public void testValueIncorrectDate() {
    String output = "Portfolio Name: 04test\r\n" +
            "-- Value Displayed for closest available date in data\r\n" +
            "-- Value will be displayed for last closing price if market close on date\r\n" +
            "-- Update portfolio from main menu if date not current\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Quantity: 0\r\n" +
            "No Data found for given Date\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Quantity: 0\r\n" +
            "No Data found for given Date\r\n" +
            "--------------------\r\n" +
            "Symbol: MSFT\r\n" +
            "Quantity: 0\r\n" +
            "No Data found for given Date\r\n" +
            "Total value of portfolio is $0.0";
    assertEquals(output, test04.value("1998-03-03"));
  }

  @Test
  public void testInspectStock() {
    try {
      assertEquals(1.45, test04.inspectStock(1).calculateDailyGainOrLoss("2024-03-15"), 0.01);
    } catch (Exception e) {
      fail("The test should have passed");
    }
  }

  @Test
  public void testGraphCorrect() {
    String output = "Performance of portfolio 04test from 2023-01-01 to 2024-03-31\n" +
            "\n" +
            "JAN2023: \n" +
            "FEB2023: \n" +
            "MAR2023: \n" +
            "APR2023: \n" +
            "MAY2023: \n" +
            "JUN2023: \n" +
            "JUL2023: \n" +
            "AUG2023: \n" +
            "SEP2023: \n" +
            "OCT2023: \n" +
            "NOV2023: \n" +
            "DEC2023: \n" +
            "JAN2024: *****************************\n" +
            "FEB2024: ******************************\n" +
            "MAR2024: ******************************\n" +
            "\n" +
            "Scale: * = " +
            "\n";
    try {
      assertEquals(output, test04.graph("2023-01-01", "2024-03-31", "monthly"));
    } catch (Exception e) {
      fail("The test should have passed");
    }
  }

  @Test
  public void testGraphIncorrect() {
    try {
      test04.graph("2024-03-31", "2023-01-01", "monthly");
      fail("The test should have failed");
    } catch (Exception e) {
      assertEquals("Start date cannot be after end date", e.getMessage());
    }
  }

  @Test
  public void testGraphIncorrectLess() {
    try {
      test04.graph("2023-03-01", "2023-04-01", "monthly");
      fail("The test should have failed");
    } catch (Exception e) {
      assertEquals("Cannot build graph for less than 5 months", e.getMessage());
    }
  }

  @Test
  public void testGraphIncorrectMore() {
    try {
      test04.graph("2015-03-01", "2023-01-01", "monthly");
      fail("The test should have failed");
    } catch (Exception e) {
      assertEquals("Cannot build graph for more than 30 months", e.getMessage());
    }
  }

  @Test
  public void testCostBasis() {
    assertEquals("Total cost basis by 2024-03-03: $37060.00", test04.costBasis("2024-03-03"));
  }

  @Test
  public void testBuySell() {
    if (manager.createPortfolio("test")) {
      assertEquals("Successfully added stock", manager.addStock("aapl",
              100, "2024-01-01"));
      assertEquals("Portfolio saved to test.xml", manager.savePortfolio());
      assertEquals(8, manager.returnPortfolioList().length);
      try {
        FlexiblePortfolioInterface test = manager.fetchFlexiblePortfolio(5);
        String buy = "Portfolio Name: test\r\n" +
                "--------------------\r\n" +
                "Symbol: AAPL\r\n" +
                "Transaction Dates: 2024-01-01,2024-03-03\r\n" +
                "Transaction Quantities: 100.0,50.0\r\n" +
                "Quantity: 150.0\r\n";
        assertEquals("Portfolio saved to test.xml",
                test.buyExisting(1, "2024-03-03", 50));
        assertEquals(buy, test.composition());
        String sell = "Portfolio Name: test\r\n" +
                "--------------------\r\n" +
                "Symbol: AAPL\r\n" +
                "Transaction Dates: 2024-01-01,2024-03-03,2024-03-07\r\n" +
                "Transaction Quantities: 100.0,50.0,-75.0\r\n" +
                "Quantity: 75.0\r\n";
        assertEquals("Portfolio saved to test.xml",
                test.sellExisting(1, "2024-03-07", 75));
        assertEquals(sell, test.composition());
      } catch (Exception e) {
        fail("Test should not have failed");
      }
      assertEquals("Delete Successful", manager.deletePortfolio(5));
      assertEquals(7, manager.returnPortfolioList().length);
    }
  }

  @Test
  public void testBuyIncorrect() {
    if (manager.createPortfolio("test")) {
      assertEquals("Successfully added stock", manager.addStock("aapl",
              100, "2024-01-01"));
      assertEquals("Portfolio saved to test.xml", manager.savePortfolio());
      assertEquals(8, manager.returnPortfolioList().length);
      try {
        FlexiblePortfolioInterface test = manager.fetchFlexiblePortfolio(5);
        assertEquals("Cannot buy negative stocks",
                test.buyExisting(1, "2024-03-03", -50));
        assertEquals("Can only Buy more stock after the date of previous transaction",
                test.buyExisting(1, "2023-03-07", 75));
      } catch (Exception e) {
        fail("Test should not have failed");
      }
      assertEquals("Delete Successful", manager.deletePortfolio(5));
      assertEquals(7, manager.returnPortfolioList().length);
    }
  }

  @Test
  public void testSellIncorrect() {
    if (manager.createPortfolio("test")) {
      assertEquals("Successfully added stock", manager.addStock("aapl",
              100, "2024-01-01"));
      assertEquals("Portfolio saved to test.xml", manager.savePortfolio());
      assertEquals(8, manager.returnPortfolioList().length);
      try {
        FlexiblePortfolioInterface test = manager.fetchFlexiblePortfolio(5);
        assertEquals("Cannot sell negative stocks",
                test.sellExisting(1, "2024-03-03", -50));
        assertEquals("Can only Sell more stock after the date of previous transaction",
                test.sellExisting(1, "2023-03-07", 75));
      } catch (Exception e) {
        fail("Test should not have failed");
      }
      assertEquals("Delete Successful", manager.deletePortfolio(5));
      assertEquals(7, manager.returnPortfolioList().length);
    }
  }
  
  @Test
  public void testSellIncorrectQuantity() {
    if (manager.createPortfolio("test")) {
      assertEquals("Successfully added stock", manager.addStock("aapl",
              100, "2024-01-01"));
      assertEquals("Portfolio saved to test.xml", manager.savePortfolio());
      assertEquals(8, manager.returnPortfolioList().length);
      try {
        FlexiblePortfolioInterface test = manager.fetchFlexiblePortfolio(5);
        assertEquals("Portfolio saved to test.xml",
                test.sellExisting(1, "2024-03-03", 50));
        assertEquals("Cannot sell more stocks than you have",
                test.sellExisting(1, "2023-03-07", 75));
      } catch (Exception e) {
        fail("Test should not have failed");
      }
      assertEquals("Delete Successful", manager.deletePortfolio(5));
      assertEquals(7, manager.returnPortfolioList().length);
    }
  }

}