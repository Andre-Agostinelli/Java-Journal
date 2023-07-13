package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.JEvent;
import cs3500.pa05.model.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventJsonTest {

  JEvent event;
  EventJson eventJson;

  @BeforeEach
  void setUp() {
    event = new JEvent("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
    eventJson = new EventJson("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
  }

  @Test
  void toEvent() {
    assertEquals(event.getStartTime(), eventJson.toEvent().getStartTime());
  }

  @Test
  void name() {
    assertEquals("Visit grandma", eventJson.name());
  }

  @Test
  void description() {
    assertEquals("Bring grandma fruit", eventJson.description());
  }

  @Test
  void day() {
    assertEquals(Weekday.SUNDAY, eventJson.day());
  }

  @Test
  void startTime() {
    assertEquals("10:00", eventJson.startTime());
  }

  @Test
  void duration() {
    assertEquals("2hrs", eventJson.duration());
  }
}