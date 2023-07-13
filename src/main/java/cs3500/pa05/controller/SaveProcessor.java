package cs3500.pa05.controller;

import cs3500.pa05.model.JavaJournal;
import cs3500.pa05.model.JournalEntry;
import cs3500.pa05.view.PopupView;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Helps save fields from miniViewer
 */
public class SaveProcessor implements EventHandler {
  JournalEntry entry;
  TextField[] newValues;
  PopupView popupView;
  Stage stage;
  Label label;
  JavaJournalControllerImpl controller;
  GridPane pane;
  JavaJournal journal;

  /**
   * Processes a new save
   *
   * @param entry the entry to save
   * @param newValues the new Values of user input
   * @param popupView the popupView
   * @param stage the stage for popupView
   * @param label the label to display
   * @param controller the current controller
   * @param pane the current gridPane
   */
  public SaveProcessor(JournalEntry entry, TextField[] newValues, PopupView popupView, Stage stage,
                       Label label, JavaJournalControllerImpl controller, GridPane pane,
                       JavaJournal journal) {
    this.entry = entry;
    this.newValues = newValues;
    this.popupView = popupView;
    this.stage = stage;
    this.label = label;
    this.controller = controller;
    this.pane = pane;
    this.journal = journal;
  }

  /**
   * Helps display the popUpView and handles edits on miniViewer
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    List<String> values = new ArrayList<>();
    for (TextField field : newValues) {
      values.add(field.getText());
    }
    try {
      entry.remove(journal);
      entry.mutate(values.toArray(new String[0]));
      entry.add(journal);
      label.setText(entry.getName());
      label.setOnMouseClicked(event2 -> {
        controller.miniViewer(label, entry);
      });
      pane.add(pane.getChildren().remove(pane.getChildren().indexOf(label)),
          entry.getWeekday().ordinal(),
          JavaJournalControllerImpl.findFirstEmptyRow(pane, entry.getWeekday().ordinal()));
      stage.close();
    } catch (Exception e2) {
      popupView.invalidInputAlert("Invalid input",
          "Please ensure all inputs are valid, descriptions are optional");
    }
  }
}
