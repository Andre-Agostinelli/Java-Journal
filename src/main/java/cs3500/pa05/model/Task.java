package cs3500.pa05.model;

import cs3500.pa05.model.json.TaskJson;

/**
 * Represents a Task
 */
public class Task extends JournalEntry {
  private Boolean complete;

  /**
   * Instantiates a task with a description
   *
   * @param name the name of the task
   * @param description the description of the task
   * @param day the weekday of the task
   * @param complete the boolean representing completion
   */
  public Task(String name, String description, Weekday day, Boolean complete) {
    super(name, description, day);
    this.complete = complete;
  }

  /**
   * Instantiates a task without a description
   *
   * @param name the name of the task
   * @param weekday the weekday of the day
   * @param complete if the task is complete
   */
  public Task(String name, Weekday weekday, Boolean complete) {
    super(name, weekday);
    description = "";
    this.complete = complete;
  }

  /**
   * Whether a task is complete
   *
   * @return whether the task is complete
   */
  public boolean isComplete() {
    return complete;
  }

  /**
   * Sets a task completion field to true;
   */
  public void completeTask() {
    this.complete = true;
  }

  /**
   * Transforms a Task to a TaskJson
   *
   * @return the corresponding TaskJson
   */
  public TaskJson toTaskJson() {
    return new TaskJson(name, description, weekday, complete);
  }

  /**
   * Mutates the textField to create a task from the entries
   *
   * @param newValues the values to build an entry off of
   */
  public void mutate(String[] newValues) {
    this.name = newValues[0];
    this.description = newValues[1];
    this.weekday = Weekday.valueOfString(newValues[2].toUpperCase());
    this.complete = false;
  }

  /**
   * To add a task to a java journal
   *
   * @param journal the journal to add to
   */
  @Override
  public void add(JavaJournal journal) {
    journal.addTask(this);
  }

  /**
   * To remove a task from a java journal
   *
   * @param journal the journal to remove from
   */
  @Override
  public void remove(JavaJournal journal) {
    journal.removeTask(this);
  }
}
