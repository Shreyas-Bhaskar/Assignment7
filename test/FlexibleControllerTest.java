import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import controller.ControllerInterface;
import controller.FlexibleController;
import model.FlexibleManagerInterface;
import model.MockFlexibleManager;
import view.FlexibleView;
import view.FlexibleViewInterface;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller component of an MVC application that manages stock portfolios.
 * It sets up mock objects for the model and view, along with a controlled input and output stream,
 * to simulate user interaction and test the controller's response to various inputs.
 * Each test case represents a different user action, verifying the integration and functionality
 * of the controller within the application framework.
 */
public class FlexibleControllerTest {
  ControllerInterface test;
  FlexibleManagerInterface model;
  FlexibleViewInterface view;
  Reader in;
  StringBuffer out;
  StringBuilder log;

  @Before
  public void setUp() {
    out = new StringBuffer();
    view = new FlexibleView(out);
    log = new StringBuilder();
    model = new MockFlexibleManager(log, 12345);
  }

  @Test
  public void testExit() throws IOException {
    in = new StringReader("0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testAdd() throws IOException {
    in = new StringReader("1 portfolio symbol 100 2022-03-04 0 q 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached createPortfolio Reached addStock symbol 100 2022-03-04 " +
            "Reached savePortfolio", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Add a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "Enter the portfolio name, or enter 0 to go back:\n" +
            "Portfolio created successfully.\n" +
            "\n" +
            "\n" +
            "--To add stock, enter the symbol of the stock.\n" +
            "Enter 0 to exit\n" +
            "Enter quantity of stock:\n" +
            "Enter Buying Date of stock\n" +
            "12345\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testAddIncorrectDate() throws IOException {
    in = new StringReader("1 portfolio symbol 100 2022-15-04 0 q 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached createPortfolio Reached savePortfolio", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Add a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "Enter the portfolio name, or enter 0 to go back:\n" +
            "Portfolio created successfully.\n" +
            "\n" +
            "\n" +
            "--To add stock, enter the symbol of the stock.\n" +
            "Enter 0 to exit\n" +
            "Enter quantity of stock:\n" +
            "Enter Buying Date of stock\n" +
            "ERROR: Invalid 'Date' input, try adding stock again.\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testRemove() throws IOException {
    in = new StringReader("3 1 q 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached deletePortfolio 1", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Remove a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To Remove a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testUpdate() throws IOException {
    in = new StringReader("4 1 q 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 " +
            "Reached Update", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Update a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To Update a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testManageExit() throws IOException {
    in = new StringReader("2 1 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testManageBuy() throws IOException {
    in = new StringReader("2 1 1 1 2024-01-01 100 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached " +
            "listStocksReached buyExisting 1 2024-01-01 100 ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to buy more stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To buy more stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Updated12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testManageSell() throws IOException {
    in = new StringReader("2 1 2 1 2024-01-01 100 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached " +
            "listStocksReached sellExisting 1 2024-01-01 100 ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to sell a stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To sell a stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Updated12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testViewComposition() throws IOException {
    in = new StringReader("2 1 3 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 " +
            "Reached Composition", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testViewValue() throws IOException {
    in = new StringReader("2 1 4 2024-01-01 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached " +
            "fetchPortfolio 1 Reached Value", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view the value of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testGraphDaily() throws IOException {
    in = new StringReader("2 1 5 2024-01-01 2024-01-02 1 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached graph " +
            "2024-01-01 2024-01-02 daily ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view the graph of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Starting Date for the period for the graph: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Ending Date for the period for the graph: \n" +
            "Enter choice for type of graph:\n" +
            "---- [1] To build daily graph\n" +
            "---- [2] To build weekly graph\n" +
            "---- [3] To build monthly graph\n" +
            "---- [0] To go back to portfolio menu\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testGraphWeekly() throws IOException {
    in = new StringReader("2 1 5 2024-01-01 2024-01-02 2 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached graph " +
            "2024-01-01 2024-01-02 weekly ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view the graph of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Starting Date for the period for the graph: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Ending Date for the period for the graph: \n" +
            "Enter choice for type of graph:\n" +
            "---- [1] To build daily graph\n" +
            "---- [2] To build weekly graph\n" +
            "---- [3] To build monthly graph\n" +
            "---- [0] To go back to portfolio menu\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testGraphMonthly() throws IOException {
    in = new StringReader("2 1 5 2024-01-01 2024-01-02 3 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached graph " +
            "2024-01-01 2024-01-02 monthly ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view the graph of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Starting Date for the period for the graph: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Ending Date for the period for the graph: \n" +
            "Enter choice for type of graph:\n" +
            "---- [1] To build daily graph\n" +
            "---- [2] To build weekly graph\n" +
            "---- [3] To build monthly graph\n" +
            "---- [0] To go back to portfolio menu\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testCostBasis() throws IOException {
    in = new StringReader("2 1 6 2024-01-01 q 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached " +
            "costBasis 2024-01-01 ", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view the cost basis of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testCalculateDailyGainOrLoss() throws IOException {
    in = new StringReader("2 1 7 1 1 2024-03-15 0 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached " +
            "listStocksReached inspectStock1Reached " +
            "calculateDailyGainOrLoss2024-03-15", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to inspect a stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To inspect a stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen th option Daily Gain Loss ----------\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "The stock gained $12345.0 on date 2024-03-15\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testCalculatePeriodGainOrLoss() throws IOException {
    in = new StringReader("2 1 7 1 2 2023-02-02 2023-03-02 0 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 " +
            "Reached listStocksReached inspectStock1Reached calculatePeriodGainOrLoss " +
            "with startDate: 2023-02-02, endDate: 2023-03-02\n", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to inspect a stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To inspect a stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen th option Period Gain Loss ----------\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Starting Date for the period: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Ending Date for the period: \n" +
            "The stock gained $12345.0 between 2023-02-02 and 2023-03-02\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testCalculateMovingAverage() throws IOException {
    in = new StringReader("2 1 7 1 3 10 2023-02-02 0 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached listStocksReached"
            + " inspectStock1Reached calculateMovingAverage2023-02-0210", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to inspect a stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To inspect a stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen th option Period Gain Loss ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter the Number of days for which you want to calculate moving average: \n" +
            "--- Enter 0 to go back to stock menu\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "The 10Day Moving Average on 2023-02-02 is $12345.0\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testCrossovers() throws IOException {
    in = new StringReader("2 1 7 1 4 2022-01-01 2023-02-02 0 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached " +
            "listStocksReached inspectStock1Reached findCrossovers with startDate: " +
            "2022-01-01, endDate: 2023-02-02\n", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to inspect a stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To inspect a stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen th option Period Crossover ----------\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Starting Date for the period: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Ending Date for the period: \n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testMovingCrossovers() throws IOException {
    in = new StringReader("2 1 7 1 5 2022-01-01 2023-02-02 30 100 0 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 Reached " +
            "listStocksReached inspectStock1Reached findMovingCrossovers with startDate:" +
            " 2022-01-01, endDate: 2023-02-02, x: 30, y: 100\n", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to manage a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To manage a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to inspect a stock in a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To inspect a stock in the portfolio, enter stock number\n" +
            "---- Following is your list of stocks in the portfolio: ----\n" +
            "[1] 12345\n" +
            "Enter input: \n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen th option Period Moving Crossover ----------\n" +
            "-------------------------------------------------------\n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Starting Date for the period: \n" +
            "---- Enter the date for which you want to perform action: ----\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- The date should be either current date or before current date\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "Enter the Ending Date for the period: \n" +
            "Enter the Number of days for Short Term Moving Average: \n" +
            "--- Enter 0 to go back to stock menu\n" +
            "Enter the Number of days for Short Term Moving Average: \n" +
            "--- Enter 0 to go back to stock menu\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen stock 12345 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To display Gain Loss for specified date\n" +
            "---- [2] To display Gain Loss for specified period\n" +
            "---- [3] To display Moving Average for specified date\n" +
            "---- [4] To display Crossover for specified period\n" +
            "---- [5] To display Moving Crossover for specified period\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- You have chosen portfolio 1 ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To buy more stock\n" +
            "---- [2] To sell a stock\n" +
            "---- [3] To view portfolio composition\n" +
            "---- [4] To view portfolio value\n" +
            "---- [5] To view portfolio graph\n" +
            "---- [6] To view portfolio cost basis\n" +
            "---- [7] To inspect a stock in portfolio\n" +
            "---- [0] To go back to main menu\n" +
            "Enter option number to select option:\n" +
            "Going back to main menu\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testDollarCostAveraging() throws IOException {
    in = new StringReader("5 test11 2000 GOOG 40 2024-03-03 AAPL 60 2024-03-03 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached createPortfolio Reached addStock GOOG 800.0 2024-03-03" +
            " Reached addStock AAPL 1200.0 2024-03-03 Reached savePortfolio", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [5] To add a portfolio with dollar cost averaging\n" +
            "---- [6] To add a portfolio with startegy\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Add Dollar Cost Averaging a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "Enter the portfolio name, or enter 0 to go back:\n" +
            "Portfolio created successfully.\n" +
            "\n" +
            "Enter Amount you want to invest\n" +
            "Enter 0 to exit\n" +
            "\n" +
            "--To add stock, enter the symbol of the stock.\n" +
            "Enter 0 to exit\n" +
            "Enter the percentage you want to invest in stock:\n" +
            "Enter Buying Date of stock\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "Enter the percentage you want to invest in stock:\n" +
            "Enter Buying Date of stock\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [5] To add a portfolio with dollar cost averaging\n" +
            "---- [6] To add a portfolio with startegy\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testStrategy() throws IOException {
    in = new StringReader("6 test11 2000 2024-02-02 2024-02-03 10 GOOG 40 AAPL 60 0 0 0");
    test = new FlexibleController(model, view, in);
    test.mainMenu();
    assertEquals("Reached createPortfolio Reached addStock GOOG 800.0 2024-02-02 Reached" +
            " addStock AAPL 1200.0 2024-02-02 Reached savePortfolio", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [5] To add a portfolio with dollar cost averaging\n" +
            "---- [6] To add a portfolio with startegy\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Add Strategy a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "Enter the portfolio name, or enter 0 to go back:\n" +
            "Portfolio created successfully.\n" +
            "\n" +
            "Enter Amount you want to invest\n" +
            "Enter 0 to exit\n" +
            "Enter Start Date for strategy\n" +
            "Enter 0 to exit\n" +
            "Enter End Date for strategy\n" +
            "Enter 0 to exit\n" +
            " Enter 1 to make strategy continuing\n" +
            "Enter period in which you want to invest\n" +
            "Enter 0 to exit\n" +
            "\n" +
            "--To add stock, enter the symbol of the stock.\n" +
            "Enter 0 to exit\n" +
            "Enter the percentage you want to invest in stock:\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "Enter the percentage you want to invest in stock:\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "Enter corresponding number perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To manage a portfolio\n" +
            "---- [3] To remove a portfolio\n" +
            "---- [4] To update a portfolio\n" +
            "---- [5] To add a portfolio with dollar cost averaging\n" +
            "---- [6] To add a portfolio with startegy\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option:\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }
}
