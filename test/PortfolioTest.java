import org.junit.Before;
import org.junit.Test;

import model.Manager;
import model.ManagerInterface;
import model.PortfolioInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The `PortfolioTest` class assesses the behavior of portfolio management within the application,
 * focusing on creating and interacting with portfolios. It verifies portfolio composition and
 * valuation accuracy for different stocks and dates, and evaluates the system's response to updates
 * and scenarios involving incorrect stock symbols or dates.
 */
public class PortfolioTest {
  ManagerInterface manager;
  PortfolioInterface test01;
  PortfolioInterface test02;
  PortfolioInterface test03;

  @Before
  public void setUp() {
    manager = new Manager();
    try {
      test01 = manager.fetchPortfolio(1);
      test02 = manager.fetchPortfolio(2);
      test03 = manager.fetchPortfolio(3);
    } catch (Exception ignore) {
      fail("Failed to fetch portfolios");
    }
  }

  @Test
  public void testComposition() {
    String output = "Portfolio Name: 03test\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Quantity: 100.0\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Quantity: 100.0\r\n";
    assertEquals(output, test03.composition());
  }

  @Test
  public void testValueCorrect() {
    String output = "Portfolio Name: 02test\r\n" +
            "-- Value Displayed for closest available date in data\r\n" +
            "-- Value will be displayed for last closing price if market close on date\r\n" +
            "-- Update portfolio from main menu if date not current\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Quantity: 100.0\r\n" +
            "Date: 2024-03-01\r\n" +
            "Price: $179.66\r\n" +
            "Value: $17966.0\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Quantity: 100.0\r\n" +
            "Date: 2024-03-01\r\n" +
            "Price: $138.08\r\n" +
            "Value: $13808.0\r\n" +
            "Total value of portfolio is $31774.0";
    assertEquals(output, test02.value("2024-03-03"));
  }

  @Test
  public void testValueIncorrectDate() {
    String output = "Portfolio Name: 03test\r\n" +
            "-- Value Displayed for closest available date in data\r\n" +
            "-- Value will be displayed for last closing price if market close on date\r\n" +
            "-- Update portfolio from main menu if date not current\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Quantity: 100.0\r\n" +
            "No Data found for given Date\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Quantity: 100.0\r\n" +
            "No Data found for given Date\r\n" +
            "Total value of portfolio is $0.0";
    assertEquals(output, test03.value("1998-03-03"));
  }
}
