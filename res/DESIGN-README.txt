Design Overview:

The Model-View-Controller (MVC) design pattern is used in the architecture of the financial portfolio management application
enhancing modularity, scalability, and concern separation.
This structure allows the application to efficiently handle user interactions, data manipulation, and presentation of information.

Model Layer:

-Stock Class: Represents a single stock in the financial market, encapsulating attributes such as the stock's symbol and quantity owned.
This class provides the foundation for tracking individual investments within a portfolio.

-Portfolio Class: Manages a collection of Stock objects, representing an investor's portfolio.
It facilitates tasks like updating stock data, calculating the overall value of the portfolio, and adding new stocks.
An investment portfolio is fully represented by each Portfolio object, which also includes serialisation methods for saving the portfolio to disc.

-Manager Class: Serves as earlier application's main coordinator. It makes portfolio management activities easier, such as making new portfolios and storing portfolios,
obtaining and updating stock data from external APIs. Data integrity and ease of retrieval are ensured via the Manager class's interface with the file system,
which saves portfolio data in XML format.

New Classes in Model for Assignment 5:

Flexible Portfolio Class:The FlexiblePortfolio class enriches portfolio management with dynamic stock transactions, allowing buying and selling on specific dates.
It introduces cost basis calculation for financial insight, performance visualization for graphical analysis over time, and detailed transaction tracking, distinguishing
it from the basic Portfolio with a focus on in-depth investment records.

Flexible Manager Class:The `FlexibleManager` class builds on `Manager` to introduce `FlexiblePortfolios` for dynamic stock transactions and in-depth financial tracking.
It redefines `createPortfolio` to craft `FlexiblePortfolio` instances for nuanced portfolio management, incorporates methods for detailed stock transactions including dates,
and enhances data fetching to handle `FlexiblePortfolios'` intricate structures, elevating portfolio management and financial insight capabilities.

New Classes in Model for Assignment 6:
A new class for API call for Alphavantage
A new class for strategy

View Layer:

-View Interface and Implementation: Responsible for all user interactions and presentations.
The view displays menus, options, and portfolio data to the user while capturing user inputs.
It guarantees a distinct division of user interface logic from application logic by abstracting the complexity of data display from the controller.

New Class in View for Assignment 5:

The `FlexibleView` class extends the basic `View` class, incorporating advanced user interactions and data presentation capabilities suited for managing dynamic and complex portfolios.
Unlike `View`, it offers specialized menu options for managing portfolios, such as buying or selling stock, viewing portfolio composition and value, and even generating visual graphs of portfolio performance.
Additionally, it handles stock-specific interactions, allowing users to inspect stocks, analyze their performance over time, and make informed decisions based on detailed statistical analyses.
This extension provides a more nuanced and flexible interface, catering to users needing comprehensive portfolio management tools within the MVC architecture.

New Classes in View for Assignment 6:
New View classes added to faciliate GUI interaction

Controller Layer:

-Controller Class: Serves as the intermediary between the model and view layers.
It interprets user inputs captured by the view, processes these inputs , and updates the view accordingly.
It guarantees a distinct division of user interface logic from application logic by abstracting the complexity of data display from the controller.

New Classes in Controller for Assignment 5:

Flexible Controller:The `FlexibleController` enhances the application by enabling dynamic stock transactions, such as buying and selling on specific dates, and integrates with
`FlexiblePortfolio` for detailed investment tracking. It provides advanced user interactions for nuanced portfolio management, including transaction date specifics and financial insights.
This controller facilitates a more sophisticated approach to portfolio visualization and performance analysis.
It represents a key advancement in handling complex user requests and portfolio management strategies within the MVC architecture.

PortfolioController:The `PortfolioController` class in a MVC architecture manages user interactions for a single portfolio, facilitating actions like buying and selling stocks, viewing portfolio composition,
and checking its value on specific dates. It leverages `FlexiblePortfolioInterface` for portfolio operations and `FlexibleViewInterface` for displaying information.
Key functionalities include detailed stock inspection, transaction execution with date validation, and navigation through a portfolio-specific menu, enhancing the application's capability to handle dynamic investment
strategies and perform detailed financial analysis.

StockController:The `StockController` class is designed to manage user interactions related to individual stock operations within a financial portfolio management application, interfacing with both `StockInterface` for stock-related functionalities
and `FlexibleViewInterface`for user interaction. It orchestrates activities such as displaying stock-specific options, executing stock analysis actions (like calculating gain/loss and moving averages), and handling user inputs through a looped menu
system.

AbstractController:The `AbstractController` class serves as a base for other controller classes within the application, providing essential utility methods such as date validation.
It includes a method to validate whether a given date string is in the correct format ("yyyy-MM-dd") and ensures that the date is not in the future relative to the system's current date, facilitating input validation across various controllers.

New Classes in controller for Assignment 6:
New Controller classes added to faciliate GUI interaction

Testing Framework:

The application adopts a rigorous testing strategy to ensure reliability and correctness, with expanded coverage to include new components. Tests are meticulously organized around the core functionalities and the enhanced features:

Unit Tests for Model Classes: These tests validate the internal logic of both foundational and newly added classes within the model layer, including the Stock, Portfolio, and FlexiblePortfolio classes. Specific attention is given to verifying stock addition,
value calculation, portfolio serialization, and the dynamic functionalities introduced by the FlexiblePortfolio class.

Integration Tests: Focused on assessing the seamless interaction between the application's backend and external financial data APIs, these tests confirm accurate data fetching and robust error handling. The integration tests have been broadened to cover the
interactions involving the FlexibleManager class, ensuring its API communications are reliable.

Controller Testing with Mock Classes: By leveraging mock classes, this testing phase meticulously simulates the behavior of the model layer, isolating the controller to validate its processing of user inputs and its management of user interaction flows.
Expanded testing now includes the FlexibleController, PortfolioController, and StockController, offering a comprehensive evaluation of how these controllers handle navigation, stock transactions, and user commands without direct dependency on the model
instances or external services.

Newly Added Tests for Enhanced Components: Additional tests have been integrated to specifically address the functionalities of the StockController, FlexibleController, FlexibleManager, PortfolioController, and StockController. These tests are designed to ensure
that the new features operate as intended, with a focus on the robustness of stock analysis, portfolio management, and the dynamic handling of stock transactions.

Features:

- Dynamic Portfolio Management: Users can create multiple portfolios, add stocks, and manage investments seamlessly, leveraging real-time data for informed decision-making.

- Real-time Financial Data: Integrates with financial data APIs to fetch the latest stock prices and updates, ensuring that portfolio valuations are always current.

- Persistent Data Storage: Utilizes XML files for data persistence, enabling users to maintain their investment data across sessions. This approach facilitates easy data recovery and manipulation.

- User-friendly Interface: Offers a streamlined user experience with clear options for managing portfolios, adding stocks, and viewing investment summaries.

New Features:

- Dynamic Portfolio Management with added Features: Users can now also buy and sell shares on specific dates, dynamically adjusting their portfolios for accurate tracking and management.

- Real-time Financial Data: Enhanced with cost basis and portfolio value calculations, providing a deeper insight into the total investment and current value as of a specific date.

- Persistent Data Storage: Ensures all transactions and calculations are saved, allowing for a comprehensive historical investment overview.

- User-friendly Interface: Augmented by stock trend statistics, offering daily/periodic gains or losses, moving averages, and crossover trends for informed decision-making.

- Performance Over Time: Newly added visualization feature depicts the performance of stocks or portfolios over time through a text-based horizontal bar chart,
  offering a succinct and informative performance snapshot.

New Features for Assignment 6:

Please refer readme.txt for new features added for assignment 6

In the recent update to our design, we've made a few strategic enhancements to improve functionality and maintain adherence to the SOLID principles. Notably:

- The `checkDate` function, initially exclusive to the `FlexibleManager`, has been abstracted out. This change allows `PortfolioController` and `StockController`
  direct access to this functionality,essential for validating date inputs without necessitating access to the `FlexibleManager`.

- Some private variables have been modified to protected access. This adjustment is aimed at facilitating better inheritance patterns,
  allowing derived classes more direct access to necessary fields while still preserving encapsulation to a reasonable extent.

- The `Stock` class, which was previously a private class, now implements a public interface.

These modifications were implemented with careful consideration to avoid significant alterations to the existing code structure.
By extending previous interfaces and classes rather than introducing broad changes, we aimed to preserve the original design's integrity and maintainability.
This approach reflects our commitment to the SOLID principles, ensuring that our application remains robust, adaptable, and easy to understand for both users and developers.

The architecture of the application is described in this design document, which prioritises user experience, extensibility, and maintainability.
The programme seeks to offer a dependable and effective tool for managing personal investments by following the MVC pattern and implementing comprehensive testing.

Design Changes for Assignment 6:

A new class added for api call

Two new methods added to FlexiblePortfolio and one new method added to FlexibleManager to support new features
