package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Task class
 */
class TaskTest {

  Task task;
  TaskJson taskJson;
  String[] newValues;

  @BeforeEach
  void setUp() {
    task = new Task("Eat food", "Eat breakfast", Weekday.SUNDAY, false);
    taskJson = new TaskJson("Eat food",
        "Eat breakfast", Weekday.SUNDAY, false);
    newValues = new String[] {"Drink water", "Stay hydrated", "Monday", "false"};
  }

  @Test
  void isComplete() {
    assertFalse(task.isComplete());
  }

  @Test
  void completeTask() {
    task.completeTask();
    assertTrue(task.isComplete());
  }

  @Test
  void toTaskJson() {
    assertEquals(taskJson.name(), task.toTaskJson().name());
  }

  @Test
  void mutate() {
    task.mutate(newValues);
    assertFalse(task.isComplete());
  }

  @Test
  void constructorTest() {
    Task task = new Task("test", Weekday.SATURDAY, false);
    assertEquals(task.isComplete(), false);
  }
}