package controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.StockInterface;
import view.FlexibleViewInterface;

/**
 * Controls the interaction between the user and the stock model, managing actions such as
 * displaying stock menu options, calculating gains or losses, and moving averages. It extends
 * the functionality of an abstract controller to apply specific operations on stocks.
 */
public class StockController extends AbstractController {

  private final String name;
  private final StockInterface stock;

  private final FlexibleViewInterface view;

  private final Scanner input;

  /**
   * Constructs a StockController with the specified stock name, model, view, and input scanner.
   *
   * @param name  The name of the stock.
   * @param stock The stock interface for interacting with the stock model.
   * @param view  The flexible view interface for display and user interaction.
   * @param input The scanner for reading user input.
   */
  protected StockController(String name, StockInterface stock, FlexibleViewInterface view,
                            Scanner input) {
    this.name = name;
    this.stock = stock;
    this.view = view;
    this.input = input;
  }

  /**
   * Displays and manages the stock menu, allowing users to select various options for stock
   * analysis including daily gain/loss, period gain/loss, and moving averages.
   *
   * @throws IOException If an I/O error occurs during the display or user interaction.
   */
  protected void stockMenu() throws IOException {
    while (true) {
      view.displayStockMenuOptions(name);
      switch (input.next()) {
        case "1":
          view.displayMessage(dayGainLoss());
          view.continueMessage();
          input.next();
          break;
        case "2":
          view.displayMessage(periodGainLoss());
          view.continueMessage();
          input.next();
          break;
        case "3":
          view.displayMessage(dayMovingAverage());
          view.continueMessage();
          input.next();
          break;
        case "4":
          view.displayMessage(periodCrossover());
          view.continueMessage();
          input.next();
          break;
        case "5":
          view.displayMessage(periodMovingCrossover());
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

  /**
   * Handles the calculation and display of period moving crossovers between two dates.
   * Users are prompted to enter a start and end date, and the sizes of short and long-term moving
   * averages.
   *
   * @return A message indicating the result of the operation or an error/message to navigate.
   * @throws IOException If an I/O error occurs during user interaction.
   */
  private String periodMovingCrossover() throws IOException {
    view.displayStockHeader("Period Moving Crossover");
    while (true) {
      view.getDateInput();
      view.displayMessage("Enter the Starting Date for the period: ");
      String date1 = input.next();
      if (checkDate(date1)) {
        view.getDateInput();
        view.displayMessage("Enter the Ending Date for the period: ");
        String date2 = input.next();
        if (checkDate(date2)) {
          try {
            view.displayMessage("Enter the Number of days for " +
                    "Short Term Moving Average: ");
            view.displayMessage("--- Enter 0 to go back to stock menu");
            int x = input.nextInt();
            if (x != 0) {
              view.displayMessage("Enter the Number of days for " +
                      "Short Term Moving Average: ");
              view.displayMessage("--- Enter 0 to go back to stock menu");
              int y = input.nextInt();
              if (y != 0 && x < y) {
                return stock.findMovingCrossovers(date1, date2, x, y);
              } else if (x >= y) {
                return "Short Term moving Average cannot be greater than Long Term Moving Average";
              } else {
                return "Going Back to stock menu";
              }
            } else {
              return "Going Back to stock menu";
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
   * Manages the identification and display of crossover events between two dates.
   * User provides a start and end date for the analysis period.
   *
   * @return A string indicating crossover events or a navigation/error message.
   * @throws IOException If an I/O error occurs during user interaction.
   */
  private String periodCrossover() throws IOException {
    view.displayStockHeader("Period Crossover");
    while (true) {
      view.getDateInput();
      view.displayMessage("Enter the Starting Date for the period: ");
      String date1 = input.next();
      if (checkDate(date1)) {
        view.getDateInput();
        view.displayMessage("Enter the Ending Date for the period: ");
        String date2 = input.next();
        if (checkDate(date2)) {
          try {
            return stock.findCrossovers(date1, date2);
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
   * Facilitates the calculation and presentation of a moving average on a specified date.
   * User inputs the number of days for the moving average and the specific date of interest.
   *
   * @return A string detailing the moving average or a navigation/error message.
   * @throws IOException If an I/O error occurs during user interaction.
   */
  private String dayMovingAverage() throws IOException {
    view.displayStockHeader("Period Gain Loss");
    try {
      view.displayMessage("Enter the Number of days for which you want to" +
              " calculate moving average: ");
      view.displayMessage("--- Enter 0 to go back to stock menu");
      int number = input.nextInt();
      if (number != 0) {
        while (true) {
          view.getDateInput();
          String date = input.next();
          if (checkDate(date)) {
            try {
              return "The " + number + "Day Moving Average on " + date +
                      " is $" + stock.calculateMovingAverage(date, number);
            } catch (Exception e) {
              return e.getMessage();
            }
          } else if (date.equals("0")) {
            return "Going Back to portfolio menu";
          } else {
            view.displayMessage("You have input an invalid date");
          }
        }
      } else {
        return "Going Back to stock menu";
      }
    } catch (Exception e) {
      return "Invalid Input";
    }
  }

  /**
   * Oversees the calculation and display of gain or loss over a specified period.
   * User enters a start and end date to determine the period's gain or loss.
   *
   * @return A message detailing the gain/loss or a navigation/error message.
   * @throws IOException If an I/O error occurs during user interaction.
   */
  private String periodGainLoss() throws IOException {
    view.displayStockHeader("Period Gain Loss");
    while (true) {

      view.getDateInput();
      view.displayMessage("Enter the Starting Date for the period: ");
      String date1 = input.next();
      if (checkDate(date1)) {
        view.getDateInput();
        view.displayMessage("Enter the Ending Date for the period: ");
        String date2 = input.next();
        if (checkDate(date2)) {
          try {
            float value = stock.calculatePeriodGainOrLoss(date1, date2);
            if (value >= 0) {
              return "The stock gained $" + value + " between " + date1 + " and " + date2;
            } else {
              return "The stock lost $" + Math.abs(value) + " between " + date1 + " and " + date2;
            }
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
   * Manages the calculation and presentation of daily gain or loss for a given date.
   * User provides a specific date to analyze the stock's performance.
   *
   * @return A string indicating the daily gain or loss or a navigation/error message.
   * @throws IOException If an I/O error occurs during user interaction.
   */
  private String dayGainLoss() throws IOException {
    view.displayStockHeader("Daily Gain Loss");
    while (true) {
      view.getDateInput();
      String date = input.next();
      if (checkDate(date)) {
        try {
          float value = stock.calculateDailyGainOrLoss(date);
          if (value >= 0) {
            return "The stock gained $" + value + " on date " + date;
          } else {
            return "The stock lost $" + Math.abs(value) + " on date " + date;
          }
        } catch (Exception e) {
          return e.getMessage();
        }
      } else if (date.equals("0")) {
        return "Going Back to portfolio menu";
      } else {
        view.displayMessage("You have input an invalid date");
      }
    }
  }
}