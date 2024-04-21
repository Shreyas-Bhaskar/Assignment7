package view;

import java.awt.event.ActionListener;


/**
 * This interface defines the essential functionality that the view must implement
 * in the context of this application. It specifies methods for displaying the view, setting action
 * listeners, displaying messages, and getting user input for portfolio selection.
 */
public interface GUIViewInterface {

  /**
   * Makes the GUI view visible to the user. This method is used to show the main application window
   * or dialog when the application is started or when it needs to be brought into focus.
   */
  void makeVisible();

  /**
   * Sets an action listener for the GUI view. This allows for the handling of user actions such as
   * button clicks and menu selections.
   *
   * @param actionEvent The action listener to be associated with user actions in the GUI.
   */
  void setButtonListener(ActionListener actionEvent);

  void displayMessage(String message);

  int getPortfolioOption(String[] options);

  int getCount(String type);
}
