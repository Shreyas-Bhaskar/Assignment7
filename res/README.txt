Stock Portfolio Manager README

Overview:

The Stock Portfolio Manager is a detailed application designed for individual investors who want to oversee
and monitor their investment portfolios.
This programme, which prioritises accessibility and ease of use,
provides a number of features to assist users in managing their investment strategies effectively,
staying informed about their assets, and making decisions based on current facts.

Key Features:

Portfolio Creation: Enables users to create various portfolios,
facilitating the organization of investments based on distinct strategies or financial objectives.

Stock Addition: Supports adding stocks to any portfolio through the input of the stock symbol and quantity owned,
offering precise tracking and management capabilities.

Portfolio Viewing: Provides comprehensive overviews of portfolio contents, displaying the symbol, quantity, and price of each stock,
granting investors a quick snapshot of their investment distribution.

Value Calculation:Determines the total value of a portfolio on a specific date,
providing investors with an assessment of their holdings based on the stock prices available for that day.

Real-time Updates: Retrieves the most recent stock data from financial APIs, ensuring portfolio valuations reflect the latest market prices.

Data Persistence: Securely stores portfolio information,
allowing users to maintain and access their investment data over multiple sessions without losing any information.

New Features Added for Assignment 5:

Flexible Portfolios:

Buy and Sell Shares: Purchase or sell a specific number of shares of a stock on a given date and adjust the portfolio accordingly.

Cost Basis Calculation: Determine the total money invested in a portfolio by a specific date, including all purchases made until that date.

Portfolio Value Calculation: Assess the value of a portfolio on a specific date, considering only the stocks purchased up to that date.

Stock Trend Statistics:

Daily and Periodic Gains or Losses: Evaluating stock performance based on opening vs. closing prices and overall trends.

Moving Averages and Crossovers: Calculate x-day moving averages and identify crossover days signaling buy or sell opportunities.

Moving Crossover Trends: Detect trends through the analysis of moving average crossovers over specified periods.

New Features added for Assignment 6:

Investment Strategy Implementation:

Fixed Amount Investment: Users can now allocate a fixed amount across a portfolio containing multiple stocks, with the ability
to specify the percentage weight for each stock. This feature supports the purchase of fractional shares, allowing for precise
investment distributions according to user-defined weights.

Dollar-Cost Averaging Strategy: The application supports creating and managing portfolios using a dollar-cost averaging approach.
Users can specify an amount to invest at regular intervals over a set period or indefinitely without an end date.
This strategy automates investment on the specified frequency, adjusting for holidays by investing on the next available day using end-of-day prices.

Graphical User Interface Enhancements:

Flexible Portfolio Creation and Management: The GUI now allows for the creation of new flexible portfolios, providing users with a visual tool to manage their investments more intuitively.

Enhanced Stock Transaction Capabilities: Users can buy or sell specific quantities of stocks on selected dates directly through the GUI, offering a more interactive and streamlined process.

Cost Basis and Portfolio Value Queries: The interface includes features to query the cost basis and the value of flexible portfolios as of a certain date, enabling users to evaluate their investments' performance visually.

File Operations for Portfolios: Users can save their flexible portfolios to files for persistence and load them back into the application, ensuring long-term tracking and management of their investment strategies.

Comprehensive Stock Statistics: Building on the previous assignment, the GUI provides detailed statistics for individual stocks, including daily and periodic performance, moving averages, and trend analyses.


Performance Over Time:

A feature to visually represent the performance of stocks or portfolios over time using a text-based horizontal bar chart.
This visualization offers a quick glance at investment performance across a specified timeframe, with the scale and resolution
adapted to provide a clear and informative overview.

Testing Strategy

A thorough testing strategy, comprising unit tests, integration tests, manual testing
covering a range of use cases and possible fault scenarios, and controller-specific testing using fake classes,
ensures the robustness and dependability of our application.

Unit Testing

JUnit: We employ JUnit for unit testing, isolating individual components to verify their correctness.
Mock Classes:  We utilize mock classes specifically designed to simulate the behavior of the model and view components.
This approach allows us to effectively test the controller's handling of user inputs and its interactions with the model
and view layers without the need for an external mocking framework.

Integration Testing

Tests interactions between components, verifying that they work together as expected.
Validates the integration with external APIs for fetching stock prices, ensuring that the application responds correctly to API responses.

Test Coverage Areas

Functional Tests: Validate the functionality of each feature, including creating portfolios, adding stocks, viewing, updating portfolios,
purchasing and selling shares within flexible portfolios, calculating the cost basis and value of portfolios, analyzing stock trend statistics,
and visualizing performance over time. These tests ensure that all functionalities, operate as intended, providing a comprehensive assessment of
the application's capabilities in managing and analyzing investment portfolios.

Additional test cases for assignment 6: Additional tests cases have been added to test fixed amount investing and dollar cost averaging strategy.

Error Handling: Checks how the programme handles faulty inputs and scenarios—such as wrong stock names, or symbols, invalid quantity, invalid dates—
to make sure it handles them gracefully.

Controller Tests: Specifically tests the controller component using mock classes for the model, ensuring correct handling of user inputs
and integration between the model and view layers.