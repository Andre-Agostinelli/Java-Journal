package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Weekday;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the JournalJson record
 */
class JournalJsonTest {
  TaskJson taskJson;
  List<TaskJson> jsonTasks;
  EventJson eventJson;
  List<EventJson> jsonEvents;
  DayJson day;
  DayJson[] days;
  File bujo;
  JournalJson journal;

  @BeforeEach
  void setUp() {
    taskJson = new TaskJson("Eat food",
        "Eat breakfast", Weekday.SUNDAY, false);
    jsonTasks = new ArrayList<>(List.of(taskJson));
    eventJson = new EventJson("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
    jsonEvents = new ArrayList<>(List.of(eventJson));
    day = new DayJson(Weekday.SUNDAY, jsonTasks, jsonEvents, 2, 1);
    days = new DayJson[] {day};
    bujo = Path.of("src/main/resources/mock.bujo").toFile();
    journal = new JournalJson(days, bujo, "Remember to visit grandma",
        "6/17", "cs3500");
  }

  @Test
  void days() {
    assertEquals(days, journal.days());
  }

  @Test
  void bujoFile() {
    assertEquals(bujo, journal.bujoFile());
  }

  @Test
  void notesAndQuotes() {
    assertEquals("Remember to visit grandma", journal.notesAndQuotes());
  }

  @Test
  void weekTitle() {
    assertEquals("6/17", journal.weekTitle());
  }

  @Test
  void password() {
    assertEquals("cs3500", journal.password());
  }
}