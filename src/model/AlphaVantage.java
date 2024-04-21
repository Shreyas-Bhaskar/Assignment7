package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Implementation of the URLInterface for fetching stock data from Alpha Vantage.
 * This class encapsulates the logic for constructing and executing API calls
 * to the Alpha Vantage service using a provided API key.
 */
public class AlphaVantage implements URLInterface {

  private String apiKey;

  /**
   * Constructs an AlphaVantage instance with a specified API key.
   *
   * @param apiKey The API key for Alpha Vantage API access.
   */
  public AlphaVantage(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * Fetches daily time series data for a given stock symbol from Alpha Vantage.
   * Constructs the request URL with the provided symbol and the stored API key,
   * then executes the call, returning the response data as a StringBuilder object.
   *
   * @param symbol The stock symbol for which to fetch time series data.
   * @return A StringBuilder containing the CSV-formatted time series data.
   * @throws IOException If an I/O error occurs when sending or receiving data.
   */
  @Override
  public StringBuilder urlCall(String symbol) throws IOException {
    URL url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_DAILY"
            + "&outputsize=full"
            + "&symbol"
            + "=" + symbol + "&apikey=" + apiKey + "&datatype=csv");
    InputStream in = url.openStream();
    StringBuilder sb = new StringBuilder();
    for (int ch; (ch = in.read()) != -1; ) {
      sb.append((char) ch);
    }
    return sb;
  }
}
