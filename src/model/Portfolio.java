package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the {@link PortfolioInterface} and represents a portfolio of stocks,
 * allowing for adding, updating, and retrieving stock information.
 */
public class Portfolio implements PortfolioInterface {
  protected final String name;
  protected List<Stock> stocksList;

  /**
   * Constructs a new Portfolio object with the given name.
   *
   * @param name the name of the portfolio
   */
  public Portfolio(String name) {
    this.name = name;
    this.stocksList = new ArrayList<Stock>();
  }

  /**
   * Adds a stock to the portfolio.
   *
   * @param s        the stock symbol
   * @param quantity the quantity of the stock to add
   * @return a message indicating the stock was successfully added
   */
  protected String addStock(String s, int quantity) {
    Stock stock = new Stock(s, quantity);
    stocksList.add(stock);
    return "Successfully added stock";
  }

  /**
   * Checks if the portfolio contains any stocks.
   *
   * @return true if the portfolio has one or more stocks, false otherwise
   */
  protected boolean stockLength() {
    return !stocksList.isEmpty();
  }

  /**
   * Saves data for specified stock symbol.
   *
   * @return a string detailing the result of the update operation for each stock
   */
  protected String saveData(String symbol) {
    try {
      URLInterface url = new AlphaVantage("EEEYJNAZOVJWJ5PQ");
      StringBuilder sb = url.urlCall(symbol);
      if (sb.toString().contains("Error")) {
        return "Update Unsuccessful for stock: " + symbol
                + " - Incorrect Stock Symbol\r\n";
      } else if (sb.toString().contains("Information")) {
        return "Update Unsuccessful for stock: " + symbol
                + " - Exceeded number of API Calls, try again tomorrow\r\n";
      } else {
        String path = System.getProperty("user.dir") + "/data/" + symbol + ".csv";
        File targetFile = new File(path);
        try (PrintWriter writer = new PrintWriter(targetFile)) {
          writer.write(sb.toString());
          return "Update Successful for stock: " + symbol + "\r\n";
        }
      }
    } catch (Exception e) {
      return "Update Unsuccessful for stock: " + symbol + " - Error: "
              + e.getMessage() + "\r\n";
    }
  }

  /**
   * Saves the portfolio to an XML file at the specified path.
   */
  protected String save() {
    String path = System.getProperty("user.dir") + "/portfolios/";
    String fileName = name + ".xml";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + fileName))) {
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      writer.write("<portfolio>\n");
      for (Stock stock : stocksList) {
        String stockId = stock.getSymbol();
        writer.write("\t<stock id=\"" + stockId + "\">\n");
        writer.write("\t\t<symbol>" + stock.getSymbol() + "</symbol>\n");
        writer.write("\t\t<quantity>" + stock.getQuantity() + "</quantity>\n");
        writer.write("\t</stock>\n");
      }
      writer.write("</portfolio>");
      return "Portfolio saved to " + fileName;
    } catch (IOException e) {
      return "Error saving portfolio: " + e.getMessage();
    }
  }

  /**
   * Generates a summary of the portfolio, including the name and details of each stock.
   *
   * @return a string representation of the portfolio's composition
   */
  @Override
  public String composition() {
    String portfolioName = name.replace(name.substring(name.length() - 4), "");
    StringBuilder output = new StringBuilder("Portfolio Name: " + portfolioName + "\r\n");
    for (Stock stock : stocksList) {
      output.append("--------------------\r\n");
      output.append("Symbol: ").append(stock.getSymbol()).append("\r\n");
      output.append("Quantity: ").append(stock.getQuantity()).append("\r\n");
    }

    return String.valueOf(output);
  }

  /**
   * Calculates the total value of the portfolio on a given date.
   *
   * @param date the date for which to calculate the portfolio's value
   * @return the value of the portfolio on the specified date
   */
  @Override
  public String value(String date) {
    String portfolioName = name.replace(name.substring(name.length() - 4), "");
    StringBuilder output = new StringBuilder("Portfolio Name: " + portfolioName + "\r\n");
    output.append("-- Value Displayed for closest available date in data\r\n");
    output.append("-- Value will be displayed for last closing price if market close on date\r\n");
    output.append("-- Update portfolio from main menu if date not current\r\n");
    float value = 0.00f;
    for (Stock stock : stocksList) {
      output.append("--------------------\r\n");
      output.append("Symbol: ").append(stock.getSymbol()).append("\r\n");
      float quantity = stock.getQuantity();
      output.append("Quantity: ").append(quantity).append("\r\n");
      try {
        String stockDate = stock.getDate(date);
        output.append("Date: ").append(stockDate).append("\r\n");
        float price = stock.getPriceOnDate(date);
        output.append("Price: $").append(price).append("\r\n");
        output.append("Value: $").append(quantity * price).append("\r\n");
        value += quantity * price;
      } catch (Exception e) {
        output.append(e.getMessage()).append("\r\n");
      }
    }
    output.append("Total value of portfolio is $").append(value);
    return String.valueOf(output);
  }

  /**
   * Updates stock information for all stocks in the portfolio by downloading the
   * latest data from a financial data API.
   *
   * @return a string detailing the result of the update operation for each stock
   */
  @Override
  public String update() {
    StringBuilder output = new StringBuilder();
    for (Stock stock : stocksList) {
      output.append(saveData(stock.getSymbol()));
    }
    return String.valueOf(output);
  }
}

