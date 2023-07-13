package cs3500.pa05;

import cs3500.pa05.controller.JavaJournalController;
import cs3500.pa05.controller.JavaJournalControllerImpl;
import cs3500.pa05.model.JavaJournal;
import cs3500.pa05.view.JavaJournalView;
import cs3500.pa05.view.JavaJournalViewImpl;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Driver to run the application
 */
public class Driver extends Application {

  /**
   * Starts the GUI for the Java Journal
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    // instantiate a simple JavaJournal cs3500.view
    JavaJournal javaJournal = new JavaJournal();
    JavaJournalController journalController = new JavaJournalControllerImpl(javaJournal, stage);
    JavaJournalView javaJournalView = new JavaJournalViewImpl(journalController);
    stage.setScene(journalController.showSplashScreen());
    stage.show();
    Stage journal = new Stage();
    TextField passwordField = new TextField();
    journalController.closeSplashScreen(stage, journal, javaJournalView.load(),
        journalController.showPasswordScreen(passwordField), passwordField);
  }

  /**
   * Launches the GUI program
   *
   * @param args null
   */
  public static void main(String[] args) {
    launch();
  }
}