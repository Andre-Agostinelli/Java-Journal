package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.JEvent;
import cs3500.pa05.model.Weekday;

/**
 * The JSON representing an event
 *
 * @param name the name of the event
 * @param description the descriptor of the event
 * @param day the corresponding weekday
 * @param startTime the startTime
 * @param duration the endTime
 */
public record EventJson(@JsonProperty("name") String name,
                        @JsonProperty("description") String description,
                        @JsonProperty("day") Weekday day, @JsonProperty("start-time")
                        String startTime, @JsonProperty("duration") String duration) {

  /**
   * Transforms an EventJson to a JEvent
   *
   * @return the corresponding JEvent
   */
  public JEvent toEvent() {
    if (description.isEmpty()) {
      return new JEvent(name, day, startTime, duration);
    } else {
      return new JEvent(name, description, day, startTime, duration);
    }
  }
}
