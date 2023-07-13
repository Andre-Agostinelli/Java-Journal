package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.JsonUtils;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a javaJournal
 */
public class JavaJournal {
  private Day[] days = new Day[7];
  private File bujoFile;
  private String notesAndQuotes;
  private String weekTitle;
  private String password = "cs3500";

  /**
   * Instantiates a JavaJournal
   *
   * @param days the days to assign
   * @param file the file corresponding to this Journal
   */
  public JavaJournal(Day[] days, File file) {
    this.days = days;
    bujoFile = file;
  }

  /**
   * Instantiates a JavaJournal based on openFile
   *
   * @param file the file to read in from
   */
  public JavaJournal(File file) {
    this.bujoFile = file;
    initializeDays();
  }

  /**
   * Default initialization
   */
  public JavaJournal() {
    for (int i = 0; i < 7; i++) {
      days[i] = new Day(Weekday.values()[i], new ArrayList<>(),
          new ArrayList<>(), 6, 6);
    }
    //bujoFile = new File("testFile2.bujo");
  }



  /**
   * Finds the percentage of tasks completed for a given week
   *
   * @return the percentage of tasks completed for a given week
   */
  public double percentComplete() {
    double totalTasks = getTasks().size();
    double tasksCompleted = 0;
    for (Day day : days) {
      List<Task> tasks = day.getTasks();
      for (Task task : tasks) {
        if (task.isComplete()) {
          tasksCompleted += 1;
        }
      }
    }
    if (totalTasks == 0) {
      return 0;
    } else {
      return tasksCompleted / totalTasks * 100;
    }
  }

  /**
   * Gets the total list of Tasks
   *
   * @return the appended list of all tasks for a given week
   */
  public List<Task> getTasks() {
    List<Task> outputTasks = new ArrayList<>();
    for (Day day : days) {
      List<Task> tasks = day.getTasks();
      outputTasks.addAll(tasks);
    }
    return outputTasks;
  }

  /**
   * Total events of this journal
   *
   * @return total events of this journal
   */
  public List<JEvent> getEvents() {
    List<JEvent> jevents = new ArrayList<>();
    for (Day day : days) {
      jevents.addAll(day.getEvents());
    }
    return jevents;
  }

  /**
   * Adds an event to the days list of events
   *
   * @param e the JEvent to add
   */
  public void addEvent(JEvent e) {
    for (Day day : days) {
      if (day.getDay().equals(e.getWeekday())) {
        day.addEvent(e);
        break;
      }
    }
  }

  /**
   * Adds a task to the days list of tasks
   *
   * @param t the Task to add
   */
  public void addTask(Task t) {
    for (Day day : days) {
      if (day.getDay().equals(t.getWeekday())) {
        day.addTask(t);
        break;
      }
    }
  }

  /**
   * Removes an event from the journal
   *
   * @param e the event
   */
  public void removeEvent(JEvent e) {
    for (Day day : days) {
      day.removeEvent(day.findEvent(e.getName()));
    }
  }

  /**
   * Removes a task from the journal
   *
   * @param t the task
   */
  public void removeTask(Task t) {
    for (Day day : days) {
      day.removeTask(day.findTask(t.getName()));
    }
  }


  /**
   * Initializes the days for a new JavaJournal based on file reading in
   */
  public void initializeDays() {
    // should read the bujo file data and initialize the data from
    // the bujo file in the fields
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode journalFile = mapper.readTree(bujoFile);
      JournalJson journalJson = mapper.convertValue(journalFile, JournalJson.class);

      Day[] daysFromFile = new Day[7];
      for (int i = 0; i < 7; i++) {
        DayJson dayJson = journalJson.days()[i];
        daysFromFile[i] = dayJson.toDay();
      }
      this.days = daysFromFile;
      this.notesAndQuotes = journalJson.notesAndQuotes();
      this.weekTitle = journalJson.weekTitle();
      this.password = journalJson.password();
    } catch (Exception e) {
      System.err.println("Error: Couldn't initialize days");
    }
  }

  /**
   * gets the days from a journal
   *
   * @return the array of days
   */
  public Day[] getDays() {
    return days;
  }

  /**
   * Allows user to change the password
   *
   * @param password the string to change the password to
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Allows access to password in tests
   *
   * @return the password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Writes this javaJournal to a file
   *
   * @param file the file to write to
   */
  public void writeToFile(File file) {
    this.bujoFile = file;
    JsonNode journalNode = JsonUtils.serializeRecord(this.toJournalJson());
    try {
      FileWriter writer = new FileWriter(bujoFile);
      writer.write(journalNode.toPrettyString());
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }


  /**
   * Converts a journal to a JournalJson
   *
   * @return the corresponding JournalJson based on this.journal
   */
  public JournalJson toJournalJson() {
    DayJson[] dayJsons = new DayJson[7];
    for (int i = 0; i < 7; i++) {
      Day day = days[i];
      DayJson cur = day.toDayJson();
      dayJsons[i] = cur;
    }
    return new JournalJson(dayJsons, this.bujoFile, this.notesAndQuotes, this.weekTitle,
        this.password);
  }

  /**
   * Allows to write in note and quotes box
   *
   * @param notesAndQuotes the string written by the user
   */
  public void addNotes(String notesAndQuotes) {
    this.notesAndQuotes = notesAndQuotes;
  }

  /**
   * Changes the weekTitle
   *
   * @param weekTitle the string to change the week title too
   */
  public void setWeekTitle(String weekTitle) {
    this.weekTitle = weekTitle;
  }

  /**
   * Determines if the Journal has a file already
   *
   * @return a boolean indicator
   */
  public boolean isJournalFileEmpty() {
    return this.bujoFile == null;
  }

  /**
   * Sets the max tasks for all days
   *
   * @param newMax the new max task number to set to
   */
  public void setMaxTasks(int newMax) {
    for (Day day : days) {
      day.changeMaxTasks(newMax);
    }
  }

  /**
   * Gets the max tasks for all days
   *
   * @return the max task number
   */
  public int getMaxTasks() {
    return this.days[0].getMaxTasks();
  }

  /**
   * Gets the max event for all days
   *
   * @return the max event number
   */
  public int getMaxEvents() {
    return this.days[0].getMaxEvents();
  }

  /**
   * Sets the max events for all days
   *
   * @param newMax the new max event number to set to
   */
  public void setMaxEvent(int newMax) {
    for (Day day : days) {
      day.changeMaxEvents(newMax);
    }
  }


  /**
   * Retrieves notes and quotes from the Journal
   *
   * @return the String in the notesAndQuotes box
   */
  public String getNotesAndQuotes() {
    return notesAndQuotes;
  }

  /**
   * Retrieves week tittle from the Journal
   *
   * @return the String in the week tittle box
   */
  public String getWeekTitle() {
    return weekTitle;
  }

  /**
   * Checks if the password is correct
   *
   * @param password the password to check against
   * @return a boolean flag indicating password correctness
   */
  public boolean correctPassword(String password) {
    return password.equals(this.password);
  }

  /**
   * Sets a file path
   *
   * @param file the file path to set this journal too
   */
  public void setFile(File file) {
    this.bujoFile = file;
  }

  /**
   * Used for testing to get a file
   *
   * @return the corresponding file being pointed at
   */
  public File getFile() {
    return this.bujoFile;
  }
}
