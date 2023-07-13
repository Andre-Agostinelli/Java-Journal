package cs3500.pa05.model;

import java.util.Objects;

/**
 * Represents a journal entry
 */
public abstract class JournalEntry {
  String name;
  String description;
  Weekday weekday;

  /**
   * Constructor
   *
   * @param name name of the entry
   * @param description entry description
   * @param weekday weekday of the entry
   */
  JournalEntry(String name, String description, Weekday weekday) {
    Objects.requireNonNull(weekday);
    if (!name.isEmpty()) {
      this.name = name;
      this.description = description;
    } else {
      throw new IllegalArgumentException("A journal entry must have a name");
    }
    this.weekday = weekday;
  }

  /**
   * Constructor without description
   *
   * @param name name of the entry
   * @param weekday weekday of the entry
   */
  JournalEntry(String name, Weekday weekday) {
    Objects.requireNonNull(weekday);
    if (!name.isEmpty()) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("A journal entry must have a name");
    }
    this.weekday = weekday;
  }

  /**
   * gets the Journal Entries name
   *
   * @return the string of the name
   */
  public String getName() {
    return name;
  }

  /**
   * gets the Journal Entries description
   *
   * @return the string of the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * gets the Journal Entries weekday
   *
   * @return the weekday of the string weekday
   */
  public Weekday getWeekday() {
    return weekday;
  }

  /**
   * Takes text fields and turns them into Journal Entries
   *
   * @param newValues the values to build an entry off of
   */
  public abstract void mutate(String[] newValues);

  /**
   * Checks if the entry contains a link
   *
   * @return boolean indicator
   */
  public boolean containsLink() {
    return (description.contains("www.") || description.contains("http"));
  }

  /**
   * Gets the link off a given event
   *
   * @return the string representing the link
   */
  public String getLink() {
    String link;
    try {
      link = description.substring(description.indexOf("http"), description.indexOf(" ",
          description.indexOf("http")));
    } catch (Exception ignored) {
      link = description.substring(description.indexOf("http"));
    }
    return link;
  }

  /**
   * Adds to a JavaJournal
   *
   * @param journal the journal to add to
   */
  abstract public void add(JavaJournal journal);

  /**
   * removes from a JavaJournal
   *
   * @param journal the journal to remove something fom
   */
  abstract public void remove(JavaJournal journal);

}
