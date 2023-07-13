package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.JEvent;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;
import java.util.ArrayList;
import java.util.List;

/**
 * The JSON representing a day
 *
 * @param day the weekday
 * @param tasks the list of tasks
 * @param events the list of events
 * @param maxEvents the max number of events
 * @param maxTasks the max number of tasks
 *
 */
public record DayJson(@JsonProperty("day") Weekday day, @JsonProperty("tasks") List<TaskJson> tasks,
                      @JsonProperty("events") List<EventJson> events, @JsonProperty("maxEvents")
                      int maxEvents, @JsonProperty("maxTasks") int maxTasks) {

  /**
   * Transforms a dayJson to a day
   *
   * @return the corresponding day
   */
  public Day toDay() {
    List<Task> taskList = new ArrayList<>();
    List<JEvent> eventlist = new ArrayList<>();
    tasks.forEach((task) -> taskList.add(task.toTask()));
    events.forEach((event) -> eventlist.add(event.toEvent()));
    return new Day(day, taskList,
        eventlist, maxEvents, maxTasks);
  }
}
