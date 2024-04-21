package view;

import java.io.IOException;

/**
 * This interface defines all the public methods to output data for the stock portfolio manager.
 */
public interface ViewInterface {

  /**
   * Displays the main menu options to the user.
   * Lists all available actions the user can perform in the portfolio manager application.
   *
   * @throws IOException if an I/O error occurs.
   */
  void displayMenuOptions() throws IOException;

  /**
   * Displays a specific message to the user.
   *
   * @param message the message to be displayed.
   * @throws IOException if an I/O error occurs.
   */
  void displayMessage(String message) throws IOException;

  /**
   * Displays a header for the selected option, indicating the action chosen by the user.
   *
   * @param option the action the user has chosen to perform.
   * @throws IOException if an I/O error occurs.
   */
  void displayOptionHeader(String option) throws IOException;

  /**
   * Prompts the user to select a portfolio from a list for a specified action.
   *
   * @param fileList an array of portfolio names to be displayed.
   * @param option the action to be performed on the selected portfolio.
   * @throws IOException if an I/O error occurs.
   */
  void getPortfolioInput(String[] fileList, String option) throws IOException;

  /**
   * Prompts the user to enter a date for which they want to check the value of a portfolio.
   *
   * @throws IOException if an I/O error occurs.
   */
  void getDateInput() throws IOException;

  /**
   * Prompts the user to enter any input to continue. This is typically used after
   * displaying a message to ensure the user has time to read it.
   *
   * @throws IOException if an I/O error occurs.
   */
  void continueMessage() throws IOException;
}
