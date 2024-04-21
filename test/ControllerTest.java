import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import controller.Controller;
import controller.ControllerInterface;
import model.ManagerInterface;
import model.MockManager;
import view.View;
import view.ViewInterface;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller component of an MVC application that manages stock portfolios.
 * It sets up mock objects for the model and view, along with a controlled input and output stream,
 * to simulate user interaction and test the controller's response to various inputs.
 * Each test case represents a different user action, verifying the integration and functionality
 * of the controller within the application framework.
 */
public class ControllerTest {
  ControllerInterface test;
  ManagerInterface model;
  ViewInterface view;
  Reader in;
  StringBuffer out;
  StringBuilder log;

  @Before
  public void setUp() {
    out = new StringBuffer();
    view = new View(out);
    log = new StringBuilder();
    model = new MockManager(log, 12345);
  }

  @Test
  public void testExit() throws IOException {
    in = new StringReader("0");
    test = new Controller(model, view, in);
    test.mainMenu();
    assertEquals("", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testAdd() throws IOException {
    in = new StringReader("1 portfolio symbol 100 0 q 0");
    test = new Controller(model, view, in);
    test.mainMenu();
    assertEquals("Reached createPortfolio Reached addStock symbol 100 " +
            "Reached savePortfolio", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to Add a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "Enter the portfolio name, or enter 0 to go back:\n" +
            "Portfolio created successfully. To add stock, enter the" +
            " symbol of the stock. To exit, enter 0.\n" +
            "Enter quantity of stock:\n" +
            "12345\n" +
            "Enter new stock symbol, or 0 to save and exit\n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testViewComposition() throws IOException {
    in = new StringReader("2 1 q 0");
    test = new Controller(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 " +
            "Reached Composition", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view composition of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "To view composition of a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testViewValue() throws IOException {
    in = new StringReader("3 2024-01-01 1 q 0");
    test = new Controller(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached " +
            "fetchPortfolio 1 Reached Value", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "-------------------------------------------------------\n" +
            "----You have chosen to view value of a portfolio. ----\n" +
            "---- To go back to main menu enter 0 ----\n" +
            "-------------------------------------------------------\n" +
            "Enter the date for which you want to check value of portfolio\n" +
            "---- The date should be in YYYY-MM-DD format\n" +
            "---- To go back to main menu enter 0\n" +
            "Enter date:\n" +
            "To view value on selected date of a portfolio, enter portfolio number\n" +
            "You have the following portfolios\n" +
            "---- Following is your list of portfolios: ----\n" +
            "[1] 1\n" +
            "Enter input: \n" +
            "12345\n" +
            "Enter any input to continue\n" +
            "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testRemove() throws IOException {
    in = new StringReader("4 1 q 0");
    test = new Controller(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached deletePortfolio 1", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
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
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testUpdate() throws IOException {
    in = new StringReader("5 1 q 0");
    test = new Controller(model, view, in);
    test.mainMenu();
    assertEquals("Reached portfolioList Reached fetchPortfolio 1 " +
            "Reached Update", log.toString());
    String output = "-------------------------------------------------------\n" +
            "---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n" +
            "-------------------------------------------------------\n" +
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
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
            "You can perform the following actions: \n" +
            "---- [1] To add a portfolio\n" +
            "---- [2] To view the composition of a portfolio\n" +
            "---- [3] To view the value of a portfolio\n" +
            "---- [4] To remove a portfolio\n" +
            "---- [5] To update a portfolio\n" +
            "---- [0] To exit application\n" +
            "Enter option number to select option\n" +
            "Exiting Application\n";
    assertEquals(output, out.toString());
  }
}
