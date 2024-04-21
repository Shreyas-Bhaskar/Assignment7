package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Represents the main graphical user interface (GUI) for this application.
 * This class provides the layout and functionality for creating, managing,
 * and updating stock portfolios through a user-friendly graphical interface.
 */
public class GUIView extends JFrame implements GUIViewInterface {

  private final JButton addButton;

  private final JButton addButtonDAC;

  private final JButton addButtonStrat;
  private final JButton manageButton;
  private final JButton removeButton;
  private final JButton updateButton;
  private final JTextField portfolioName;

  private final JTextField portfolioNameDAC;
  private final JTextField amountDAC;
  private final JTextField portfolioNameStrat;
  private final JTextField amountStrat;
  private final JTextField period;
  private final JTextField startDate;
  private final JTextField endDate;
  private final JPanel inputPanel;

  private final JPanel inputPanelDAC;

  private final JPanel inputPanelStrat;
  private final List<JTextField> symbolFields;
  private final List<JTextField> dateFields;
  private final List<JTextField> quantityFields;

  private final List<JTextField> symbolDACFields;
  private final List<JTextField> dateDACFields;
  private final List<JTextField> percentDACFields;

  private final List<JTextField> symbolStratFields;
  private final List<JTextField> percentStratFields;
  private final List<JLabel> labels;
  private final List<JLabel> labelsDAC;
  private final List<JLabel> labelsStrat;
  private final List<JPanel> panels;
  private final List<JPanel> panelsDAC;
  private final List<JPanel> panelsStrat;
  private int count;
  private int countDAC;
  private int countStrat;

  /**
   * Constructs the GUIView and initializes the user interface components for this application.
   * This includes buttons for adding, managing, and removing portfolios,as well as input fields
   * for entering portfolio and stock information.
   */
  public GUIView() {
    super();
    this.count = 0;
    this.countDAC = 0;
    this.countStrat = 0;

    symbolFields = new ArrayList<>();
    dateFields = new ArrayList<>();
    quantityFields = new ArrayList<>();

    labels = new ArrayList<>();
    labelsDAC = new ArrayList<>();
    labelsStrat = new ArrayList<>();

    panels = new ArrayList<>();
    panelsDAC = new ArrayList<>();
    panelsStrat = new ArrayList<>();

    symbolDACFields = new ArrayList<>();
    dateDACFields = new ArrayList<>();
    percentDACFields = new ArrayList<>();

    symbolStratFields = new ArrayList<>();
    percentStratFields = new ArrayList<>();

    this.setTitle("Stocks!");
    this.setSize(360, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel datePanel = new JPanel();
    datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel dateFormat = new JLabel("Enter all dates in YYYY-MM-DD format");
    mainPanel.add(datePanel);
    datePanel.add(dateFormat);


    inputPanel = new JPanel();
    inputPanel.setBorder(BorderFactory.createTitledBorder("Create New Portfolio"));
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(inputPanel);

    inputPanelDAC = new JPanel();
    inputPanelDAC.setBorder(BorderFactory.createTitledBorder("Create New Portfolio With "
            + "Dollar Cost Averaging"));
    inputPanelDAC.setLayout(new BoxLayout(inputPanelDAC, BoxLayout.Y_AXIS));
    JScrollPane mainScrollPane2 = new JScrollPane(inputPanelDAC);

    inputPanelStrat = new JPanel();
    inputPanelStrat.setBorder(BorderFactory.createTitledBorder
            ("Create New Portfolio with Strategy"));
    inputPanelStrat.setLayout(new BoxLayout(inputPanelStrat, BoxLayout.Y_AXIS));
    JScrollPane mainScrollPane3 = new JScrollPane(inputPanelStrat);


    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Other Portfolio Options"));
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    mainPanel.add(mainScrollPane, BorderLayout.SOUTH);
    mainPanel.add(mainScrollPane2, BorderLayout.SOUTH);
    mainPanel.add(mainScrollPane3, BorderLayout.SOUTH);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    // input panel

    JPanel namePanel = new JPanel();
    namePanel.add(new JLabel("Portfolio Name"));
    portfolioName = new JTextField(15);
    namePanel.add(portfolioName);
    inputPanel.add(namePanel);

    JPanel inputButtons = new JPanel();
    inputButtons.setLayout(new BoxLayout(inputButtons, BoxLayout.X_AXIS));
    JButton addFieldButton = new JButton("Add Stock");
    addFieldButton.addActionListener(new AddFieldListener());
    inputButtons.add(addFieldButton);
    addButton = new JButton("Save Portfolio");
    inputButtons.add(addButton);
    inputPanel.add(inputButtons);

    // input panel 2

    JPanel namePanel2 = new JPanel();
    namePanel2.add(new JLabel("Portfolio Name"));
    portfolioNameDAC = new JTextField(6);
    namePanel2.add(portfolioNameDAC);
    namePanel2.add(new JLabel("Amount"));
    amountDAC = new JTextField(5);
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
    namePanel3.add(new JLabel("Portfolio Name"));
    portfolioNameStrat = new JTextField(6);
    namePanel3.add(portfolioNameStrat);
    namePanel3.add(new JLabel("Amount"));
    amountStrat = new JTextField(5);
    namePanel3.add(amountStrat);
    inputPanelStrat.add(namePanel3);

    JPanel datePanel3 = new JPanel();
    datePanel3.add(new JLabel("Start"));
    startDate = new JTextField(5);
    datePanel3.add(startDate);
    datePanel3.add(new JLabel("End"));
    endDate = new JTextField(5);
    datePanel3.add(endDate);
    datePanel3.add(new JLabel("Period"));
    period = new JTextField(3);
    datePanel3.add(period);
    inputPanelStrat.add(datePanel3);

    JPanel inputButtons3 = new JPanel();
    inputButtons2.setLayout(new BoxLayout(inputButtons2, BoxLayout.X_AXIS));
    JButton addFieldButton3 = new JButton("Add Stock");
    addFieldButton3.addActionListener(new AddFieldListenerStrat());
    inputButtons3.add(addFieldButton3);
    addButtonStrat = new JButton("Save Strategy Portfolio");
    inputButtons3.add(addButtonStrat);
    inputPanelStrat.add(inputButtons3);


    //buttons
    manageButton = new JButton("Manage");
    buttonPanel.add(manageButton);

    removeButton = new JButton("Remove");
    buttonPanel.add(removeButton);

    updateButton = new JButton("Update");
    buttonPanel.add(updateButton);

    //quit button
    JButton quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    buttonPanel.add(quitButton);

    add(mainPanel);
  }

  /**
   * Makes the GUI visible to the user.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Sets the action listener for the main operation buttons in the GUI. This method allows the
   * controller to handle actions performed by the user, such as creating, managing, or removing
   * portfolios.
   *
   * @param actionEvent the action listener to be set for the buttons.
   */
  public void setButtonListener(ActionListener actionEvent) {
    addButton.addActionListener(actionEvent);
    addButtonDAC.addActionListener(actionEvent);
    addButtonStrat.addActionListener(actionEvent);
    manageButton.addActionListener(actionEvent);
    removeButton.addActionListener(actionEvent);
    updateButton.addActionListener(actionEvent);
  }

  /**
   * Retrieves the symbols of all stocks entered by the user.
   *
   * @return a list of stock symbols as strings.
   */
  public List<String> getSymbols(String type) {
    List<String> symbols = new ArrayList<>();
    if ("Strat".equals(type)) {
      for (JTextField field : symbolStratFields) {
        symbols.add(field.getText());
      }
    } else if ("DAC".equals(type)) {
      for (JTextField field : symbolDACFields) {
        symbols.add(field.getText());
      }
    } else {
      for (JTextField field : symbolFields) {
        symbols.add(field.getText());
      }
    }

    return symbols;
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
    } else {
      for (JTextField field : dateFields) {
        dates.add(field.getText());
      }
    }
    return dates;
  }

  /**
   * Retrieves the percentage values entered by the user for either Dollar-Cost Averaging (DAC)
   * or another specified investment strategy.
   * This method distinguishes between the two based on the input type and collects the respective
   * percentages from the GUI fields.
   *
   * @param type A string identifier to select the investment strategy type, expecting "DAC" for
   *            Dollar-Cost Averaging or another identifier for different strategies.
   * @return A list of strings, each representing the percentage (as entered by the user) to be
   * allocated to each stock in the portfolio.
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
   * the selected investment strategy.
   */
  public String getAmounts(String type) {
    if ("DAC".equals(type)) {
      return amountDAC.getText();
    } else {
      return amountStrat.getText();
    }
  }

  /**
   * Retrieves the quantities for each stock entered by the user.
   *
   * @return a list of quantities as strings.
   */
  public List<String> getQuantities() {
    List<String> quantities = new ArrayList<>();
    for (JTextField field : quantityFields) {
      quantities.add(field.getText());
    }
    return quantities;
  }

  /**
   * Retrieves the name of the portfolio entered by the user.
   *
   * @return the portfolio name as a string.
   */
  public String getName(String type) {
    if ("Strat".equals(type)) {
      return portfolioNameStrat.getText();
    } else if ("DAC".equals(type)) {
      return portfolioNameDAC.getText();
    } else {
      return portfolioName.getText();
    }
  }

  /**
   * Removes all dynamically added fields for entering stock information from the GUI.
   * This method is typically called after the user has saved a portfolio and wishes to
   * start fresh with a new one.
   */
  public void removeFields() {
    for (JLabel label : labels) {
      inputPanel.remove(label);
    }
    for (JPanel panel : panels) {
      inputPanel.remove(panel);
    }
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
    symbolFields.clear();
    dateFields.clear();
    quantityFields.clear();
    labels.clear();

    symbolDACFields.clear();
    dateDACFields.clear();
    percentDACFields.clear();
    labelsDAC.clear();

    symbolStratFields.clear();
    percentStratFields.clear();
    labelsStrat.clear();

    portfolioName.setText("");
    portfolioNameDAC.setText("");
    amountDAC.setText("");
    portfolioNameStrat.setText("");
    amountStrat.setText("");
    period.setText("");
    startDate.setText("");
    endDate.setText("");
    revalidate();
    repaint();
    count = 0;
  }

  public String getPeriod() {
    return period.getText();
  }

  /**
   * Prompts the user to select a portfolio from a list of options.
   *
   * @param options an array of portfolio names to be presented to the user.
   * @return the index of the selected portfolio option.
   */
  @Override
  public int getPortfolioOption(String[] options) {
    return JOptionPane.showOptionDialog(GUIView.this,
            "Please select a portfolio", "Options", JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
  }

  /**
   * Displays a message dialog to the user. This method can be used to show error messages,
   * confirmations, or other information.
   *
   * @param message the message to be displayed to the user.
   */
  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  /**
   * Retrieves the count of stocks entered by the user. This method is useful for ensuring
   * that all stock information fields have been filled out before saving a portfolio.
   *
   * @return the number of stocks entered.
   */
  @Override
  public int getCount(String type) {
    if ("DAC".equals(type)) {
      return countDAC;
    } else if ("Strat".equals(type)) {
      return countStrat;
    } else {
      return count;
    }
  }

  /**
   * Action listener class for adding new stock input fields to the UI. This inner class provides
   * functionality to dynamically add new fields for entering stock information into the portfolio.
   */
  private class AddFieldListener implements ActionListener {

    /**
     * Responds to action events by adding new input fields for stock information into the GUI.
     * This allows the user to enter multiple stocks into a portfolio.
     *
     * @param e the action event triggered by clicking the "Add Stock" button.
     */
    public void actionPerformed(ActionEvent e) {
      JTextField symbolField = new JTextField(15);
      JTextField dateField = new JTextField(15);
      JTextField quantityField = new JTextField(15);

      symbolFields.add(symbolField);
      dateFields.add(dateField);
      quantityFields.add(quantityField);

      count++;

      JLabel titleLabel = new JLabel("New Stock: " + count);

      labels.add(titleLabel);

      inputPanel.add(titleLabel);

      JPanel symbolPanel = new JPanel();
      symbolPanel.add(new JLabel("Symbol: "));
      symbolPanel.add(symbolField);
      inputPanel.add(symbolPanel);

      JPanel datePanel = new JPanel();
      datePanel.add(new JLabel("Date: "));
      datePanel.add(dateField);
      inputPanel.add(datePanel);

      JPanel quantityPanel = new JPanel();
      quantityPanel.add(new JLabel("Quantity: "));
      quantityPanel.add(quantityField);
      inputPanel.add(quantityPanel);

      panels.add(symbolPanel);
      panels.add(datePanel);
      panels.add(quantityPanel);


      revalidate();
      repaint();
    }
  }

  /**
   * Action listener class for adding new stock input fields to the UI. This inner class provides
   * functionality to dynamically add new fields for entering stock information into the portfolio.
   */
  private class AddFieldListenerDAC implements ActionListener {

    /**
     * Responds to action events by adding new input fields for stock information into the GUI.
     * This allows the user to enter multiple stocks into a portfolio.
     *
     * @param e the action event triggered by clicking the "Add Stock" button.
     */
    public void actionPerformed(ActionEvent e) {
      JTextField symbolField = new JTextField(3);
      JTextField dateField = new JTextField(4);
      JTextField percentField = new JTextField(3);

      symbolDACFields.add(symbolField);
      dateDACFields.add(dateField);
      percentDACFields.add(percentField);

      countDAC++;

      JLabel titleLabel = new JLabel("New Stock: " + countDAC);

      labelsDAC.add(titleLabel);

      inputPanelDAC.add(titleLabel);

      JPanel symbolPanel = new JPanel();
      inputPanelDAC.add(symbolPanel);
      symbolPanel.add(new JLabel("Symbol: "));
      symbolPanel.add(symbolField);

      symbolPanel.add(new JLabel("Date: "));
      symbolPanel.add(dateField);

      symbolPanel.add(new JLabel("Percent: "));
      symbolPanel.add(percentField);

      panelsDAC.add(symbolPanel);


      revalidate();
      repaint();
    }
  }

  /**
   * Action listener class for adding new stock input fields to the UI. This inner class provides
   * functionality to dynamically add new fields for entering stock information into the portfolio.
   */
  private class AddFieldListenerStrat implements ActionListener {

    /**
     * Responds to action events by adding new input fields for stock information into the GUI.
     * This allows the user to enter multiple stocks into a portfolio.
     *
     * @param e the action event triggered by clicking the "Add Stock" button.
     */
    public void actionPerformed(ActionEvent e) {
      JTextField symbolField = new JTextField(5);
      JTextField percentField = new JTextField(5);

      symbolStratFields.add(symbolField);
      percentStratFields.add(percentField);

      countStrat++;

      JLabel titleLabel = new JLabel("New Stock: " + countStrat);

      labelsStrat.add(titleLabel);

      inputPanelStrat.add(titleLabel);

      JPanel symbolPanel = new JPanel();
      inputPanelStrat.add(symbolPanel);
      symbolPanel.add(new JLabel("Symbol: "));
      symbolPanel.add(symbolField);

      symbolPanel.add(new JLabel("Percent: "));
      symbolPanel.add(percentField);

      panelsStrat.add(symbolPanel);


      revalidate();
      repaint();
    }
  }
}
