import org.junit.Before;
import org.junit.Test;

import model.Manager;
import model.ManagerInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * The `ManagerTest` class evaluates the functionality of the `Manager` component
 * in a stock portfolio management application. It includes tests for creating, listing,
 * adding stocks to, saving, and deleting portfolios, ensuring the component behaves as expected
 * under various scenarios.
 */
public class ManagerTest {

  ManagerInterface manager;

  @Before
  public void setUp() {
    manager = new Manager();
  }

  @Test
  public void testPortfolioList() {
    String[] list = {"01test.xml", "02test.xml", "03test.xml"};
    assertEquals(list[0], manager.returnPortfolioList()[0]);
    assertEquals(list[1], manager.returnPortfolioList()[1]);
    assertEquals(list[2], manager.returnPortfolioList()[2]);
  }

  @Test
  public void testCreatePortfolio() {
    assertFalse(manager.createPortfolio("0"));
    assertTrue(manager.createPortfolio("test"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatePortfolioError() {
    manager.createPortfolio("%");
  }

  @Test
  public void testAddStock() {
    assertTrue(manager.createPortfolio("test"));
    try {
      assertEquals("Can't save for stock: aaaa - Error: Incorrect " +
              "Stock Symbol\r\n", manager.addStock("aaaa", 100));
      assertEquals("Successfully added stock", manager.addStock(
              "aapl", 100));
    } catch (Exception e) {
      assertEquals("Can't save for stock: aaaa - Error: Exceeded number of API Calls," +
              " try again tomorrow", e.getMessage());
    }
  }

  @Test
  public void testSavePortfolioFail() {
    assertTrue(manager.createPortfolio("test"));
    assertEquals("Cannot Create, No Stock in portfolio", manager.savePortfolio());
  }

  @Test
  public void testFetchMainMenu() {
    try {
      manager.fetchPortfolio(0);
    } catch (Exception e) {
      assertEquals("Going back to main menu", e.getMessage());
    }
  }

  @Test
  public void testFetchIncorrect() {
    try {
      manager.fetchPortfolio(5);
    } catch (Exception e) {
      assertEquals("Input portfolio number does not exist", e.getMessage());
    }
  }

  @Test
  public void testFetchCorrect() {
    String output = "Portfolio Name: 02test\r\n" +
            "--------------------\r\n" +
            "Symbol: AAPL\r\n" +
            "Quantity: 100\r\n" +
            "--------------------\r\n" +
            "Symbol: GOOG\r\n" +
            "Quantity: 100\r\n" +
            "--------------------\r\n" +
            "Symbol: MSFT\r\n" +
            "Quantity: 100\r\n";
    try {
      assertEquals(output, manager.fetchPortfolio(2).composition());
    } catch (Exception ignore) {
      fail("Failed to fetch portfolios");
    }
  }

  @Test
  public void testDeleteFail() {
    assertEquals("Input portfolio number does not exist", manager.deletePortfolio(5));
    assertEquals("Going back to main menu", manager.deletePortfolio(0));
  }

  @Test
  public void testSaveAndDelete() {
    if (manager.createPortfolio("test")) {
      assertEquals("Successfully added stock", manager.addStock("aapl",
              100));
      assertEquals("Portfolio saved to test.xml", manager.savePortfolio());
      assertEquals(5, manager.returnPortfolioList().length);
      assertEquals("Delete Successful", manager.deletePortfolio(5));
      assertEquals(4, manager.returnPortfolioList().length);
    }
  }

}
