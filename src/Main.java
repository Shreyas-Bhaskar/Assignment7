import java.io.IOException;
import controller.GUIController;
import model.FlexibleManager;
import model.FlexibleManagerInterface;
import view.GUIView;

/**
 * This is the main claas of the stock portfolio manager used to call teh controller object
 * and run the program.
 */
public class Main {

  /**
   * The entry point of the application.
   * This method initializes the application's MVC (Model-View-Controller) structure:
   * Creates a model instance for managing portfolios,
   * Initializes the view for user interaction,
   * Sets up the controller to manage application logic and user inputs.
   * Then, it triggers the main menu of the application through the controller.
   *
   * @param args Command line arguments (not used).
   */
  public static void main(String[] args) throws IOException {
    FlexibleManagerInterface model = new FlexibleManager();
    //    FlexibleViewInterface view = new FlexibleView(System.out);
    //    ControllerInterface controller = new FlexibleController(
    //            model, view, new InputStreamReader(System.in));
    GUIView view = new GUIView();
    GUIController controller = new GUIController(model, view);
    controller.mainMenu();
  }
}
