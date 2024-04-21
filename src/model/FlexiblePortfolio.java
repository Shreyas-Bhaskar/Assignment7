package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static model.Manager.readXMLDocumentFromFile;

/**
 * Represents a flexible portfolio that extends the basic portfolio model.
 * This class allows for additional functionalities such as calculating portfolio value over a
 * period, generating graphs based on the portfolio's performance, and more.
 */
public class FlexiblePortfolio extends Portfolio implements FlexiblePortfolioInterface {

  /**
   * Constructs a new FlexiblePortfolio object with the specified name.
   *
   * @param name the name of the portfolio
   */
  public FlexiblePortfolio(String name) {
    super(name);
  }

  private float portfolioValue(String date) {
    float value = 0;
    for (Stock stock : stocksList) {
      try {
        value += (stock.getQuantityOnDate(date) * stock.getQuantityOnDate(date));
      } catch (Exception ignore) {
        value += 0;
      }

    }
    return value;
  }

  /**
   * Calculates the average value of the portfolio between two dates.
   * This method iterates over each day in the specified range, summing the portfolio's value on
   * each date,and then divides by the total number of days to find the average value.
   *
   * @param startDate The start date of the period for which the average is calculated, inclusive.
   * @param endDate   The end date of the period for which the average is calculated, inclusive.
   * @return The average value of the portfolio over the specified period.
   */
  private float portfolioAverage(String startDate, String endDate) {
    float average = 0;
    int count = 0;
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);
    List<LocalDate> totalDates = new ArrayList<>();
    while (!start.isAfter(end)) {
      totalDates.add(start);
      start = start.plusDays(1);
    }
    for (LocalDate date : totalDates) {
      average += portfolioValue(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      count += 1;
    }
    return average / count;
  }

  /**
   * Generates a graph representation of the portfolio's performance over a specified period.
   * The graph can be generated as daily, weekly, or monthly summaries.
   *
   * @param startDate the start date of the period for the graph
   * @param endDate   the end date of the period for the graph
   * @param type      the type of graph to generate (daily, weekly, monthly)
   * @return a string representation of the graph
   */
  @Override
  public String graph(String startDate, String endDate, String type) throws Exception {
    StringBuilder graph = new StringBuilder();
    graph.append("Performance of portfolio ").append(name).append(" from ");
    graph.append(startDate).append(" to ").append(endDate).append("\n\n");

    //Parsing the date
    LocalDate dateBefore = LocalDate.parse(startDate);
    LocalDate dateAfter = LocalDate.parse(endDate);

    if (dateAfter.isBefore(dateBefore)) {
      throw new Exception("Start date cannot be after end date");
    }

    switch (type) {
      case "daily":
        long days = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        if (days < 30 && days > 5) {
          graph.append(buildDailyGraph(dateBefore, dateAfter));
          break;
        } else if (days > 30) {
          throw new Exception("Cannot build graph for more than 30 days");
        } else if (days < 5) {
          throw new Exception("Cannot build graph for less than 5 days");
        }
        break;
      case "weekly":
        long weeks = ChronoUnit.WEEKS.between(dateBefore, dateAfter);
        if (weeks < 210 && weeks > 35) {
          graph.append(buildWeeklyGraph(dateBefore, dateAfter));
          break;
        } else if (weeks > 210) {
          throw new Exception("Cannot build graph for more than 30 weeks");
        } else if (weeks < 35) {
          throw new Exception("Cannot build graph for less than 5 weeks");
        }
        graph.append(buildWeeklyGraph(dateBefore, dateAfter));
        break;
      case "monthly":
        long months = ChronoUnit.MONTHS.between(
                YearMonth.from(dateBefore),
                YearMonth.from(dateAfter)
        );
        if (months < 30 && months > 5) {
          graph.append(buildMonthlyGraph(dateBefore, dateAfter));
          break;
        } else if (months > 30) {
          throw new Exception("Cannot build graph for more than 30 months");
        } else if (months < 5) {
          throw new Exception("Cannot build graph for less than 5 months");
        }
        break;
      default:
        break;
    }
    graph.append("\nScale: * = \n");
    return String.valueOf(graph);
  }

  private String buildDailyGraph(LocalDate start, LocalDate end) {
    StringBuilder graph = new StringBuilder();
    while (!start.isAfter(end)) {
      int value = (int) portfolioValue(start.toString()) / 1000;
      graph.append(start).append(": ").append("*".repeat(value)).append("\n");
      start = start.plusDays(1);
    }
    return String.valueOf(graph);
  }

  private String buildWeeklyGraph(LocalDate start, LocalDate end) {
    StringBuilder graph = new StringBuilder();
    while (!start.isAfter(end)) {
      LocalDate last = start.plusDays(7);
      int value = (int) portfolioAverage(start.toString(), last.toString()) / 1000;
      graph.append(start).append("-").append(last).append(": ").append("*".repeat(value))
              .append("\n");
      start = last.plusDays(1);
    }
    return String.valueOf(graph);
  }

  private String buildMonthlyGraph(LocalDate start, LocalDate end) {
    StringBuilder graph = new StringBuilder();
    while (!start.isAfter(end)) {
      LocalDate first = LocalDate.of(start.getYear(), start.getMonth(), 1);
      LocalDate last = LocalDate.of(start.getYear(), start.getMonth(), start.lengthOfMonth());
      int value = (int) portfolioAverage(first.toString(), last.toString()) / 1000;
      graph.append(first.getMonth().toString(), 0, 3).append(first.getYear()).
              append(": ").append("*".repeat(value)).append("\n");
      start = start.plusMonths(1);
    }
    return String.valueOf(graph);
  }

  /**
   * Calculates the total cost basis of the portfolio as of a specific date.
   *
   * @param date the date up to which the cost basis is calculated
   * @return a string indicating the total cost basis by the specified date
   */
  @Override
  public String costBasis(String date) {
    float totalCostBasis = 0;
    for (Stock stock : stocksList) {
      try {
        totalCostBasis = stock.getCostBasis(date);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return String.format("Total cost basis by %s: $%.2f", date, totalCostBasis);
  }

  /**
   * Lists all the stocks currently held in the portfolio.
   *
   * @return a list of strings, where each string represents a stock symbol in the portfolio
   */
  @Override
  public List<String> listStocks() {
    List<String> list = new ArrayList<String>();
    for (Stock stock : stocksList) {
      list.add(stock.getSymbol());
    }
    return list;
  }

  /**
   * Buys an existing stock in the portfolio, adding the specified quantity on the given date.
   *
   * @param number   the index of the stock in the portfolio's list
   * @param date     the date of the purchase
   * @param quantity the quantity of the stock to buy
   * @return a string message indicating the result of the operation
   */
  @Override
  public String buyExisting(int number, String date, int quantity) {

    String dates = stocksList.get(number - 1).getDates();

    dates = dates.substring(dates.length() - 10);

    if (!LocalDate.parse(dates).isAfter(LocalDate.parse(date))) {
      if (quantity > 0) {
        stocksList.get(number - 1).transact(date, quantity);
        stocksList.get(number - 1).setQuantity(quantity);
        return save();
      } else {
        return "Cannot buy negative stocks";
      }
    } else {
      return "Can only Buy more stock after the date of previous transaction";
    }
  }

  /**
   * Sells an existing stock from the portfolio,
   * subtracting the specified quantity on the given date.
   *
   * @param number   the index of the stock in the portfolio's list
   * @param date     the date of the sale
   * @param quantity the quantity of the stock to sell
   * @return a string message indicating the result of the operation
   */
  @Override
  public String sellExisting(int number, String date, int quantity) {
    if (stocksList.get(number - 1).getQuantity() >= quantity) {
      String dates = stocksList.get(number - 1).getDates();
      dates = dates.substring(dates.length() - 10);

      if (!LocalDate.parse(dates).isAfter(LocalDate.parse(date))) {
        if (quantity > 0) {
          stocksList.get(number - 1).transact(date, (-1 * quantity));
          stocksList.get(number - 1).setQuantity(-1 * quantity);
          return save();
        } else {
          return "Cannot sell negative stocks";
        }
      } else {
        return "Can only Sell more stock after the date of previous transaction";
      }
    } else {
      return "Cannot sell more stocks than you have";
    }
  }

  /**
   * Inspects a specific stock in the portfolio, identified by its index in the portfolio's list.
   *
   * @param number the index of the stock to inspect
   * @return the StockInterface of the inspected stock
   */
  @Override
  public StockInterface inspectStock(int number) {
    return stocksList.get(number - 1);
  }

  /**
   * Deletes the strategy file located in the strategies directory
   *
   * @return A String message indicating whether the deletion was successful or failed.
   */
  private String deleteStrategy() {
    String path = System.getProperty("user.dir") + "/strategies/";
    File strategy = new File(path);
    return strategy.delete() ? "Delete Successful" : "Delete Failed";
  }

  /**
   * Fetches the strategy data from an XML file located in the strategies' directory .
   * Parses the strategy information, including start date, end date, stocks, prices, and
   * period from the XML file.
   * Converts the list of price strings into a list of Floats, handling any number format
   * exceptions by logging an error.
   *
   * @return An instance of Strategy populated with the fetched data.
   * @throws Exception If there is an issue reading the XML file or parsing the strategy data.
   */
  protected Strategy fetchStrategy() throws Exception {

    String path = System.getProperty("user.dir") + "/strategies/";
    Document document = readXMLDocumentFromFile(path + name + ".xml");

    //Get all strategies
    NodeList nList = document.getElementsByTagName("strategy");

    Node node = nList.item(0);
    Element eElement = (Element) node;
    String startDate = eElement.getElementsByTagName("startDate").item(0).getTextContent();
    String endDate = eElement.getElementsByTagName("endDate").item(0).getTextContent();

    String[] getStocks = eElement.getElementsByTagName("stocks").item(0).getTextContent().split(",");

    List<String> stocks = new ArrayList<String>(Arrays.asList(getStocks));

    List<String> pricesString = Arrays.stream(eElement.getElementsByTagName("prices").item(0)
                    .getTextContent().split(","))
            .collect(Collectors.toList());
    List<Float> prices = new ArrayList<>();
    for (String priceString : pricesString) {
      try {
        float price = Float.parseFloat(priceString.trim());
        prices.add(price);
      } catch (NumberFormatException e) {
        System.err.println("Invalid price format: " + priceString);
      }
    }

    int period = Integer.parseInt(eElement.getElementsByTagName(
            "period").item(0).getTextContent());

    Strategy strategy = new Strategy(startDate, endDate, period, stocks, prices);

    return strategy;

  }

  /**
   * Executes an investment strategy over a defined period, purchasing stocks based on
   * predefined criteria.
   * Transactions occur from the strategy's start date to its end date
   * (or the current date if undefined),
   * with investments distributed according to the strategy's specified stock weights and periods.
   */
  @Override
  public void useStrategy() {
    try {
      Strategy strategy = fetchStrategy();
      LocalDate start = LocalDate.parse(strategy.getStartDate());
      LocalDate end;
      if (!strategy.getEndDate().equals("1")) {
        end = LocalDate.parse(strategy.getEndDate());
      } else {
        end = LocalDate.now();
      }
      while (!start.isAfter(end)) {
        for (int i = 0; i < strategy.getStocks().size(); i++) {
          String dates = stocksList.get(i).getDates();
          dates = dates.substring(dates.length() - 10);
          try {
            if (LocalDate.parse(dates).isBefore(start)) {
              float quantity = (strategy.getPrices().get(i)
                      / stocksList.get(i).getBuyPriceOnDate(start.toString()));
              stocksList.get(i).transact(start.toString(), quantity);
              stocksList.get(i).setQuantity(quantity);
              save();
            }
          } catch (Exception e) {
            System.out.println("");
          }
        }
        start = start.plusDays(strategy.getPeriod());
      }
    } catch (Exception e) {
      System.out.println("");
    }
  }

  /**
   * Checks if the strategy XML file exists in the strategies' directory.
   * This method is useful for verifying the existence of the strategy file before attempting
   * operations such as reading or deletion.
   *
   * @return true if the file exists, false otherwise.
   */
  private boolean checkStrategyExists() {
    String path = System.getProperty("user.dir") + "/strategies/";
    String filePath = path + name + ".xml";
    File file = new File(filePath);

    try {
      if (file.exists()) {
        return true;
      }
    } catch (Exception e) {
      System.err.println("An error occurred while checking the strategy : " + e.getMessage());
    }
    return false;
  }

  @Override
  public boolean checkStartDate(String date) {
    boolean value = true;
    for (Stock stock : stocksList) {
      String dates = stock.getDates();
      dates = dates.substring(dates.length() - 10);
      value = value && !LocalDate.parse(dates).isAfter(LocalDate.parse(date));
    }
    return value;
  }

  /**
   * Provides a detailed composition of the portfolio, including stock symbols, transaction dates,
   * and quantities. Overrides method from PortfolioInterface to work with FlexiblePortfolio.
   *
   * @return a string representation detailing the composition of the flexible portfolio
   */
  @Override
  public String composition() {
    StringBuilder output = new StringBuilder("Portfolio Name: " + name + "\r\n");
    for (Stock stock : stocksList) {
      output.append("--------------------\r\n");
      output.append("Symbol: ").append(stock.getSymbol()).append("\r\n");
      output.append("Transaction Dates: ").append(stock.getDates()).append("\r\n");
      output.append("Transaction Quantities: ").append(stock.getQuantities()).append("\r\n");
      output.append("Quantity: ").append(stock.getQuantity()).append("\r\n");
    }

    return String.valueOf(output);
  }

  /**
   * Calculates the value of the portfolio on a specific date, including details for each stock.
   * Overrides method from PortfolioInterface to work with FlexiblePortfolio.
   *
   * @param date the date for which the portfolio value is calculated
   * @return a string representation of the flexible portfolio's value on the specified date
   */
  @Override
  public String value(String date) {
    StringBuilder output = new StringBuilder("Portfolio Name: " + name + "\r\n");
    output.append("-- Value Displayed for closest available date in data\r\n");
    output.append("-- Value will be displayed for last closing price if market close on date\r\n");
    output.append("-- Update portfolio from main menu if date not current\r\n");
    float value = 0.00f;
    for (Stock stock : stocksList) {
      output.append("--------------------\r\n");
      output.append("Symbol: ").append(stock.getSymbol()).append("\r\n");
      try {
        int quantity = stock.getQuantityOnDate(date);
        output.append("Quantity: ").append(quantity).append("\r\n");
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
   * Saves the current state of the portfolio to an XML file within the user's directory.
   * Overrides method from PortfolioInterface to work with FlexiblePortfolio.
   *
   * @return a string message indicating the result of the save operation
   */
  @Override
  protected String save() {
    String path = System.getProperty("user.dir") + "/portfolios/";
    String fileName = name + ".xml";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + fileName))) {
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      writer.write("<portfolio>\n");
      for (Stock stock : stocksList) {
        writer.write("\t<stock id=\"" + stock.getSymbol() + "\">\n");
        writer.write("\t\t<symbol>" + stock.getSymbol() + "</symbol>\n");

        writer.write("\t\t<transactDate>" + stock.getDates() + "</transactDate>\n");
        writer.write("\t\t<transactQuantity>" + stock.getQuantities() + "</transactQuantity>\n");

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
   * Buys an existing stock from the portfolio,
   * Adding the specified quantity on the given date.
   *
   * @param number the index of the stock in the portfolio's list
   * @param date   the date of the sale
   * @return a string message indicating the result of the operation
   */
  @Override
  public String buyExistingPrice(int number, String date, float price) {

    String dates = stocksList.get(number).getDates();

    dates = dates.substring(dates.length() - 10);
    try {
      if (!LocalDate.parse(dates).isAfter(LocalDate.parse(date))) {
        float quantity = price / stocksList.get(number).getBuyPriceOnDate(date);
        stocksList.get(number).transact(date, quantity);
        stocksList.get(number).setQuantity(quantity);
        return save();
      } else {
        return "Can only Buy more stock after the date of previous transaction";
      }
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
