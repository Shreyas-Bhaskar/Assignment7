Running the Stock Portfolio Manager Application

This guide will help you get started with running the application from a JAR file.

Requirements:

Java Runtime Environment.

Setup Instructions:

Place Required Files:
Ensure that the StockPortfolioManager.jar file is in a dedicated folder.
Make sure that the folder which has the jar file has the folders 'portfolio' and 'data'.

Open Terminal or Command Prompt:
Navigate to the folder containing the StockPortfolioManager.jar file, portfolio and data files using the terminal.

Run the Application:
Execute the following command to start the application:

java -jar StockPortfolioManager.jar

To operate the financial portfolio management application effectively and perform the specified tasks, follow these detailed instructions:

1. Launch the Application:
   - Start the application. The main menu will be displayed with a list of options.

2. Create First Portfolio:
   - Select option `[1] To add a portfolio` by typing `1` and pressing enter.
   - When prompted for a portfolio name, enter portfolio name e.g.,`testingportfolio1` and press enter.
   - For the symbol of the first stock, type e.g.,`AAPL` and press enter. Then, enter the quantity, e.g., `50`, and press enter.
   - Repeat the addition process for the second stock with the symbol e.g.,`GOOG` and a quantity of your choice, e.g., `30`.
   - For the third stock, use the symbol e.g.,`MSFT`, enter a quantity, e.g., `20`, and press enter.
   - After adding the third stock, type `0` and press enter to save the portfolio and return to the main menu.

3. Create Second Portfolio:
   - Again, select option `[1] To add a portfolio` from the main menu.
   - Enter the portfolio name as `testingportfolio2` and press enter.
   - Add the first stock to this portfolio with the symbol e.g.,`TSLA`, specify the quantity, e.g., `40`, and press enter.
   - For the second stock, enter another symbol, e.g., `GOOG`, specify the quantity, e.g., `25`, and press enter.
   - Type `0` and press enter to save this second portfolio and go back to the main menu.

4. Query Portfolio Values on a Specific Date:
   - Navigate to the Main Menu: Start by selecting option [2] To manage a portfolio. Do this by typing 2 and pressing Enter.
   - Select a Portfolio: You will be presented with a list of all available portfolios.
   - Type the number corresponding to the portfolio you wish to manage and press Enter.
   - Access Portfolio Actions: Once a portfolio is selected, a menu with various actions will appear.
   - View Portfolio Value: To check the portfolio's value, choose option [3] to view the value of a portfolio.
   - Select this by typing 3 and pressing Enter.
   - Enter the Date: You will be prompted to input the date for which you want to find the portfolio's value.
   - Please enter the date in the yyyy-MM-dd format and press Enter.
   - View Portfolio Details: The system will display a detailed list of stocks within the selected portfolio,
   - including each stock's price on the specified date, the quantity of stock present on that date, and the final value of the portfolio.

4. Find Cost Basis of a portfolio :
   - Navigate to the Main Menu: Begin by selecting the option [2] To manage a portfolio. Do this by typing 2 and pressing Enter.
   - Select a Portfolio: You'll see a list of all available portfolios. Type the number corresponding to the portfolio you wish to manage and press Enter.
   - Access Portfolio Actions: After selecting a portfolio, a menu with various actions will become available.
   - View Cost Basis: To check the portfolio's cost basis, choose option [6] to view the cost basis of a portfolio. Select this by typing 6 and pressing Enter.
   - Enter the Date: You will be prompted to input the date up to which you want to find the portfolio's value. Please enter the date in the yyyy-MM-dd format and press Enter.
   - View Cost Basis: The system will then display the cost basis of the portfolio up to the specified date, allowing you to understand the total invested amount over time.

5. To view Portfolio graph :
   - Navigate to the Main Menu: Begin by selecting the option [2] To manage a portfolio. Do this by typing 2 and pressing Enter.
   - Choose a Portfolio to Manage: From the list of your portfolios, enter the number corresponding to the portfolio you wish to view a graph for.
   - Access the Portfolio Graph Feature: Once managing the selected portfolio, enter 5 to choose to view the portfolio graph.
   - Enter the Starting Date: You will be prompted to enter the starting date for the graph. Ensure the date is in the YYYY-MM-DD format and is a current or past date.
   - Enter the Ending Date: Next, you will be asked to enter the ending date for the graph, following the same format and rules as the starting date.
   - Select the Type of Graph: Choose the granularity of the graph you wish to generate:

         Enter 1 for a daily graph.
         Enter 2 for a weekly graph.
         Enter 3 for a monthly graph.
         To return to the portfolio menu without generating a graph, enter 0.

   -View the Graph: After selecting the type of graph, the performance of the chosen portfolio between the specified dates will be displayed.

6. To inspect a stock in Portfolio:
   - At the main menu, select option [2] to manage a portfolio.
   - Enter the number corresponding to the portfolio you wish to manage from the list provided.
   - From the portfolio management menu, choose option [7] to inspect a stock in the portfolio.
   - Enter the stock number you want to inspect from the list of stocks in the selected portfolio.
   - You will then be presented with a range of actions to perform on the selected stock,
   - such as viewing gain/loss for a specific date or period, displaying moving averages, or identifying crossovers.
   - Select the desired action by entering the corresponding option number.

7. To add a portfolio with dollar cost averaging:
   - At the main menu, select option [5] and proceed with the instructions displayed.

8. To add a portfolio with strategy:
   - At the main menu, select option [6] and proceed with the instructions displayed.

9. Please Use the GUI created for Assignment 6 for more interactive user experience

Notes:
- The value displayed will be for the closest available date in the data.
- If the market was closed on the specified date, the value shown will be based on the last closing price.
- If the data for the specified date is not current, consider updating the portfolio from the main menu to ensure accuracy.

Supported Stocks and Date Limitations:

Stock Support:
Our program dynamically supports all stocks available through the Alpha Vantage API.
Offering users the flexibility to add and manage a diverse portfolio that aligns with their investment strategies.

Date Limitations for Value Determination:
The ability to determine the value of stocks is contingent on the availability of data from the Alpha Vantage API.
Generally, the API provides daily stock prices and historical data.
Users can query the value of their investments for any date that has corresponding data on the Alpha Vantage API,
typically ranging from the most recent trading day going several years back.

Please note:

The specific dates available for a given stock will depend on the stock's data coverage on the Alpha Vantage API.
Most stocks have data going back at least 20 years, but this can vary.
The application retrieves the closest available data to the specified date if no exact match is found.
For instance, if the market was closed on the requested date (e.g., weekends or holidays), the application will use the most recent trading day's data.
To ensure up-to-date information, users might need to update their portfolios regularly, especially if querying values for recent dates.

API Limitations:
Users should be aware of the Alpha Vantage API's rate limit of 75 requests per minute.
This limitation means that each minutr, data for up to 75 new stocks can be added, or updates for 75 existing stocks in the portfolio can be fetched from the API.
Beyond this limit, users will need to wait until the next minute for additional data requests.