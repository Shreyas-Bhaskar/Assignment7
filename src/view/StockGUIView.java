package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/**
 * This class represents the GUI view for managing individual stocks within a portfolio.
 * It allows users to perform operations like viewing daily gain/loss, calculating period
 * gain/loss, computing moving averages, and identifying moving crossovers.
 */
public class StockGUIView  extends JFrame implements StockGUIViewInterface {
  private final JButton dailyGainLossButton;
  private final JButton periodGainLossButton;
  private final JButton movingAverageButton;
  private final JButton crossoversButton;
  private final JButton movingCrossoversButton;
  private final JTextField gainLossDate;
  private final JTextField startDateCrossovers;
  private final JTextField endDateCrossovers;
  private final JTextField movingAverageDate;
  private final JTextField movingAverageX;
  private final JTextField startDateMovingCrossovers;
  private final JTextField endDateMovingCrossovers;
  private final JTextField movingCrossoversX;
  private final JTextField movingCrossoversY;

  /**
   * Constructs a StockGUIView with a specified stock name. Initializes the GUI components
   * and layouts for the stock management interface.
   *
   * @param name The name of the stock being managed.
   */
  public StockGUIView(String name) {
    super();
    this.setTitle("Stock Name: " + name);
    this.setSize(400, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel datePanel = new JPanel();
    datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel dateFormat = new JLabel("Enter all dates in YYYY-MM-DD format");
    mainPanel.add(datePanel);
    datePanel.add(dateFormat);

    JPanel gainLossPanel = new JPanel();
    gainLossPanel.setBorder(BorderFactory.createTitledBorder("Daily Gain Loss"));
    mainPanel.add(gainLossPanel);
    gainLossPanel.add(new JLabel("Date:"));
    gainLossDate = new JTextField(10);
    gainLossPanel.add(gainLossDate);
    dailyGainLossButton = new JButton("Daily Gain Loss");
    gainLossPanel.add(dailyGainLossButton);

    JPanel crossoversPanel = new JPanel();
    crossoversPanel.setBorder(BorderFactory.createTitledBorder("Crossovers and Period Gain Loss"));
    mainPanel.add(crossoversPanel);
    JPanel crossoversInput = new JPanel();
    crossoversPanel.add(crossoversInput);
    crossoversInput.add(new JLabel("Start Date:"));
    startDateCrossovers = new JTextField(6);
    crossoversInput.add(startDateCrossovers);
    crossoversInput.add(new JLabel("End Date:"));
    endDateCrossovers = new JTextField(6);
    crossoversInput.add(endDateCrossovers);
    JPanel crossoverButtons = new JPanel();
    crossoverButtons.setLayout(new BoxLayout(crossoverButtons, BoxLayout.X_AXIS));
    crossoversPanel.add(crossoverButtons);
    periodGainLossButton = new JButton("Period Gain Loss");
    crossoverButtons.add(periodGainLossButton);
    crossoversButton = new JButton("Crossover");
    crossoverButtons.add(crossoversButton);


    JPanel movingAveragePanel = new JPanel();
    movingAveragePanel.setBorder(BorderFactory.createTitledBorder("Moving Average"));
    mainPanel.add(movingAveragePanel);
    JPanel movingAverageInput = new JPanel();
    movingAveragePanel.add(movingAverageInput);
    movingAverageInput.add(new JLabel("Date:"));
    movingAverageDate = new JTextField(8);
    movingAverageInput.add(movingAverageDate);
    movingAverageInput.add(new JLabel("No Days:"));
    movingAverageX = new JTextField(4);
    movingAverageInput.add(movingAverageX);
    movingAverageButton = new JButton("Moving Average");
    movingAveragePanel.add(movingAverageButton);

    JPanel movingCrossoversPanel = new JPanel();
    movingCrossoversPanel.setSize(new Dimension(350,150));
    movingCrossoversPanel.setBorder(BorderFactory.createTitledBorder("Moving Crossovers"));
    mainPanel.add(movingCrossoversPanel);
    JPanel movingCrossoversInput = new JPanel();
    movingCrossoversPanel.add(movingCrossoversInput);
    movingCrossoversInput.add(new JLabel("Start Date:"));
    startDateMovingCrossovers = new JTextField(6);
    movingCrossoversInput.add(startDateMovingCrossovers);
    movingCrossoversInput.add(new JLabel("End Date:"));
    endDateMovingCrossovers = new JTextField(6);
    movingCrossoversInput.add(endDateMovingCrossovers);
    JPanel movingCrossoverButtons = new JPanel();
    movingCrossoverButtons.setLayout(new BoxLayout(movingCrossoverButtons, BoxLayout.X_AXIS));
    movingCrossoversPanel.add(movingCrossoverButtons);

    movingCrossoverButtons.add(new JLabel("STMA (X):"));
    movingCrossoversX = new JTextField(3);
    movingCrossoverButtons.add(movingCrossoversX);
    movingCrossoverButtons.add(new JLabel("LTMA (Y):"));
    movingCrossoversY = new JTextField(3);
    movingCrossoverButtons.add(movingCrossoversY);

    movingCrossoversButton = new JButton("Moving Crossovers");
    movingCrossoverButtons.add(movingCrossoversButton);

    //quit button
    JButton quitButton = new JButton("Back");
    quitButton.addActionListener((ActionEvent e) -> this.dispose());
    mainPanel.add(quitButton);


    add(mainPanel);
  }

  /**
   * Makes the GUI view visible to the user. This method should be called to display the
   * stock management interface once it is ready.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Sets an ActionListener for the buttons in the GUI view. This allows for the handling
   * of user actions such as viewing daily gain/loss, calculating period gain/loss,
   * computing moving averages, and identifying moving crossovers.
   *
   * @param actionEvent The ActionListener to be set for the buttons.
   */
  public void setButtonListener(ActionListener actionEvent) {
    dailyGainLossButton.addActionListener(actionEvent);
    periodGainLossButton.addActionListener(actionEvent);
    crossoversButton.addActionListener(actionEvent);
    movingAverageButton.addActionListener(actionEvent);
    movingCrossoversButton.addActionListener(actionEvent);
  }

  /**
   * Collects and returns the text input from all JTextField components within the GUI.
   * It also clears the text fields after the information is collected. This method
   * facilitates the retrieval of user input for various stock management operations.
   *
   * @return A map containing the keys and values of all JTextField components.
   */
  public Map<String, String> texts() {
    Map<String, String> result = new HashMap<>();
    result.put("gainLossDate",gainLossDate.getText());
    gainLossDate.setText("");
    result.put("startDateCrossovers",startDateCrossovers.getText());
    startDateCrossovers.setText("");
    result.put("endDateCrossovers",endDateCrossovers.getText());
    endDateCrossovers.setText("");
    result.put("movingAverageDate",movingAverageDate.getText());
    movingAverageDate.setText("");
    result.put("movingAverageX",movingAverageX.getText());
    movingAverageX.setText("");
    result.put("startDateMovingCrossovers",startDateMovingCrossovers.getText());
    startDateMovingCrossovers.setText("");
    result.put("endDateMovingCrossovers",endDateMovingCrossovers.getText());
    endDateMovingCrossovers.setText("");
    result.put("movingCrossoversX",movingCrossoversX.getText());
    movingCrossoversX.setText("");
    result.put("movingCrossoversY",movingCrossoversY.getText());
    movingCrossoversY.setText("");
    return result;
  }

  /**
   * Displays a message to the user in a dialog box. This method is useful for showing
   * information messages, warnings, errors, and other notifications to the user.
   *
   * @param message The message to be displayed.
   */
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }
}
