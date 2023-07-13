package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the JsonUtils class
 */
class JsonUtilsTest {

  TaskJson task;

  /**
   * To initialize data for testing
   */
  @BeforeEach
  public void setup() {
    task = new TaskJson("Do homework", "Finish philosophy homework",
        Weekday.TUESDAY, false);
  }

  /**
   * To test the serializeRecord method in JsonUtils
   */
  @Test
  void serializeRecord() {
    JsonNode taskNode = new JsonUtils().serializeRecord(task);
    assertEquals("Do homework", taskNode.get("name").asText());
    assertEquals("Finish philosophy homework", taskNode.get("description").asText());
  }
}