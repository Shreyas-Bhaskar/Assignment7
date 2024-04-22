package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an investment strategy, encapsulating start and end dates, a period for transactions,
 * and associated stocks and their prices.
 */
public class Strategy {
  private String startDate;

  private String endDate;

  private int period;

  private List<String> stocks;

  private List<Float> prices;

  /**
   * Constructs a Strategy with specified parameters.
   *
   * @param startDate The start date for the strategy.
   * @param endDate   The end date for the strategy.
   * @param period    The period between investment actions.
   * @param stocks    The list of stock symbols.
   * @param prices    The list of prices corresponding to each stock.
   */
  public Strategy(String startDate, String endDate, int period, List<String> stocks, List<Float>
          prices) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.period = period;
    this.stocks = stocks;
    this.prices = prices;
  }

  protected String getStartDate() {
    return startDate;
  }

  protected String getEndDate() {
    return endDate;
  }

  protected int getPeriod() {
    return period;
  }

  protected List<String> getStocks() {
    return stocks;
  }

  protected List<Float> getPrices() {
    return prices;
  }

  /**
   * Saves the strategy to an XML file named after the specified portfolio name.
   *
   * @param portfolioname The name of the portfolio for which the strategy is being saved.
   * @return A success message if saved successfully, otherwise an error message.
   */
  public String saveStrategy(String portfolioname) {
    String path = System.getProperty("user.dir") + "/strategies/";
    String fileName = portfolioname + ".xml";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + fileName))) {
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      writer.write("<strategy>\n");
      writer.write("\t<startDate>" + this.startDate + "</startDate>\n");
      writer.write("\t<endDate>" + this.endDate + "</endDate>\n");
      writer.write("\t<period>" + this.period + "</period>\n");

      writer.write("\t<stocks>");
      writer.write(stocks.stream()
              .map(String::valueOf)
              .collect(Collectors.joining(",", "", "")));
      writer.write("</stocks>\n");

      writer.write("\t<prices>");
      writer.write(prices.stream()
              .map(String::valueOf)
              .collect(Collectors.joining(",", "", "")));
      writer.write("</prices>\n");

      writer.write("</strategy>");
      return "Strategy saved to " + fileName;
    } catch (IOException e) {
      return "Error saving strategy: " + e.getMessage();
    }
  }
}
