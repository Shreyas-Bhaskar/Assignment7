package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * The Manager class implements {@link ManagerInterface} is responsible for managing portfolios
 * within the application. It handles operations such as creating new portfolios, fetching
 * existing portfolios, adding stocks to portfolios, and deleting portfolios. This class
 * serves as the central point of interaction with the portfolio data stored on the file system.
 */
public class Manager implements ManagerInterface {
  protected final String path;
  protected final File folder;
  protected Portfolio currentPortfolio;

  /**
   * Constructs a Manager object, takes in path as input which specifies the folder at which the
   * portfolios will be saved.
   */
  public Manager() {
    this.path = System.getProperty("user.dir") + "/portfolios/";
    folder = new File(this.path);
  }

  /**
   * Reads and parses an XML document from a specified file path.
   *
   * @param filePath the path to the XML file to be read
   * @return a Document object representing the parsed XML document
   * @throws Exception if any error occurs during file reading or parsing
   */
  protected static Document readXMLDocumentFromFile(String filePath) throws Exception {

    //Get Document Builder
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    //Build Document
    Document document = builder.parse(new File(filePath));

    //Normalize the XML Structure; It's just too important !!
    document.getDocumentElement().normalize();

    return document;
  }

  /**
   * Returns a list of portfolio names found in the manager's folder in sorted order.
   *
   * @return an array of strings containing the names of the portfolios
   */
  public String[] returnPortfolioList() {
    String[] list = folder.list();
    if (list != null) {
      Arrays.sort(list);
    }
    return list;
  }

  /**
   * Validates the given name for creating a new portfolio.
   * The name must not be null, empty, start with non-letter characters
   * It also checks for name uniqueness in existing portfolios.
   *
   * @param name the name to validate for a new portfolio
   * @return true if the name is valid, false otherwise
   */
  boolean validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
      return false;
    }
    if (!Character.isLetter(name.charAt(0)) && name.charAt(0) != '_') {
      return false;
    }
    if (name.length() >= 3 && name.substring(0, 3).equalsIgnoreCase("xml")) {
      return false;
    }
    for (char c : name.toCharArray()) {
      if (!Character.isLetterOrDigit(c) && c != '-' && c != '_' && c != '.') {
        return false;
      }
    }
    String[] existingPortfolios = returnPortfolioList();
    for (String fileName : existingPortfolios) {
      if (fileName.equalsIgnoreCase(name + ".xml")) {
        return false;
      }
    }
    return true;
  }

  /**
   * Saves the daily time series data for a given stock symbol as a CSV file.
   * The data is fetched from the Alpha Vantage API.
   * The CSV file is stored in the 'data' directory within the user's current working directory.
   *
   * @param symbol the stock symbol for which to fetch and save data
   * @return true if the data is successfully fetched and saved, false otherwise
   * @throws RuntimeException         if the fetch data URL is malformed or the API has changed
   * @throws IllegalArgumentException if no price data could be found for the given symbol
   */
  protected boolean saveData(String symbol) {
    try {
      URLInterface url = new AlphaVantage("EEEYJNAZOVJWJ5PQ");
      StringBuilder sb = url.urlCall(symbol);
      if (sb.toString().contains("Error")) {
        throw new IllegalArgumentException("Incorrect Stock Symbol");
      } else if (sb.toString().contains("Information")) {
        throw new IllegalArgumentException("Exceeded number of API Calls, try again tomorrow");
      } else {
        String path = System.getProperty("user.dir") + "/data/" + symbol + ".csv";
        File targetFile = new File(path);
        try (PrintWriter writer = new PrintWriter(targetFile)) {
          writer.write(sb.toString());
          return true;
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Can't save for stock: " + symbol +
              " - Error: " + e.getMessage() + "\r\n");
    }
  }

  /**
   * Attempts to create a new portfolio with the given name if it passes validation checks.
   * Throws IllegalArgumentException if the name is invalid.
   *
   * @param name the name for the new portfolio
   * @return true if the portfolio was created successfully, false if the operation was canceled
   * @throws IllegalArgumentException if the given name fails validation checks
   */
  @Override
  public boolean createPortfolio(String name) throws IllegalArgumentException {
    // validate here
    // if successful, create new portfolio and return success
    // else return validation error
    if (!name.equals("0")) {
      if (validateName(name)) {
        currentPortfolio = new Portfolio(name);
        return true;
      } else {
        throw new IllegalArgumentException("Invalid Name Enter Again");
      }
    } else {
      return false;
    }
  }

  /**
   * Adds a stock to the current portfolio if its data can be successfully fetched and saved.
   *
   * @param symbol        the stock symbol to add
   * @param stockQuantity the quantity of the stock to add
   * @return a message indicating the outcome of the operation
   */
  @Override
  public String addStock(String symbol, int stockQuantity) {
    try {
      if (saveData(symbol) && stockQuantity > 0) {
        return currentPortfolio.addStock(symbol.toUpperCase(), stockQuantity);
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
   * Saves the current portfolio to a specified path.
   * Returns a success message if the portfolio contains stocks, otherwise returns an error message.
   *
   * @return a message indicating the outcome of the save operation
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
   * Fetches a portfolio by its number in the list,
   * Populating it with stocks from its XML representation.
   *
   * @param number the portfolio's number in the list
   * @return a Portfolio representing the fetched portfolio
   * @throws Exception if there's an error in fetching the portfolio
   */
  @Override
  public Portfolio fetchPortfolio(int number) throws Exception {
    if (number != 0) {
      if (number <= returnPortfolioList().length) {
        String name = returnPortfolioList()[number - 1];
        Portfolio portfolio = new Portfolio(name);
        Document document = readXMLDocumentFromFile(path + name);

        NodeList nList = document.getElementsByTagName("stock");

        for (int temp = 0; temp < nList.getLength(); temp++) {
          Node node = nList.item(temp);
          if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            String symbol = eElement.getElementsByTagName("symbol").item(0).getTextContent();
            int quantity = Integer.parseInt(eElement.getElementsByTagName(
                    "quantity").item(0).getTextContent());
            portfolio.addStock(symbol, quantity);
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

  /**
   * Deletes a portfolio by its number in the list.
   * If the number is valid and deletion is successful, returns a success message.
   * Otherwise, returns an error message or indicates going back to the main menu.
   *
   * @param number the portfolio's number in the list (1-based index)
   * @return a message indicating the outcome of the deletion operation
   */
  @Override
  public String deletePortfolio(int number) {
    if (number != 0) {
      if (number <= returnPortfolioList().length) {
        File portfolio = new File(path + returnPortfolioList()[number - 1]);
        return portfolio.delete() ? "Delete Successful" : "Delete Failed";
      } else {
        return "Input portfolio number does not exist";
      }
    } else {
      return "Going back to main menu";
    }
  }
}
