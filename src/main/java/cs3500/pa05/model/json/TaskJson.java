package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;

/**
 * The JSON representing a Task
 *
 * @param name the name of the task
 * @param description the descriptor of the task
 * @param day the day of the task
 * @param complete is the task complete
 *
 */
public record TaskJson(@JsonProperty("name") String name,
                       @JsonProperty("description") String description,
                       @JsonProperty("day") Weekday day, @JsonProperty("complete")
                       boolean complete) {

  /**
   * Transforms a TaskJson to a Task
   *
   * @return the corresponding Task
   *
   */
  public Task toTask() {
    if (description.isEmpty()) {
      return new Task(name, day, complete);
    } else {
      return new Task(name, description, day, complete);
    }
  }
}
