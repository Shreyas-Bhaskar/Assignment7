package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

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

  private final JButton addButtonDAC;

  private final JButton addButtonStrat;


  private final JTextField amountDAC;
  private final JTextField amountStrat;
  private final JTextField period;
  private final JTextField startDate;
  private final JTextField endDate;
  private final JPanel inputPanelDAC;

  private final JPanel inputPanelStrat;

  private final List<JTextField> dateDACFields;
  private final List<JTextField> percentDACFields;

  private final JTextField[] dateDACFieldsArr;
  private final JTextField[] percentDACFieldsArr;

  private final List<JTextField> percentStratFields;

  private final JTextField[] percentStratFieldsArr;
  private final List<JLabel> labelsDAC;
  private final List<JLabel> labelsStrat;
  private final List<JPanel> panelsDAC;
  private final List<JPanel> panelsStrat;
  private final String name;
  private final JTextField date;
  private final JTextField buyDate;
  private final JTextField buyQuantity;
  private final JComboBox<String> buyCombobox;

  private final JButton rebalanceButton;

  private final JTextField rebalanceDate;
  private final JPanel inputPanelRebalance;
  private final Map<String, JTextField> stockWeights;



  /**
   * Constructs a PortfolioGUIView with a specified portfolio name and options for stocks.
   * Initializes the GUI components and layouts for the portfolio management interface.
   *
   * @param name The name of the portfolio being managed.
   * @param options An array of stock symbols representing the stocks available for management.
   */
  public PortfolioGUIView(String name, String[] options) {
    super();
    this.name = name;
    this.setTitle("Manage Portfolio " + name);
    this.setSize(350, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.options = options;

    this.setLayout(new BorderLayout());

    labelsDAC = new ArrayList<>();
    labelsStrat = new ArrayList<>();

    panelsDAC = new ArrayList<>();
    panelsStrat = new ArrayList<>();

    dateDACFields = new ArrayList<>();
    percentDACFields = new ArrayList<>();

    percentStratFields = new ArrayList<>();

    dateDACFieldsArr = new JTextField[options.length];
    percentDACFieldsArr = new JTextField[options.length];
    percentStratFieldsArr = new JTextField[options.length];

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel datePanel = new JPanel();
    datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel dateFormat = new JLabel("Enter all dates in YYYY-MM-DD format");
    mainPanel.add(datePanel);
    datePanel.add(dateFormat);

    inputPanelDAC = new JPanel();
    inputPanelDAC.setBorder(BorderFactory.createTitledBorder("Add With "
            + "Dollar Cost Averaging"));
    inputPanelDAC.setLayout(new BoxLayout(inputPanelDAC, BoxLayout.Y_AXIS));
    JScrollPane mainScrollPane2 = new JScrollPane(inputPanelDAC);

    inputPanelStrat = new JPanel();
    inputPanelStrat.setBorder(BorderFactory.createTitledBorder("Add Socks with Strategy"));
    inputPanelStrat.setLayout(new BoxLayout(inputPanelStrat, BoxLayout.Y_AXIS));
    JScrollPane mainScrollPane3 = new JScrollPane(inputPanelStrat);

    mainPanel.add(mainScrollPane2, BorderLayout.SOUTH);
    mainPanel.add(mainScrollPane3, BorderLayout.SOUTH);


    // dac panel


    JPanel namePanel2 = new JPanel();

    namePanel2.add(new JLabel("Amount"));
    amountDAC = new JTextField(8);
    namePanel2.add(amountDAC);
    inputPanelDAC.add(namePanel2);


    JPanel inputButtons2 = new JPanel();
    inputButtons2.setLayout(new BoxLayout(inputButtons2, BoxLayout.X_AXIS));
    JButton addFieldButton2 = new JButton("Add Stock");
    addFieldButton2.addActionListener(new AddFieldListenerDAC());
    inputButtons2.add(addFieldButton2);
    addButtonDAC = new JButton("Save DAC Portfolio");
    inputButtons2.add(addButtonDAC);
    inputPanelDAC.add(inputButtons2);

    // input panel 3

    JPanel namePanel3 = new JPanel();
    namePanel3.add(new JLabel("Amount"));
    amountStrat = new JTextField(5);
    namePanel3.add(amountStrat);
    namePanel3.add(new JLabel("Period"));
    period = new JTextField(3);
    namePanel3.add(period);
    inputPanelStrat.add(namePanel3);

    JPanel datePanel3 = new JPanel();
    datePanel3.add(new JLabel("Start"));
    startDate = new JTextField(5);
    datePanel3.add(startDate);
    datePanel3.add(new JLabel("End"));
    endDate = new JTextField(5);
    datePanel3.add(endDate);

    inputPanelStrat.add(datePanel3);

    JPanel inputButtons3 = new JPanel();
    inputButtons2.setLayout(new BoxLayout(inputButtons2, BoxLayout.X_AXIS));
    JButton addFieldButton3 = new JButton("Add Stock");
    addFieldButton3.addActionListener(new AddFieldListenerStrat());
    inputButtons3.add(addFieldButton3);
    addButtonStrat = new JButton("Save Strategy Portfolio");
    inputButtons3.add(addButtonStrat);
    inputPanelStrat.add(inputButtons3);


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

    // Inside the constructor of PortfolioGUIView:
    inputPanelRebalance = new JPanel();
    inputPanelRebalance.setBorder(BorderFactory.createTitledBorder("Rebalance Portfolio"));
    inputPanelRebalance.setLayout(new BoxLayout(inputPanelRebalance, BoxLayout.Y_AXIS));
    JScrollPane rebalanceScrollPane = new JScrollPane(inputPanelRebalance);

    datePanel.add(new JLabel("Rebalance Date:"));
    rebalanceDate = new JTextField(10);
    datePanel.add(rebalanceDate);
    inputPanelRebalance.add(datePanel);

    stockWeights = new HashMap<>();
    for (String option : options) {
      JPanel stockPanel = new JPanel();
      JLabel stockLabel = new JLabel(option + " Weight (%):");
      JTextField weightField = new JTextField(5);
      stockWeights.put(option, weightField);
      stockPanel.add(stockLabel);
      stockPanel.add(weightField);
      inputPanelRebalance.add(stockPanel);
    }

    rebalanceButton = new JButton("Rebalance Portfolio");
    inputPanelRebalance.add(rebalanceButton);

    mainPanel.add(rebalanceScrollPane);

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
   * Retrieves the name of the portfolio being managed by this GUI view.
   * This can be useful for displaying the portfolio name in the user interface or when logging
   * activities.
   *
   * @return The name of the managed portfolio.
   */
  @Override
  public String getName() {
    return name;
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

  public String getRebalanceDate() {
    return rebalanceDate.getText().trim();
  }

  public Map<String, JTextField> getStockWeights() {
    return stockWeights;
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
    addButtonDAC.addActionListener(actionEvent);
    addButtonStrat.addActionListener(actionEvent);
    rebalanceButton.addActionListener(actionEvent);
    // Inside setButtonListener method in PortfolioGUIView:

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

  public String getPeriod() {
    return period.getText();
  }

  /**
   * Retrieves the percentage values entered by the user for a specified investment strategy.
   * This method collects the percentage data from the GUI fields based on whether the user
   * is managing a Dollar-Cost Averaging strategy ("DAC") or another strategy type. It extracts
   * the data from the respective GUI input fields and stores them in a list.
   *
   * @param type A string identifier to select the investment strategy type, expecting "DAC" for
   *             Dollar-Cost Averaging or other identifiers for different strategies.
   * @return A list of strings representing the percentages specified by the user for the
   *         selected investment strategy.
   */
  public List<String> getPercents(String type) {
    List<String> percents = new ArrayList<>();
    if ("DAC".equals(type)) {
      for (JTextField field : percentDACFields) {
        percents.add(field.getText());
      }
    } else {
      for (JTextField field : percentStratFields) {
        percents.add(field.getText());
      }
    }
    return percents;
  }

  /**
   * Retrieves the total investment amount specified by the user for either Dollar-Cost Averaging
   * (DAC) or another specified investment strategy.
   * The method differentiates between the strategy types based on the input type and returns the
   * relevant amount from the GUI input field.
   *
   * @param type A string identifier to select the investment strategy type, expecting "DAC" for
   *             Dollar-Cost Averaging or another identifier for different strategies.
   * @return A string representing the total amount to be invested as specified by the user for
   *         the selected investment strategy.
   */
  public String getAmounts(String type) {
    if ("DAC".equals(type)) {
      return amountDAC.getText();
    } else {
      return amountStrat.getText();
    }
  }

  /**
   * Retrieves the dates associated with each stock entered by the user.
   *
   * @return a list of dates as strings in the format YYYY-MM-DD.
   */
  public List<String> getDates(String type) {

    List<String> dates = new ArrayList<>();
    if ("DAC".equals(type)) {
      for (JTextField field : dateDACFields) {
        dates.add(field.getText());
      }
    } else if ("Strat".equals(type)) {
      dates.add(startDate.getText());
      dates.add(endDate.getText());
    }
    return dates;
  }

  /**
   * Displays a message to the user in a dialog box. This method is useful for showing
   * notifications,
   * warnings, and error messages.
   *
   * @param message The message to be displayed.
   */
  //  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  /**
   * Removes all dynamically added fields for entering stock information from the GUI.
   * This method is typically called after the user has saved a portfolio and wishes to
   * start fresh with a new one.
   */
  public void removeFields() {
    for (JLabel label : labelsDAC) {
      inputPanelDAC.remove(label);
    }
    for (JPanel panel : panelsDAC) {
      inputPanelDAC.remove(panel);
    }
    for (JLabel label : labelsStrat) {
      inputPanelStrat.remove(label);
    }
    for (JPanel panel : panelsStrat) {
      inputPanelStrat.remove(panel);
    }
    dateDACFields.clear();
    percentDACFields.clear();
    labelsDAC.clear();

    percentStratFields.clear();
    labelsStrat.clear();

    amountDAC.setText("");
    amountStrat.setText("");
    period.setText("");
    startDate.setText("");
    endDate.setText("");
    revalidate();
    repaint();

  }

  private class AddFieldListenerDAC implements ActionListener {

    /**
     * Responds to action events by adding new input fields for stock information into the GUI.
     * This allows the user to enter multiple stocks into a portfolio.
     *
     * @param e the action event triggered by clicking the "Add Stock" button.
     */
    public void actionPerformed(ActionEvent e) {

      int count = 0;


      for (String option:options) {


        dateDACFieldsArr[count] = new JTextField(6);
        percentDACFieldsArr[count] = new JTextField(6);

        dateDACFields.add(dateDACFieldsArr[count]);
        percentDACFields.add(percentDACFieldsArr[count]);

        JLabel titleLabel = new JLabel("Add Stock: " + option);

        labelsDAC.add(titleLabel);

        inputPanelDAC.add(titleLabel);

        JPanel symbolPanel = new JPanel();
        inputPanelDAC.add(symbolPanel);

        symbolPanel.add(new JLabel("Date: "));
        symbolPanel.add(dateDACFieldsArr[count]);

        symbolPanel.add(new JLabel("Percent: "));
        symbolPanel.add(percentDACFieldsArr[count]);

        panelsDAC.add(symbolPanel);
        revalidate();
        repaint();

        count ++;
      }
    }
  }

  private class AddFieldListenerStrat implements ActionListener {

    /**
     * Responds to action events by adding new input fields for stock information into the GUI.
     * This allows the user to enter multiple stocks into a portfolio.
     *
     * @param e the action event triggered by clicking the "Add Stock" button.
     */
    public void actionPerformed(ActionEvent e) {

      int count = 0;

      for (String option:options) {
        percentStratFieldsArr[count] = new JTextField(8);

        percentStratFields.add(percentStratFieldsArr[count]);

        JLabel titleLabel = new JLabel("Add Stock: " + option);

        labelsStrat.add(titleLabel);

        inputPanelStrat.add(titleLabel);

        JPanel symbolPanel = new JPanel();
        inputPanelStrat.add(symbolPanel);

        symbolPanel.add(new JLabel("Percent: "));
        symbolPanel.add(percentStratFieldsArr[count]);

        panelsStrat.add(symbolPanel);


        revalidate();
        repaint();
        count++;
      }
    }
  }
}


