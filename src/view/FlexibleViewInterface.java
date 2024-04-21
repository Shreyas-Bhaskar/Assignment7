package view;

import java.io.IOException;
import java.util.List;

/**
 * Extends the ViewInterface to provide additional display functionalities specific to flexible
 * portfolios.
 * This interface includes methods for displaying portfolio menu options, stock inputs, and stock
 * menu options.
 */
public interface FlexibleViewInterface extends ViewInterface {

  /**
   * Displays the menu options for a specific portfolio.
   *
   * @param name The name of the portfolio for which the menu options are displayed.
   * @throws IOException If an I/O error occurs during the display.
   */
  void displayPortfolioMenuOptions(String name) throws IOException;

  /**
   * Prompts the user to select a stock from a list to inspect its values.
   *
   * @param fileList The name of the stock for which the menu options are displayed.
   * @param option   A description of the stock management option being displayed.
   * @throws IOException If an I/O error occurs during the display.
   */
  void getStockInput(List<String> fileList, String option) throws IOException;

  /**
   * Displays the menu options available for individual stock management within a portfolio.
   *
   * @param name The name of the stock for which the menu options are displayed.
   * @throws IOException If an I/O error occurs during the display.
   */
  void displayStockMenuOptions(String name) throws IOException;

  /**
   * Displays a header or introduction for a specific stock management option (e.g., checking
   * gain loss, moving average, crossovers, etc.).
   *
   * @param option A description of the stock management option being displayed.
   * @throws IOException If an I/O error occurs during the display.
   */
  void displayStockHeader(String option) throws IOException;

  /**
   * Prompts the user for input to select the granularity or type of graph they wish to view.
   * This method is intended to facilitate the selection process for generating visual
   * representations of portfolio or stock performance, offering choices such as daily, weekly,
   * or monthly views.
   *
   * @throws IOException If an I/O error occurs during the input gathering process.
   */
  void getGraphInput() throws IOException;
}
