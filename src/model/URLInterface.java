package model;

import java.io.IOException;

/**
 * Defines the contract for making URL calls to retrieve stock market data.
 * Implementations of this interface are responsible for fetching data from
 * a specified source using stock symbols.
 */
public interface URLInterface {
  StringBuilder urlCall(String symbol) throws IOException;
}
