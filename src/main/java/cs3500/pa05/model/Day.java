package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import java.util.List;

/**
 * To represent a day
 */
public class Day {
  private Weekday day;
  private List<Task> tasks;
  private List<JEvent> events;
  private int maxEvents;
  private int maxTasks;

  /**
   * Instantiates a weekday
   *
   * @param day       the day of the week
   * @param tasks     the tasks for that day
   * @param events    the events for that day
   * @param maxEvents the max events for the day
   * @param maxTasks  the max tasks for the day
   */
  @JsonCreator
  public Day(Weekday day, List<Task> tasks, List<JEvent> events,
             int maxEvents, int maxTasks) {
    this.day = day;
    this.tasks = tasks;
    this.events = events;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
  }

  /**
   * Returns the tasks for a day
   *
   * @return the list of tasks
   */
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * Adds an event to the day
   *
   * @param jevent the event
   */
  public void addEvent(JEvent jevent) {
    if (events.size() < maxEvents) {
      events.add(jevent);
    } else {
      throw new IllegalStateException("Cannot exceed max number of events");
    }
  }

  /**
   * Adds a task to the day
   *
   * @param task the event
   */
  public void addTask(Task task) {
    if (tasks.size() < maxTasks) {
      tasks.add(task);
    } else {
      throw new IllegalStateException("Cannot exceed max number of tasks");
    }
  }

  /**
   * Removes an event from a day
   *
   * @param event the event
   */
  public void removeEvent(JEvent event) {
    events.remove(event);
  }

  /**
   * Removes a task from a day
   *
   * @param task the task
   */
  public void removeTask(Task task) {
    tasks.remove(task);
  }

  /**
   * Transforms a day to a DayJson
   *
   * @return the corresponding DayJson for this.day
   */
  public DayJson toDayJson() {
    List<TaskJson> taskList = new ArrayList<>();
    List<EventJson> eventList = new ArrayList<>();
    tasks.forEach((task) -> taskList.add(task.toTaskJson()));
    events.forEach((event) -> eventList.add(event.toEventJson()));
    return new DayJson(day, taskList, eventList, maxEvents, maxTasks);
  }

  /**
   * gets the events
   *
   * @return a list of events
   */
  public List<JEvent> getEvents() {
    return events;
  }

  /**
   * finds a given event based on a string
   *
   * @param event the matching string for event to find
   * @return the event (or null) corresponding to the string name
   */
  public JEvent findEvent(String event) {
    for (JEvent e : events) {
      if (e.getName().equals(event)) {
        return e;
      }
    }
    return null;
  }

  /**
   * Finds a given task
   *
   * @param task the string task to find
   * @return the corresponding task, if in the list
   */
  public Task findTask(String task) {
    for (Task t : tasks) {
      if (t.getName().equals(task)) {
        return t;
      }
    }
    return null;
  }

  /**
   * Gets the day
   *
   * @return a weekday representation of the day
   */
  public Weekday getDay() {
    return day;
  }

  /**
   * Gets the max Events for a day
   *
   * @return the integer representing max Events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Gets the max Tasks for a day
   *
   * @return the integer representing max Tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Alters the max Tasks
   *
   * @param newMax the new Max to change the maxTasks to
   */
  public void changeMaxTasks(int newMax) {
    this.maxTasks = newMax;
  }

  /**
   * Alters the max Events
   *
   * @param newMax the new Max to change the maxEvents to
   */
  public void changeMaxEvents(int newMax) {
    this.maxEvents = newMax;
  }
}
