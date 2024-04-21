package view;

import java.io.IOException;

/**
 The View class implements ViewInterface, facilitating user interaction and data presentation in 
 the MVC architecture. It takes in variable out which tells it how to output data.
 */
public class View implements ViewInterface {

  Appendable out;

  /**
   * Constructs a View object that outputs to the specified Appendable object.
   *
   * @param out the Appendable object to which output will be appended.
   */
  public View(Appendable out) {
    this.out = out;
  }


  @Override
  public void displayMenuOptions() throws IOException {
    this.out.append("-------------------------------------------------------\n");
    this.out.append("---------- WELCOME TO YOUR PORTFOLIO MANAGER ----------\n");
    this.out.append("-------------------------------------------------------\n");
    this.out.append("You can perform the following actions: \n");
    this.out.append("---- [1] To add a portfolio\n");
    this.out.append("---- [2] To view the composition of a portfolio\n");
    this.out.append("---- [3] To view the value of a portfolio\n");
    this.out.append("---- [4] To remove a portfolio\n");
    this.out.append("---- [5] To update a portfolio\n");
    this.out.append("---- [0] To exit application\n");
    this.out.append("Enter option number to select option\n");
  }


  @Override
  public void displayOptionHeader(String option) throws IOException {
    this.out.append("-------------------------------------------------------\n");
    this.out.append("----" + "You have chosen to ").append(option).append(" a portfolio. ----\n");
    this.out.append("---- To go back to main menu enter 0 ----\n");
    this.out.append("-------------------------------------------------------\n");
  }

  @Override
  public void getPortfolioInput(String[] fileList, String option) throws IOException {
    this.out.append("To ").append(option).append(" a portfolio, enter portfolio number\n");
    this.out.append("You have the following portfolios\n");
    listPortfolios(fileList);
    this.out.append("Enter input: \n");
  }


  @Override
  public void getDateInput() throws IOException {
    this.out.append("Enter the date for which you want to check value of portfolio\n");
    this.out.append("---- The date should be in YYYY-MM-DD format\n");
    this.out.append("---- To go back to main menu enter 0\n");
    this.out.append("Enter date:\n");
  }


  @Override
  public void continueMessage() throws IOException {
    this.out.append("Enter any input to continue\n");
  }


  @Override
  public void displayMessage(String message) throws IOException {
    this.out.append(message).append("\n");
  }

  /**
   * Lists all portfolios available to the user.
   *
   * @param fileList an array of portfolio names to be listed.
   * @throws IOException if an I/O error occurs.
   */
  private void listPortfolios(String[] fileList) throws IOException {
    if (fileList != null) {
      this.out.append("---- Following is your list of portfolios: ----\n");
      int count = 1;
      for (String name : fileList) {
        String list = String.format("[%d] %s\n",
                count++,
                name.replace(name.substring(name.length() - 4), ""));
        this.out.append(list);
      }
    }
  }
}
