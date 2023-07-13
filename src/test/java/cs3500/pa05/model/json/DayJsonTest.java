package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.JEvent;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the DayJson
 */
class DayJsonTest {

  Task task;
  TaskJson taskJson;
  List<Task> tasks;
  List<TaskJson> jsonTasks;
  JEvent event;
  EventJson eventJson;
  JEvent meeting;
  List<JEvent> events;
  List<EventJson> jsonEvents;
  Day day;
  DayJson dayJson;

  @BeforeEach
  void setUp() {
    task = new Task("Eat food", "Eat breakfast", Weekday.SUNDAY, false);
    taskJson = new TaskJson("Eat food",
        "Eat breakfast", Weekday.SUNDAY, false);
    tasks = new ArrayList<>(List.of(task));
    jsonTasks = new ArrayList<>(List.of(taskJson));
    event = new JEvent("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
    eventJson = new EventJson("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
    meeting = new JEvent("Business meeting", Weekday.SUNDAY, "9:00", "1hr");
    events = new ArrayList<>(List.of(event));
    jsonEvents = new ArrayList<>(List.of(eventJson));
    day = new Day(Weekday.SUNDAY, tasks, events, 2, 1);
    dayJson = new DayJson(Weekday.SUNDAY, jsonTasks, jsonEvents, 2, 1);
  }

  @Test
  void toDay() {
    assertEquals(day.getDay(), dayJson.toDay().getDay());
  }

  @Test
  void day() {
    assertEquals(Weekday.SUNDAY, dayJson.day());
  }

  @Test
  void tasks() {
    assertEquals(jsonTasks, dayJson.tasks());
  }

  @Test
  void events() {
    assertEquals(jsonEvents, dayJson.events());
  }

  @Test
  void maxEvents() {
    assertEquals(2, dayJson.maxEvents());
  }

  @Test
  void maxTasks() {
    assertEquals(1, dayJson.maxTasks());
  }
}