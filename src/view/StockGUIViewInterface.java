package view;

import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Interface for the GUI view responsible for managing individual stocks within a portfolio.
 * It defines methods for displaying the GUI, setting action listeners, collecting user inputs,
 * and displaying messages to the user.
 */
public interface StockGUIViewInterface {

  /**
   * Makes the stock management GUI visible to the user. This should be called
   * after the GUI has been initialized and is ready to be displayed.
   */
  void makeVisible();

  /**
   * Sets ActionListeners for the GUI's buttons. This enables the GUI to respond to user
   * actions, facilitating interactions such as viewing daily gain/loss, computing moving
   * averages, and analyzing crossovers.
   *
   * @param actionEvent The ActionListener to attach to the GUI's interactive components.
   */
  void setButtonListener(ActionListener actionEvent);

  /**
   * Gathers and returns the text inputs from all JTextField components within the GUI.
   * This method aids in retrieving user input necessary for stock management operations.
   * Text fields are cleared after the data is collected.
   *
   * @return A map containing the keys representing the input field identifiers and their
   *         corresponding values as entered by the user.
   */
  Map<String, String> texts();

  /**
   * Displays a message to the user via a dialog box. Useful for conveying information,
   * warnings, and error messages to the user.
   *
   * @param message The message to be displayed in the dialog box.
   */
  void displayMessage(String message);
}
