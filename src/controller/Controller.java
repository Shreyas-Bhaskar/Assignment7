package controller;

import java.io.IOException;
import java.util.Scanner;

import model.ManagerInterface;
import view.ViewInterface;

/**
 * This class represents the controller in an MVC architecture, responsible for
 * handling user input and coordinating interactions between the view and the model.
 */
public class Controller extends AbstractController implements ControllerInterface {
  protected final Scanner input;
  protected final ManagerInterface model;
  protected final ViewInterface view;

  /**
   * Constructs a Controller object with specified model and view.
   *
   * @param model the model interface to interact with the data layer
   * @param view  the view interface to interact with the user interface
   * @param in    takes in the way the controller will receive an input.
   */
  public Controller(ManagerInterface model, ViewInterface view, Readable in) {
    this.model = model;
    this.view = view;
    this.input = new Scanner(in);
  }


  /**
   * Displays the main menu and processes user input to navigate through the application's
   * functionality.
   * This includes creating, viewing, and modifying portfolios.
   *
   * @throws IOException if an I/O error occurs.
   */
  @Override
  public void mainMenu() throws IOException {
    while (true) {
      view.displayMenuOptions();
      switch (input.next()) {
        case "1":
          view.displayMessage(addPortfolio());
          view.continueMessage();
          input.next();
          break;
        case "2":
          view.displayMessage(viewCompositionPortfolio());
          view.continueMessage();
          input.next();
          break;
        case "3":
          view.displayMessage(viewValuePortfolio());
          view.continueMessage();
          input.next();
          break;
        case "4":
          view.displayMessage(removePortfolio());
          view.continueMessage();
          input.next();
          break;
        case "5":
          view.displayMessage(updatePortfolio());
          view.continueMessage();
          input.next();
          break;
        case "0":
          view.displayMessage("Exiting Application");
          input.close();
          return;
        default:
          view.displayMessage("Enter a valid input");
          break;
      }
    }
  }

  /**
   * Guides the user through adding a new portfolio,
   * including the option to add stocks to the portfolio.
   *
   * @return a string indicating the outcome of the add operation
   * @throws IOException if an I/O error occurs.
   */
  private String addPortfolio() throws IOException {
    view.displayOptionHeader("Add");
    view.displayMessage("Enter the portfolio name, or enter 0 to go back:");
    String portfolioName = input.next();
    if ("0".equals(portfolioName)) {
      return "Going Back to Main Menu";
    }

    try {
      if (model.createPortfolio(portfolioName)) {
        view.displayMessage("Portfolio created successfully. To add stock, enter " +
                "the symbol of the stock. To exit, enter 0.");
        String stockSymbol;
        while (true) {
          stockSymbol = input.next();
          if ("0".equals(stockSymbol)) {
            break;
          }
          view.displayMessage("Enter quantity of stock:");
          try {
            int stockQuantity = input.nextInt();
            String addStockResult = model.addStock(stockSymbol, stockQuantity);
            view.displayMessage(addStockResult);
          } catch (Exception e) {
            view.displayMessage("Invalid input for stock quantity. Please try again:");
            input.nextLine();
          }
          view.displayMessage("Enter new stock symbol, or 0 to save and exit");
        }
        return model.savePortfolio();
      } else {
        return "Failed to create portfolio. Invalid portfolio name.";
      }
    } catch (Exception e) {
      return e.getMessage();
    }
  }


  /**
   * Displays the composition of a selected portfolio.
   *
   * @return a string representation of the portfolio's composition or an error message
   * @throws IOException if an I/O error occurs.
   */
  private String viewCompositionPortfolio() throws IOException {
    String[] fileList = model.returnPortfolioList();
    if (fileList == null) {
      return "No portfolio to view";
    }
    view.displayOptionHeader("view composition of");
    view.getPortfolioInput(fileList, "view composition of");
    try {
      return model.fetchPortfolio(input.nextInt()).composition();
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * Allows the user to view the value of a portfolio on a specified date.
   *
   * @return a string representing the portfolio's value or an error message
   * @throws IOException if an I/O error occurs.
   */
  private String viewValuePortfolio() throws IOException {
    String[] fileList = model.returnPortfolioList();
    if (fileList == null) {
      return "No portfolio to view";
    }
    view.displayOptionHeader("view value of");
    while (true) {
      view.getDateInput();
      String date = input.next();
      if (checkDate(date)) {
        view.getPortfolioInput(fileList, "view value on selected date of");
        try {
          return model.fetchPortfolio(input.nextInt()).value(date);
        } catch (Exception e) {
          return e.getMessage();
        }
      } else if (date.equals("0")) {
        return "Going Back to main menu";
      } else {
        view.displayMessage("You have input an invalid date");
      }
    }
  }

  /**
   * Facilitates the removal of a selected portfolio.
   *
   * @return a string indicating the outcome of the removal operation
   * @throws IOException if an I/O error occurs.
   */
  protected String removePortfolio() throws IOException {
    String[] fileList = model.returnPortfolioList();
    if (fileList == null) {
      return "No portfolio to remove";
    }
    view.displayOptionHeader("Remove");
    view.getPortfolioInput(fileList, "Remove");
    try {
      return model.deletePortfolio(input.nextInt());
    } catch (Exception e) {
      return "Invalid Input";
    }
  }

  /**
   * Supports updating the stock values within a selected portfolio.
   *
   * @return a string detailing the outcome of the update operation
   * @throws IOException if an I/O error occurs.
   */
  protected String updatePortfolio() throws IOException {
    String[] fileList = model.returnPortfolioList();
    if (fileList == null) {
      return "No portfolio to update";
    }
    view.displayOptionHeader("Update");
    view.getPortfolioInput(fileList, "Update");
    try {
      return model.fetchPortfolio(input.nextInt()).update();
    } catch (Exception e) {
      return "Invalid Input";
    }
  }
}
