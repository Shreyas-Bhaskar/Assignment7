package controller;

import java.io.IOException;

/**
 * This interface represents the controller of the stock management portal.
 */
public interface ControllerInterface {

  /**
   * This function runs the text gui interface to take input from the user and
   * call the appropriate add view or remove function based on user input.
   */
  void mainMenu() throws IOException;
}
