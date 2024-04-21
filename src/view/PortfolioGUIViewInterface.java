package view;

import java.awt.event.ActionListener;

/**
 * Defines the GUI view in managing a specific stock portfolio.
 * This interface encapsulates operations such as displaying the view, setting action listeners
 * for various user interactions, and retrieving user input for stock transactions and portfolio
 * management.
 */
public interface PortfolioGUIViewInterface {

  /**
   * Makes the portfolio management GUI visible to the user. This is typically called
   * after the GUI has been initialized and is ready to be shown.
   */
  void makeVisible();

  /**
   * Sets ActionListeners for the GUI's interactive components. This allows the GUI to respond
   * to user actions, such as button clicks for buying or selling stocks, and viewing portfolio
   * details.
   *
   * @param actionEvent The ActionListener to attach to the GUI's buttons.
   */
  void setButtonListener(ActionListener actionEvent);

  /**
   * Retrieves the selected stock symbol index from a JComboBox component, indicating
   * which stock the user intends to buy or sell.
   *
   * @return The index of the selected stock symbol.
   */
  int getStockSymbol();

  /**
   * Retrieves the date entered by the user for performing value and cost basis calculations.
   * The method should clear the input field after retrieving the value.
   *
   * @return The date entered by the user, intended for stock transactions or calculations.
   */
  String getDate();

  /**
   * Retrieves the date specified by the user for buying a stock. The method should clear
   * the input field after retrieving the value.
   *
   * @return The buy date entered by the user.
   */
  String getBuyDate();

  /**
   * Retrieves the quantity of stock to buy or sell as entered by the user. The method should
   * clear the input field after retrieving the value.
   *
   * @return The quantity of stock for the transaction, as entered by the user.
   */
  String getQuantity();

  /**
   * Displays a dialog box for the user to select a stock from the portfolio, typically used
   * when the user wants to inspect details of a specific stock.
   *
   * @return The index of the selected stock option.
   */
  int getStockOption();

  /**
   * Shows a message to the user via a dialog box. This method is useful for displaying
   * information, warnings, and errors.
   *
   * @param message The message to be displayed in the dialog box.
   */
  void displayMessage(String message);
}
