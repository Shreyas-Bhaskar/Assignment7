package view;

import java.io.IOException;
import java.util.List;

/**
 * The FlexibleView class implements FlexibleViewInterface, facilitating user interaction and
 * data presentation in the MVC architecture. It extends the {@link View} class and has functions
 * for {@link controller.FlexibleController} class.
 */
public class FlexibleView extends View implements FlexibleViewInterface {

  /**
   * Constructs a FlexibleView object that outputs to the specified Appendable object.
   *
   * @param out the Appendable object to which output will be appended.
   */
  public FlexibleView(Appendable out) {
    super(out);
  }

  @Override
  public void displayMenuOptions() throws IOException {
    this.out.append("-------------------------------------------------------\n");
    this.out.append("---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n");
    this.out.append("-------------------------------------------------------\n");
    this.out.append("Enter corresponding number perform the following actions: \n");
    this.out.append("---- [1] To add a portfolio\n");
    this.out.append("---- [2] To manage a portfolio\n");
    this.out.append("---- [3] To remove a portfolio\n");
    this.out.append("---- [4] To update a portfolio\n");
    this.out.append("---- [5] To add a portfolio with dollar cost averaging\n");
    this.out.append("---- [6] To add a portfolio with startegy\n");
    this.out.append("---- [0] To exit application\n");
    this.out.append("Enter option number to select option:\n");
  }

  @Override
  public void getDateInput() throws IOException {
    this.out.append("---- Enter the date for which you want to perform action: ----\n");
    this.out.append("---- The date should be in YYYY-MM-DD format\n");
    this.out.append("---- The date should be either current date or before current date\n");
    this.out.append("---- To go back to main menu enter 0\n");
    this.out.append("Enter date:\n");
  }

  @Override
  public void displayPortfolioMenuOptions(String name) throws IOException {
    this.out.append("-------------------------------------------------------\n");
    this.out.append("---------- You have chosen portfolio ").append(name).append(" ----------\n");
    this.out.append("-------------------------------------------------------\n");
    this.out.append("Enter corresponding number perform the following actions: \n");
    this.out.append("---- [1] To buy more stock\n");
    this.out.append("---- [2] To sell a stock\n");
    this.out.append("---- [3] To view portfolio composition\n");
    this.out.append("---- [4] To view portfolio value\n");
    this.out.append("---- [5] To view portfolio graph\n");
    this.out.append("---- [6] To view portfolio cost basis\n");
    this.out.append("---- [7] To inspect a stock in portfolio\n");
    this.out.append("---- [8] To buy stocks with dollar cost averaging\n");
    this.out.append("---- [9] To buy stocks with strategy\n");
    this.out.append("---- [0] To go back to main menu\n");
    this.out.append("Enter option number to select option:\n");
  }

  @Override
  public void getStockInput(List<String> fileList, String option) throws IOException {
    this.out.append("To ").append(option).append(" the portfolio, enter stock number\n");
    listStocks(fileList);
    this.out.append("Enter input: \n");
  }

  @Override
  public void displayStockMenuOptions(String name) throws IOException {
    this.out.append("-------------------------------------------------------\n");
    this.out.append("---------- You have chosen stock ").append(name).append(" ----------\n");
    this.out.append("-------------------------------------------------------\n");
    this.out.append("Enter corresponding number perform the following actions: \n");
    this.out.append("---- [1] To display Gain Loss for specified date\n");
    this.out.append("---- [2] To display Gain Loss for specified period\n");
    this.out.append("---- [3] To display Moving Average for specified date\n");
    this.out.append("---- [4] To display Crossover for specified period\n");
    this.out.append("---- [5] To display Moving Crossover for specified period\n");
    this.out.append("---- [0] To go back to main menu\n");
    this.out.append("Enter option number to select option:\n");
  }

  @Override
  public void displayStockHeader(String option) throws IOException {
    this.out.append("-------------------------------------------------------\n");
    this.out.append("---------- You have chosen th option ").append(option).append(" ----------\n");
    this.out.append("-------------------------------------------------------\n");
  }


  @Override
  public void getGraphInput() throws IOException {
    this.out.append("Enter choice for type of graph:\n");
    this.out.append("---- [1] To build daily graph\n");
    this.out.append("---- [2] To build weekly graph\n");
    this.out.append("---- [3] To build monthly graph\n");
    this.out.append("---- [0] To go back to portfolio menu\n");
  }

  /**
   * Outputs a formatted list of stock names from the provided list to the designated output stream.
   * Each stock name is enumerated and modified to exclude its file extension before being listed.
   *
   * @param fileList A List of Strings representing the stock names to be listed.
   * @throws IOException If an I/O error occurs during writing to the output stream.
   */
  private void listStocks(List<String> fileList) throws IOException {
    if (fileList != null) {
      this.out.append("---- Following is your list of stocks in the portfolio: ----\n");
      int count = 1;
      for (String name : fileList) {
        String list = String.format("[%d] %s\n",
                count++,
                name);
        this.out.append(list);
      }
    }
  }
}
