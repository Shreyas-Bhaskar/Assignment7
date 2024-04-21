package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The FlexibleManager class extends Manager and implements the FlexibleManagerInterface.
 * It is responsible for managing flexible portfolios, including creating portfolios,
 * adding stocks to them, saving portfolios, and fetching portfolios with their details.
 */
public class FlexibleManager extends Manager implements FlexibleManagerInterface {

  private FlexiblePortfolio currentPortfolio;

  // Override create portfolio to create flexible portfolio instead of simple

  /**
   * Creates a new flexible portfolio with the given name. This method overrides the
   * createPortfolio method in the superclass to specifically create a FlexiblePortfolio.
   * It validates the name provided before creating the portfolio.
   *
   * @param name The name of the portfolio to be created.
   * @return boolean True if the portfolio is successfully created, false otherwise.
   * @throws IllegalArgumentException if the provided name is invalid.
   */
  @Override
  public boolean createPortfolio(String name) throws IllegalArgumentException {
    // validate here
    // if successful, create new portfolio and return success
    // else return validation error
    if (!name.equals("0")) {
      if (validateName(name)) {
        currentPortfolio = new FlexiblePortfolio(name);
        return true;
      } else {
        throw new IllegalArgumentException("Invalid Name Enter Again");
      }
    } else {
      return false;
    }
  }

  /**
   * Adds a stock to the current portfolio. This method attempts to fetch stock data,
   * create a new stock instance, and add it to the portfolio if the provided symbol
   * and quantity are valid.
   *
   * @param symbol        The stock symbol to add.
   * @param stockQuantity The quantity of the stock to add.
   * @param date          The date of the transaction.
   * @return A string message indicating the outcome of the operation.
   */
  @Override
  public String addStock(String symbol, int stockQuantity, String date) {
    try {
      if (saveData(symbol) && stockQuantity > 0) {
        Stock stock = new Stock(symbol.toUpperCase(), stockQuantity);
        stock.transact(date, stockQuantity);
        currentPortfolio.stocksList.add(stock);
        return "Successfully added stock";
      } else if (stockQuantity <= 0) {
        return "Cannot buy negative stock quantity";
      } else {
        return "Failed to fetch stock price for " + symbol;
      }
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * Adds a stock to the current portfolio using the provided symbol, stock price, and date.
   * This method fetches the stock price for the specified date, calculates the quantity of the
   * stock that can be purchased with the provided stock price, and adds the stock to the
   * portfolio. If the stock price cannot be fetched or another error occurs, an appropriate error
   * message is returned.
   *
   * @param symbol     The symbol of the stock to be added.
   * @param stockPrice The total amount to be invested in the stock.
   * @param date       The date of the transaction.
   * @return A string message indicating whether the stock was successfully added or if an error
   * occurred.
   */
  @Override
  public String addStock(String symbol, float stockPrice, String date) {
    try {
      if (saveData(symbol)) {
        Stock temp = new Stock(symbol.toUpperCase(), 10);
        float stockQuantity = stockPrice / temp.getBuyPriceOnDate(date);
        Stock stock = new Stock(symbol.toUpperCase(), stockQuantity);
        stock.transact(date, stockQuantity);
        currentPortfolio.stocksList.add(stock);
        return "Successfully added stock";
      } else {
        return "Failed to fetch stock price for " + symbol;
      }
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  // override save portfolio to use the flexible portfolio instead of the simple portfolio

  /**
   * Saves the current portfolio to a persistent storage. This method overrides the
   * savePortfolio method in the superclass to specifically handle flexible portfolios.
   * It checks if the portfolio contains any stocks before attempting to save.
   *
   * @return A string message indicating the outcome of the operation.
   */
  @Override
  public String savePortfolio() {

    if (currentPortfolio.stockLength()) {
      return currentPortfolio.save();
    } else {
      return "Cannot Create, No Stock in portfolio";
    }
  }

  /**
   * Fetches a flexible portfolio by its number in the list of portfolios. This method
   * reads the portfolio's details from an XML representation and populates a FlexiblePortfolio
   * instance with stocks.
   *
   * @param number number of the portfolio in the list used to identify which portfolio to fetch.
   * @return FlexiblePortfolioInterface The fetched portfolio as a FlexiblePortfolioInterface.
   * @throws Exception if there is an error in fetching the portfolio.
   */
  @Override
  public FlexiblePortfolioInterface fetchFlexiblePortfolio(int number) throws Exception {
    if (number != 0) {
      if (number <= returnPortfolioList().length) {
        String name = returnPortfolioList()[number - 1];
        Document document = readXMLDocumentFromFile(path + name);
        name = name.replace(name.substring(name.length() - 4), "");
        FlexiblePortfolio portfolio = new FlexiblePortfolio(name);

        //Get all stocks
        NodeList nList = document.getElementsByTagName("stock");

        for (int temp = 0; temp < nList.getLength(); temp++) {
          Node node = nList.item(temp);
          if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            String symbol = eElement.getElementsByTagName("symbol").item(0).getTextContent();
            String[] transactDate = eElement.getElementsByTagName("transactDate").item(0)
                    .getTextContent().split(",");
            String[] transactQuantity = eElement.getElementsByTagName("transactQuantity")
                    .item(0).getTextContent().split(",");
            float quantity = Float.parseFloat(eElement.getElementsByTagName(
                    "quantity").item(0).getTextContent());

            Stock stock = new Stock(symbol, quantity);
            for (int i = 0; i < transactDate.length; i++) {
              stock.transact(transactDate[i], Float.parseFloat(transactQuantity[i]));
            }

            portfolio.stocksList.add(stock);
          }
        }

        return portfolio;
      } else {
        throw new IllegalArgumentException("Input portfolio number does not exist");
      }
    } else {
      throw new RuntimeException("Going back to main menu");
    }
  }
}


