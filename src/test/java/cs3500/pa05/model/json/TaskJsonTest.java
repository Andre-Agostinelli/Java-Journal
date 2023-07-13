package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the TaskJson record
 */
class TaskJsonTest {

  TaskJson task;
  Task water;

  @BeforeEach
  void setUp() {
    task = new TaskJson("Drink water", "Stay hydrated",
        Weekday.FRIDAY, false);
    water = new Task("Drink water", "Stay hydrated",
        Weekday.FRIDAY, false);
  }

  @Test
  void toTask() {
    assertEquals(water.getName(), task.toTask().getName());
  }

  @Test
  void name() {
    assertEquals("Drink water", task.name());
  }

  @Test
  void description() {
    assertEquals("Stay hydrated", task.description());
  }

  @Test
  void day() {
    assertEquals(Weekday.FRIDAY, task.day());
  }

  @Test
  void complete() {
    assertFalse(task.complete());
  }
}