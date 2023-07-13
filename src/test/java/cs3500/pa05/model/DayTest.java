package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Day class
 */
class DayTest {

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
    day = new Day(Weekday.SUNDAY, tasks, events, 2, 3);
    dayJson = new DayJson(Weekday.SUNDAY, jsonTasks, jsonEvents, 2, 3);
  }

  @Test
  void getTasks() {
    assertEquals(tasks, day.getTasks());
  }

  @Test
  void addEvent() {
    day.addEvent(meeting);
    assertEquals(day.getEvents().get(1), meeting);
  }

  @Test
  void addTask() {
    day.addTask(task);
    assertEquals(day.getTasks().get(1), task);
  }

  @Test
  void toDayJson() {
    assertEquals(dayJson, day.toDayJson());
  }

  @Test
  void getEvents() {
    assertEquals(events, day.getEvents());
  }

  @Test
  void findEvent() {
    assertEquals(event, day.findEvent("Visit grandma"));
  }

  @Test
  void findTask() {
    assertEquals(task, day.findTask("Eat food"));
  }

  @Test
  void getDay() {
    assertEquals(Weekday.SUNDAY, day.getDay());
  }

  @Test
  void getMaxEvents() {
    assertEquals(2, day.getMaxEvents());
  }

  @Test
  void getMaxTasks() {
    assertEquals(3, day.getMaxTasks());
  }

  @Test
  void changeMaxTasks() {
    day.changeMaxTasks(3);
    assertEquals(3, day.getMaxTasks());
  }

  @Test
  void changeMaxEvents() {
    day.changeMaxEvents(3);
    assertEquals(3, day.getMaxEvents());
  }

  @Test
  void exceptions() {
    Day day = new Day(Weekday.SATURDAY, new ArrayList<Task>(), new ArrayList<JEvent>(),
        1, 1);
    day.addEvent(event);
    day.addTask(task);
    assertThrows(IllegalStateException.class,
        () -> {
          day.addEvent(event);
        });
    assertThrows(IllegalStateException.class,
        () -> {
          day.addTask(task);
        });
  }
}