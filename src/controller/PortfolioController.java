package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.FlexiblePortfolioInterface;
import model.StockInterface;
import model.Strategy;
import view.FlexibleViewInterface;

/**
 * Controls the operations of a stock portfolio, facilitating actions like buying and selling stocks
 * ,viewing portfolio composition, and more. This controller uses a combination of portfolio model
 * and view interface for operations and interactions.
 */
public class PortfolioController extends AbstractController {
  private final String name;
  private final FlexiblePortfolioInterface portfolio;

  private final FlexibleViewInterface view;

  private final Scanner input;

  /**
   * Constructs a PortfolioController instance with a specified portfolio name, portfolio interface,
   * view interface, and input scanner.
   *
   * @param name      The name of the portfolio.
   * @param portfolio The portfolio model interface for managing stock transactions.
   * @param view      The view interface for user interaction and display.
   * @param input     The scanner for reading user input.
   */
  protected PortfolioController(String name, FlexiblePortfolioInterface portfolio,
                                FlexibleViewInterface view, Scanner input) {
    this.name = name;
    this.portfolio = portfolio;
    this.view = view;
    this.input = input;
  }

  /**
   * Presents and handles the portfolio menu, allowing the user to perform various operations.
   * Loops until the user chooses to exit.
   *
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  protected void portfolioMenu() throws IOException {
    while (true) {
      view.displayPortfolioMenuOptions(name);
      switch (input.next()) {
        case "1":
          view.displayMessage(buyStock());
          view.continueMessage();
          input.next();
          break;
        case "2":
          view.displayMessage(sellStock());
          view.continueMessage();
          input.next();
          break;
        case "3":
          view.displayMessage(portfolio.composition());
          view.continueMessage();
          input.next();
          break;
        case "4":
          view.displayMessage(viewValue());
          view.continueMessage();
          input.next();
          break;
        case "5":
          view.displayMessage(buildGraph());
          view.continueMessage();
          input.next();
          break;
        case "6":
          view.displayMessage(costBasis());
          view.continueMessage();
          input.next();
          break;
        case "7":
          inspectStock();
          break;
        case "8":
          view.displayMessage(investDAC());
          view.continueMessage();
          input.next();
          break;
        case "9":
          view.displayMessage(strategySet());
          view.continueMessage();
          input.next();
          break;
        case "0":
          view.displayMessage("Going back to main menu");
          return;
        default:
          view.displayMessage("Enter a valid input");
          break;
      }
    }
  }

  private String investDAC() throws IOException {
    portfolio.update();
    view.displayOptionHeader("Add Dollar Cost Averaging");
    try {
      view.displayMessage("Enter Amount you want to invest\nEnter 0 to exit");
      int amount = input.nextInt();
      if (amount == 0) {
        return "Going Back to main Menu";
      }
      view.displayMessage("\n--To add stock, enter the symbol of the stock.\nEnter 0 to exit");
      List<String> stocklist = portfolio.listStocks();
      List<Integer> stockpercent = new ArrayList<>();
      List<String> stockdate = new ArrayList<>();
      int count = 0;
      for (String stock : stocklist) {
        view.displayMessage("Enter the percentage you want to invest in: " + stock);
        try {
          int stockPercentage = input.nextInt();
          view.displayMessage("Enter Buying Date of stock");
          String date = input.next();
          if (checkDate(date)) {
            stockpercent.add(stockPercentage);
            stockdate.add(date);
            count++;
          } else {
            throw new IllegalArgumentException("ERROR: Invalid 'Date' input, try adding "
                    + "stock again.");
          }

        } catch (InputMismatchException e) {
          view.displayMessage("ERROR: Invalid 'Percentage' input, try adding stock again.");
        } catch (IllegalArgumentException e) {
          view.displayMessage(e.getMessage());
        }
        view.displayMessage("Enter new stock symbol, or 0 to save and exit");
      }
      if (stockpercent.stream().reduce(0, Integer::sum) == 100) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
          result.append("For stock ").append(stocklist.get(i)).append(" ")
                  .append(portfolio.buyExistingPrice(i, stockdate.get(i),
                          (float) (amount / 100) * stockpercent.get(i))).append("\n");
        }
        return String.valueOf(result);
      } else {
        return "The percentages do not add upto 100";
      }
    } catch (InputMismatchException e) {
      return "Invalid Amount";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  private String strategySet() throws IOException {
    view.displayOptionHeader("Add Strategy");
    try {
      view.displayMessage("Enter Amount you want to invest\nEnter 0 to exit");
      int amount = input.nextInt();
      if (amount == 0) {
        return "Going Back to main Menu";
      }
      view.displayMessage("Enter Start Date for strategy\nEnter 0 to exit");
      String startDate = input.next();
      if ("0".equals(startDate)) {
        return "Going Back to main Menu";
      } else if (!checkDate(startDate)) {
        return "Invalid date, going back to main menu";
      } else if (!portfolio.checkStartDate(startDate)) {
        return "Start date should start after all previous transaction end";
      }
      view.displayMessage("Enter End Date for strategy\nEnter 0 to exit"
              + "\n Enter 1 to make strategy continuing");
      String endDate = input.next();
      if ("0".equals(endDate)) {
        return "Going Back to main Menu";
      } else if (!endDate.equals("1")) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        parser.setLenient(false);
        try {
          parser.parse(endDate);
        } catch (Exception e) {
          return "Invalid date, going back to main menu";
        }
      }
      view.displayMessage("Enter period in which you want to invest\nEnter 0 to exit");
      int period = input.nextInt();
      if (period == 0) {
        return "Going Back to main Menu";
      }
      List<String> stocklist = portfolio.listStocks();
      List<Integer> stockpercent = new ArrayList<>();
      int count = 0;
      for (String stock : stocklist) {
        try {
          view.displayMessage("Enter the percentage you want to invest in stock:" + stock);
          int stockPercentage = input.nextInt();
          stockpercent.add(stockPercentage);
          count++;
        } catch (InputMismatchException e) {
          view.displayMessage("ERROR: Invalid 'Percentage' input, try adding stock again.");
        } catch (IllegalArgumentException e) {
          view.displayMessage(e.getMessage());
        }
        view.displayMessage("Enter new stock symbol, or 0 to save and exit");
      }
      if (stockpercent.stream().reduce(0, Integer::sum) == 100) {
        List<Float> prices = new ArrayList<>();
        for (int i = 0; i < count; i++) {
          prices.add((float) (amount / 100) * stockpercent.get(i));
        }
        Strategy strategy = new Strategy(startDate, endDate, period, stocklist, prices);
        strategy.saveStrategy(name);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
          result.append("For stock ").append(stocklist.get(i)).append(" ")
                  .append(portfolio.buyExistingPrice(i, startDate, prices.get(i))).append("\n");
        }
        return String.valueOf(result);
      } else {
        return "The percentages do not add upto 100";
      }
    } catch (InputMismatchException e) {
      return "Invalid Amount";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * Handles the inspection of a specific stock within the portfolio, allowing for detailed
   * operations on the selected stock including Calculate Gain or Loss, Moving Crossovers
   * and many more.
   *
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  private void inspectStock() throws IOException {
    List<String> fileList = portfolio.listStocks();

    view.displayOptionHeader("inspect a stock in");
    view.getStockInput(fileList, "inspect a stock in");
    try {
      int number = input.nextInt();
      StockInterface stock = portfolio.inspectStock(number);
      String name = fileList.get(number - 1);
      StockController stockController = new StockController(name, stock, view, input);
      stockController.stockMenu();
    } catch (Exception e) {
      view.displayMessage("Invalid Input");
    }
  }

  /**
   * Initiates the process for viewing the cost basis of the portfolio as of a specified date.
   *
   * @return A string message indicating the result of the cost basis operation.
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  private String costBasis() throws IOException {
    view.displayOptionHeader("view the cost basis of");
    while (true) {
      view.getDateInput();
      String date = input.next();
      if (checkDate(date)) {
        return portfolio.costBasis(date);
      } else if (date.equals("0")) {
        return "Going Back to portfolio menu";
      } else {
        view.displayMessage("You have input an invalid date");
      }
    }
  }

  /**
   * Facilitates the creation and display of a graph visualizing portfolio performance over
   * a user-defined period.
   * This method prompts for and validates start and end dates, allows the user to select the
   * data granularity (daily, weekly, monthly), and then generates a visual representation based
   * on these parameters. Error handling is integrated to manage invalid dates or selections,
   * with the ability to return to previous menus as needed.
   * The process repeats until valid inputs are provided or the user opts to exit.
   *
   * @return A string that either represents the generated graph
   * @throws IOException if issues arise during input/output operations, especially in
   *                     interactions with the user.
   */
  private String buildGraph() throws IOException {
    List<String> choices = Arrays.asList("daily", "weekly", "monthly");
    view.displayOptionHeader("view the graph of");
    while (true) {
      view.getDateInput();
      view.displayMessage("Enter the Starting Date for the period for the graph: ");
      String date1 = input.next();
      if (checkDate(date1)) {
        view.getDateInput();
        view.displayMessage("Enter the Ending Date for the period for the graph: ");
        String date2 = input.next();
        if (checkDate(date2)) {
          try {
            view.getGraphInput();
            int x = input.nextInt();
            if (x == 1 || x == 2 || x == 3) {
              return portfolio.graph(date1, date2, choices.get(x - 1));
            } else if (x == 0) {
              return "Going Back to stock menu";
            } else {
              throw new InputMismatchException();
            }
          } catch (InputMismatchException e) {
            return "Invalid Input";
          } catch (Exception e) {
            return e.getMessage();
          }
        } else if (date2.equals("0")) {
          return "Going Back to portfolio menu";
        } else {
          view.displayMessage("You have input an invalid date");
        }
      } else if (date1.equals("0")) {
        return "Going Back to portfolio menu";
      } else {
        view.displayMessage("You have input an invalid date");
      }
    }
  }

  /**
   * Facilitates viewing the current value of the portfolio as of a specified date.
   *
   * @return A string message indicating the portfolio value as of the specified date.
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  private String viewValue() throws IOException {
    view.displayOptionHeader("view the value of");
    while (true) {
      view.getDateInput();
      String date = input.next();
      if (checkDate(date)) {
        return portfolio.value(date);
      } else if (date.equals("0")) {
        return "Going Back to portfolio menu";
      } else {
        view.displayMessage("You have input an invalid date");
      }
    }
  }

  /**
   * Facilitates the selling of stocks within the portfolio. It displays the list of stocks
   * available for sale,
   * prompts the user for the selection of a specific stock and the quantity to sell,
   * and the date of the sale.
   * The method ensures that valid inputs are provided and performs the sale operation accordingly.
   *
   * @return A string message indicating the outcome of the sell operation
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  private String sellStock() throws IOException {
    List<String> fileList = portfolio.listStocks();

    view.displayOptionHeader("sell a stock in");
    view.getStockInput(fileList, "sell a stock in");
    try {
      int number = input.nextInt();
      if (number != 0) {
        while (true) {
          view.getDateInput();
          String date = input.next();
          if (checkDate(date)) {
            int quantity = input.nextInt();
            if (quantity > 0) {
              return "Updated" + portfolio.sellExisting(number, date, quantity);
            } else {
              return "Enter positive integer value";
            }

          } else if (date.equals("0")) {
            return "Going Back to portfolio menu";
          } else {
            view.displayMessage("You have input an invalid date");
          }
        }
      } else {
        return "Going Back to portfolio menu";
      }
    } catch (Exception e) {
      return "Invalid Input";
    }
  }

  /**
   * Facilitates the buying of additional stocks within the portfolio.
   * Prompts the user for the selection of a specific stock and the quantity to buy, and the
   * date of the purchase.
   * The method ensures that valid inputs are provided and performs the buy operation accordingly.
   *
   * @return A string message indicating the outcome of the buy operation
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  private String buyStock() throws IOException {
    List<String> fileList = portfolio.listStocks();

    view.displayOptionHeader("buy more stock in");
    view.getStockInput(fileList, "buy more stock in");
    try {
      int number = input.nextInt();
      if (number != 0) {
        while (true) {
          view.getDateInput();
          String date = input.next();
          if (checkDate(date)) {
            int quantity = input.nextInt();
            if (quantity > 0) {
              return "Updated" + portfolio.buyExisting(number, date, quantity);
            } else {
              return "Enter positive integer value";
            }

          } else if (date.equals("0")) {
            return "Going Back to portfolio menu";
          } else {
            view.displayMessage("You have input an invalid date");
          }
        }
      } else {
        return "Going Back to portfolio menu";
      }
    } catch (Exception e) {
      return "Invalid Input";
    }
  }
}
