package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import model.StockInterface;
import view.StockGUIView;

/**
 * This controller handles interactions between the stock model and the stock GUI
 * processing user inputs and updating the view based on stock data.
 */
public class StockGUIController extends AbstractController implements ActionListener {
  private final StockInterface stock;
  private final StockGUIView view;

  /**
   * Constructs a StockGUIController with references to both the stock model and the GUI view.
   * This setup allows for communication and interaction between the model and view .
   *
   * @param stock The stock model that contains the data and logic for stock operations.
   * @param view The GUI view that presents information to the user and receives user input.
   */
  public StockGUIController(StockInterface stock,
                            StockGUIView view) {
    this.stock = stock;
    this.view = view;
  }

  /**
   * Prepares the user interface for interaction by setting this controller to listen for
   * action events from the GUI and making the view visible to the user.
   */
  public void mainMenu() {
    this.view.setButtonListener(this);
    this.view.makeVisible();
  }

  /**
   * Responds to action events triggered by the user interface. Depending on the user's
   * selection, it performs various stock-related operations such as calculating daily
   * gain or loss, period gain or loss, moving averages, and finding crossovers.
   *
   * @param e The action event that occurred, triggering this method.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Map<String, String> texts = view.texts();
    String date1 = texts.get("startDateCrossovers");
    String date2 = texts.get("endDateCrossovers");
    switch (e.getActionCommand()) {
      case "Daily Gain Loss":
        if (texts.get("gainLossDate").isEmpty()) {
          view.displayMessage("Enter a Date");
        } else if (checkDate(texts.get("gainLossDate"))) {
          try {
            float value = stock.calculateDailyGainOrLoss(texts.get("gainLossDate"));
            if (value >= 0) {
              view.displayMessage("The stock gained $" + value + " on date " +
                      texts.get("gainLossDate"));
            } else {
              view.displayMessage("The stock lost $" + Math.abs(value) + " on date " +
                      texts.get("gainLossDate"));
            }
          } catch (Exception ex) {
            view.displayMessage(ex.getMessage());
          }
        } else {
          view.displayMessage("Enter Valid Date");
        }
        break;
      case "Period Gain Loss":
        if (date1.isEmpty() || date2.isEmpty()) {
          view.displayMessage("Either Start or End Date is Missing");
        } else if (checkDate(date1) && checkDate(date2)) {
          try {
            float value = stock.calculatePeriodGainOrLoss(date1, date2);
            if (value >= 0) {
              view.displayMessage("The stock gained $" + value +
                      " between " + date1 + " and " + date2);
            } else {
              view.displayMessage("The stock lost $" + Math.abs(value) +
                      " between " + date1 + " and " + date2);
            }
          } catch (Exception ex) {
            view.displayMessage(ex.getMessage());
          }
        } else {
          view.displayMessage("Enter Valid Dates");
        }
        break;
      case "Crossover":
        if (date1.isEmpty() || date2.isEmpty()) {
          view.displayMessage("Either Start or End Date is Missing");
        } else if (checkDate(date1) && checkDate(date2)) {
          try {
            view.displayMessage(stock.findCrossovers(date1, date2));
          } catch (Exception ex) {
            view.displayMessage(ex.getMessage());
          }
        } else {
          view.displayMessage("Enter Valid Dates");
        }
        break;
      case "Moving Average":
        date1 = texts.get("movingAverageDate");
        if (date1.isEmpty()) {
          view.displayMessage("Enter a Date");
        } else if (checkDate(date1)) {
          try {
            int num = Integer.parseInt(texts.get("movingAverageX"));
            float value = stock.calculateMovingAverage(date1, num);
            view.displayMessage("The " + num + "Day Moving Average on " + date1 +
                    " is $" + value);
          } catch (NumberFormatException ex) {
            view.displayMessage("Invalid Number");
          } catch (Exception ex) {
            view.displayMessage(ex.getMessage());
          }
        } else {
          view.displayMessage("Enter Valid Date");
        }
        break;
      case "Moving Crossovers":
        date1 = texts.get("startDateMovingCrossovers");
        date2 = texts.get("endDateMovingCrossovers");
        if (date1.isEmpty() || date2.isEmpty()) {
          view.displayMessage("Either Start or End Date is Missing");
        } else if (checkDate(date1) && checkDate(date2)) {
          try {
            int num1 = Integer.parseInt(texts.get("movingCrossoversX"));
            int num2 = Integer.parseInt(texts.get("movingCrossoversY"));
            String value = stock.findMovingCrossovers(date1, date2, num1, num2);
            view.displayMessage(value);
          } catch (NumberFormatException ex) {
            view.displayMessage("Invalid Number");
          } catch (Exception ex) {
            view.displayMessage(ex.getMessage());
          }
        } else {
          view.displayMessage("Enter Valid Dates");
        }
        break;
      default:
        break;
    }
  }
}
