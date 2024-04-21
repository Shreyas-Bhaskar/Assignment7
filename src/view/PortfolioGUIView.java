package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 * This class represents the GUI view for managing a specific stock portfolio, allowing users
 * to buy or sell stocks, inspect stock details, and view the portfolio's composition, value,
 * and cost basis.It extends JFrame, providing a window with various interactive components for
 * portfolio management.
 */
public class PortfolioGUIView extends JFrame implements PortfolioGUIViewInterface {
  String[] options;
  private final JButton buyButton;
  private final JButton sellButton;
  private final JButton compositionButton;
  private final JButton valueButton;
  private final JButton costButton;
  private final JButton inspectButton;
  private final JTextField date;
  private final JTextField buyDate;
  private final JTextField buyQuantity;
  private final JComboBox<String> buyCombobox;

  /**
   * Constructs a PortfolioGUIView with a specified portfolio name and options for stocks.
   * Initializes the GUI components and layouts for the portfolio management interface.
   *
   * @param name The name of the portfolio being managed.
   * @param options An array of stock symbols representing the stocks available for management.
   */
  public PortfolioGUIView(String name, String[] options) {
    super();
    this.setTitle("Manage Portfolio " + name);
    this.setSize(350, 410);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.options = options;

    this.setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel datePanel = new JPanel();
    datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel dateFormat = new JLabel("Enter all dates in YYYY-MM-DD format");
    mainPanel.add(datePanel);
    datePanel.add(dateFormat);

    JLabel dateLabel = new JLabel("Date:");
    JLabel quantityLabel = new JLabel("Quantity:");

    JPanel buyPanel = new JPanel();
    buyPanel.setBorder(BorderFactory.createTitledBorder("Which Stock do you want to buy or sell?"));
    buyCombobox = new JComboBox<>(options);
    buyPanel.add(buyCombobox);
    mainPanel.add(buyPanel);
    JPanel buyText = new JPanel();
    buyText.setLayout(new BoxLayout(buyText, BoxLayout.X_AXIS));
    buyPanel.add(buyText);
    buyText.add(dateLabel);
    buyDate = new JTextField(10);
    buyText.add(buyDate);
    buyText.add(quantityLabel);
    buyQuantity = new JTextField(8);
    buyText.add(buyQuantity);
    buyButton = new JButton("Buy Stock");
    buyPanel.add(buyButton);
    sellButton = new JButton("Sell Stock");
    buyPanel.add(sellButton);

    JPanel costValuePanel = new JPanel();
    costValuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    costValuePanel.setBorder(BorderFactory.createTitledBorder("Value and Cost Basis"));
    JLabel label = new JLabel("Date:");
    costValuePanel.add(label);
    mainPanel.add(costValuePanel);

    JPanel costPanel = new JPanel();
    costPanel.setLayout(new BoxLayout(costPanel, BoxLayout.X_AXIS));
    costValuePanel.add(costPanel);

    date = new JTextField(10);
    costPanel.add(date);
    valueButton = new JButton("Value");
    costPanel.add(valueButton);
    costButton = new JButton("Cost Basis");
    costPanel.add(costButton);


    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Other Portfolio Options"));
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    //buttons
    compositionButton = new JButton("Composition");
    buttonPanel.add(compositionButton);

    inspectButton = new JButton("Inspect Stock");
    buttonPanel.add(inspectButton);

    //quit button
    JButton quitButton = new JButton("Back");
    quitButton.addActionListener((ActionEvent e) -> this.dispose());
    buttonPanel.add(quitButton);

    add(mainPanel);
  }

  /**
   * Returns the selected index of the stock symbol from the combobox, indicating which stock
   * the user has chosen to buy or sell.
   *
   * @return The index of the selected stock symbol in the combobox.
   */
  public int getStockSymbol() {
    return buyCombobox.getSelectedIndex();
  }

  /**
   * Retrieves the date entered by the user for performing value and cost basis calculations.
   * Automatically clears the input field after retrieving the value.
   *
   * @return The date entered by the user.
   */
  public String getDate() {
    String result = date.getText();
    date.setText("");
    return result;
  }

  /**
   * Retrieves the date entered by the user for buying or selling a stock. Automatically clears
   * the input field after retrieving the value.
   *
   * @return The buy or sell date entered by the user.
   */
  public String getBuyDate() {
    String result = buyDate.getText();
    buyDate.setText("");
    return result;
  }

  /**
   * Retrieves the quantity entered by the user for the stock transaction. Automatically clears
   * the input field after retrieving the value.
   *
   * @return The quantity of stock to buy or sell, as entered by the user.
   */
  public String getQuantity() {
    String result = buyQuantity.getText();
    buyQuantity.setText("");
    return result;
  }

  /**
   * Makes the GUI view visible to the user. This method should be called to display the portfolio
   * management interface once it is ready.
   */
  //  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Sets an ActionListener for the buttons in the GUI view. This allows for handling of user
   * actions such as buying, selling, and requesting portfolio information.
   *
   * @param actionEvent The ActionListener to be set for the buttons.
   */
  //  @Override
  public void setButtonListener(ActionListener actionEvent) {
    buyButton.addActionListener(actionEvent);
    sellButton.addActionListener(actionEvent);
    compositionButton.addActionListener(actionEvent);
    valueButton.addActionListener(actionEvent);
    costButton.addActionListener(actionEvent);
    inspectButton.addActionListener(actionEvent);
  }

  /**
   * Displays a dialog box for the user to select a stock from the portfolio. Used when the user
   * wishes to inspect a specific stock's details.
   *
   * @return The index of the selected stock option.
   */
  //  @Override
  public int getStockOption() {
    return JOptionPane.showOptionDialog(PortfolioGUIView.this,
            "Please select a stock", "Options", JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
  }

  /**
   * Displays a message to the user in a dialog box. This method is useful for showing notifications,
   * warnings, and error messages.
   *
   * @param message The message to be displayed.
   */
  //  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }


}
