package cs3500.pa05.model;

import cs3500.pa05.model.json.EventJson;

/**
 * To represent an event
 */
public class JEvent extends JournalEntry {
  String startTime;
  String duration;

  /**
   * Constructor with description
   *
   * @param name the name of the event
   * @param description the descriptor of the event
   * @param weekday the week day of the event
   * @param startTime the startTime of the event
   * @param duration the duration of the event
   */
  public JEvent(String name, String description, Weekday weekday,
                String startTime, String duration) {
    super(name, description, weekday);
    if (startTime.isEmpty() || duration.isEmpty()) {
      throw new IllegalArgumentException("an event must have a start time and duration");
    } else {
      this.startTime = startTime;
      this.duration = duration;
    }
  }

  /**
   * Constructor without description
   *
   * @param name the name of the event
   * @param weekday the week day of the event
   * @param startTime the startTime of the event
   * @param duration the duration of the event
   */
  public JEvent(String name, Weekday weekday,
                String startTime, String duration) {
    super(name, weekday);
    if (startTime.isEmpty() || duration.isEmpty()) {
      throw new IllegalArgumentException("an event must have a start time and duration");
    } else {
      this.startTime = startTime;
      this.duration = duration;
    }
    description = "";
  }

  /**
   * Represents a JEvent as JSON
   *
   * @return the EventJSON equivalent of this
   */
  public EventJson toEventJson() {
    return new EventJson(name, description, weekday, startTime, duration);
  }

  /**
   * Gets the start time
   *
   * @return the start time to return
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * Gets the duration time
   *
   * @return the duration time to return
   */
  public String getDuration() {
    return duration;
  }

  /**
   * Mutates the string values to a JEvent
   *
   * @param newValues the new Values to base this JEvent on
   */
  public void mutate(String[] newValues) {
    this.name = newValues[0];
    this.description = newValues[1];
    this.weekday = Weekday.valueOfString(newValues[2].toUpperCase());
    this.startTime = newValues[3];
    this.duration = newValues[4];
  }

  /**
   * To add an event to a java journal
   *
   * @param journal the journal to add to
   */
  @Override
  public void add(JavaJournal journal) {
    journal.addEvent(this);
  }

  /**
   * To remove an event from a java journal
   *
   * @param journal the journal to remove from
   */
  @Override
  public void remove(JavaJournal journal) {
    journal.removeEvent(this);
  }
}
