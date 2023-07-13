package cs3500.pa05.view;

import cs3500.pa05.controller.JavaJournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The view implementation representing a JavaJournal
 */
public class JavaJournalViewImpl implements JavaJournalView {

  FXMLLoader loader;

  /**
   * Instantiates a JavaJournalViewImpl
   *
   * @param controller the control to base the view on
   */
  public JavaJournalViewImpl(JavaJournalController controller) {
    // initialization and location setting omitted for brevity
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("journal.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Whack-a-Mole GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }


  /**
   * Sets the scene given a String (board)
   *
   * @param scene the string to set a scene too
   */
  public void setScene(String scene) {
    this.loader.setLocation(getClass().getClassLoader().getResource(scene));
  }

  /**
   * Sets a new controller for the view
   *
   * @param controller the controller to assign to the view
   */
  public void setController(JavaJournalController controller) {
    this.loader.setController(controller);
  }
}