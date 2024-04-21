package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.FlexibleManagerInterface;
import model.FlexiblePortfolioInterface;
import model.Strategy;
import view.FlexibleViewInterface;

/**
 * This class represents the controller in an MVC architecture, responsible for
 * handling user input and coordinating interactions between the view and the model.
 */
public class FlexibleController extends AbstractController implements ControllerInterface {
  private final Scanner input;
  private final FlexibleManagerInterface model;
  private final FlexibleViewInterface view;

  /**
   * Constructs a Controller object with specified model and view.
   *
   * @param model the model interface to interact with the data layer
   * @param view  the view interface to interact with the user interface
   * @param in    takes in the way the controller will receive an input.
   */
  public FlexibleController(FlexibleManagerInterface model, FlexibleViewInterface view,
                            Readable in) {
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
          managePortfolio();
          break;
        case "3":
          view.displayMessage(removePortfolio());
          view.continueMessage();
          input.next();
          break;
        case "4":
          view.displayMessage(updatePortfolio());
          view.continueMessage();
          input.next();
          break;
        case "5":
          view.displayMessage(addDACPortfolio());
          view.continueMessage();
          input.next();
          break;
        case "6":
          view.displayMessage(addStrategyPortfolio());
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

  private String addStrategyPortfolio() throws IOException {
    view.displayOptionHeader("Add Strategy");
    view.displayMessage("Enter the portfolio name, or enter 0 to go back:");
    String portfolioName = input.next();
    if (!portfolioName.equals("0")) {
      try {
        // create portfolio will throw illegal argument if it fails
        if (model.createPortfolio(portfolioName)) {
          view.displayMessage("Portfolio created successfully.\n");
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
          }
          view.displayMessage("Enter End Date for strategy\nEnter 0 to exit" +
                  "\n Enter 1 to make strategy continuing");
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

          view.displayMessage("\n--To add stock, enter the symbol of the stock.\nEnter 0 to exit");
          List<String> stocklist = new ArrayList<>();
          List<Integer> stockpercent = new ArrayList<>();
          int count = 0;
          while (true) {
            String stockSymbol = input.next();
            if (!stockSymbol.equals("0")) {
              try {
                if (!stocklist.contains(stockSymbol)) {
                  view.displayMessage("Enter the percentage you want to invest in stock:");
                  int stockPercentage = input.nextInt();
                  stocklist.add(stockSymbol);
                  stockpercent.add(stockPercentage);
                  count++;
                } else {
                  throw new IllegalArgumentException("ERROR: Invalid 'Symbol' input, cannot add " +
                          "same symbol twice.");
                }
              } catch (InputMismatchException e) {
                view.displayMessage("ERROR: Invalid 'Percentage' input, try adding stock again.");
              } catch (IllegalArgumentException e) {
                view.displayMessage(e.getMessage());
              }
              view.displayMessage("Enter new stock symbol, or 0 to save and exit");
            } else {
              break;
            }
          }
          if (stockpercent.stream().reduce(0, Integer::sum) == 100) {
            List<Float> prices = new ArrayList<>();
            for (int i = 0; i < count; i++) {
              prices.add((float) (amount / 100) * stockpercent.get(i));
            }
            Strategy strategy = new Strategy(startDate, endDate, period, stocklist, prices);
            strategy.saveStrategy(portfolioName);
            for (int i = 0; i < count; i++) {
              model.addStock(stocklist.get(i), prices.get(i), startDate);
            }
          } else {
            return "The percentages do not add upto 100";
          }
          return model.savePortfolio();
        } else {
          return "ERROR: Invalid portfolio name, Try creating Portfolio again.";
        }
      } catch (InputMismatchException e) {
        return "Invalid Amount";
      } catch (Exception e) {
        return e.getMessage();
      }
    } else {
      // Make a view function for this output
      return "Going Back to main Menu";
    }
  }

  private String addDACPortfolio() throws IOException {
    view.displayOptionHeader("Add Dollar Cost Averaging");
    view.displayMessage("Enter the portfolio name, or enter 0 to go back:");
    String portfolioName = input.next();
    if (!portfolioName.equals("0")) {
      try {
        // create portfolio will throw illegal argument if it fails
        if (model.createPortfolio(portfolioName)) {
          view.displayMessage("Portfolio created successfully.\n");
          view.displayMessage("Enter Amount you want to invest\nEnter 0 to exit");
          int amount = input.nextInt();
          if (amount == 0) {
            return "Going Back to main Menu";
          }
          view.displayMessage("\n--To add stock, enter the symbol of the stock.\nEnter 0 to exit");
          List<String> stocklist = new ArrayList<>();
          List<Integer> stockpercent = new ArrayList<>();
          List<String> stockdate = new ArrayList<>();
          int count = 0;
          while (true) {
            String stockSymbol = input.next();
            if (!stockSymbol.equals("0")) {
              view.displayMessage("Enter the percentage you want to invest in stock:");
              try {
                if (!stocklist.contains(stockSymbol)) {
                  int stockPercentage = input.nextInt();
                  view.displayMessage("Enter Buying Date of stock");
                  String date = input.next();
                  if (checkDate(date)) {
                    stocklist.add(stockSymbol);
                    stockpercent.add(stockPercentage);
                    stockdate.add(date);
                    count++;
                  } else {
                    throw new IllegalArgumentException("ERROR: Invalid 'Date' input, try adding " +
                            "stock again.");
                  }
                } else {
                  throw new IllegalArgumentException("ERROR: Invalid 'Symbol' input, cannot add " +
                          "same symbol twice.");
                }
              } catch (InputMismatchException e) {
                view.displayMessage("ERROR: Invalid 'Percentage' input, try adding stock again.");
              } catch (IllegalArgumentException e) {
                view.displayMessage(e.getMessage());
              }
              view.displayMessage("Enter new stock symbol, or 0 to save and exit");
            } else {
              break;
            }
          }
          if (stockpercent.stream().reduce(0, Integer::sum) == 100) {
            for (int i = 0; i < count; i++) {
              model.addStock(stocklist.get(i), (float) (amount / 100) * stockpercent.get(i),
                      stockdate.get(i));
            }
          } else {
            return "The percentages do not add upto 100";
          }
          return model.savePortfolio();
        } else {
          return "ERROR: Invalid portfolio name, Try creating Portfolio again.";
        }
      } catch (InputMismatchException e) {
        return "Invalid Amount";
      } catch (Exception e) {
        return e.getMessage();
      }
    } else {
      // Make a view function for this output
      return "Going Back to main Menu";
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
    if (!portfolioName.equals("0")) {
      try {
        // create portfolio will throw illegal argument if it fails
        if (model.createPortfolio(portfolioName)) {
          view.displayMessage("Portfolio created successfully.\n");
          view.displayMessage("\n--To add stock, enter the symbol of the stock.\nEnter 0 to exit");
          List<String> stocklist = new ArrayList<>();
          while (true) {
            String stockSymbol = input.next();
            if (!stockSymbol.equals("0")) {
              view.displayMessage("Enter quantity of stock:");
              try {
                if (!stocklist.contains(stockSymbol)) {
                  int stockQuantity = input.nextInt();
                  view.displayMessage("Enter Buying Date of stock");
                  String date = input.next();
                  if (checkDate(date)) {
                    String addStockResult = model.addStock(stockSymbol, stockQuantity, date);
                    stocklist.add(stockSymbol);
                    view.displayMessage(addStockResult);
                  } else {
                    throw new IllegalArgumentException("ERROR: Invalid 'Date' input, try adding " +
                            "stock again.");
                  }
                } else {
                  throw new IllegalArgumentException("ERROR: Invalid 'Symbol' input, cannot add " +
                          "same symbol twice.");
                }
              } catch (InputMismatchException e) {
                view.displayMessage("ERROR: Invalid 'Quantity' input, try adding stock again.");
              } catch (IllegalArgumentException e) {
                view.displayMessage(e.getMessage());
              }
              view.displayMessage("Enter new stock symbol, or 0 to save and exit");
            } else {
              break;
            }
          }
          return model.savePortfolio();
        } else {
          return "ERROR: Invalid portfolio name, Try creating Portfolio again.";
        }
      } catch (Exception e) {
        return e.getMessage();
      }
    } else {
      // Make a view function for this output
      return "Going Back to main Menu";
    }
  }


  /**
   * Manages a selected portfolio by allowing further actions such as viewing, buying, or
   * selling stocks within it.
   * Initiates a submenu for detailed portfolio management after selecting a portfolio.
   *
   * @throws IOException if an I/O error occurs.
   */
  private void managePortfolio() throws IOException {
    String[] fileList = model.returnPortfolioList();
    if (fileList == null) {
      view.displayMessage("No portfolio to Manage");
      return;
    }
    view.displayOptionHeader("manage");
    view.getPortfolioInput(fileList, "manage");
    try {
      int number = input.nextInt();
      FlexiblePortfolioInterface portfolio = model.fetchFlexiblePortfolio(number);
      portfolio.useStrategy();
      String name = fileList[number - 1];
      name = name.replace(name.substring(name.length() - 4), "");
      PortfolioController portfolioController
              = new PortfolioController(name, portfolio, view, input);
      portfolioController.portfolioMenu();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }

  }

  /**
   * Facilitates the removal of a selected portfolio.
   *
   * @return a string indicating the outcome of the removal operation
   * @throws IOException if an I/O error occurs.
   */
  private String removePortfolio() throws IOException {
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
  private String updatePortfolio() throws IOException {
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
