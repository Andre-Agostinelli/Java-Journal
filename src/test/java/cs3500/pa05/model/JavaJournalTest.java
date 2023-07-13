package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.controller.JavaJournalControllerImpl;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.JavaJournal;
import cs3500.pa05.model.JEvent;
import cs3500.pa05.model.JournalEntry;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Weekday;



import java.util.ArrayList;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class JavaJournalTest {

  Task task1;
  Task task2;
  TaskJson taskJson1;
  TaskJson taskJson2;

  List<Task> tasks;
  List<TaskJson> jsonTasks;

  JEvent event1;
  JEvent event2;
  EventJson eventJson1;
  EventJson eventJson2;


  List<JEvent> events;
  List<EventJson> jsonEvents;

  Day day1;
  Day day2;

  DayJson dayJson1;
  DayJson dayJson2;


  JavaJournal journal1;
  JavaJournal journal2;

  File file1;

  Day[] initDays;
  Day sunday;
  Day monday;
  Day tuesday;
  Day wednesday;
  Day thursday;
  Day friday;
  Day saturday;
  Task initTask1;
  Task initTask2;
  List<Task> initTasks;
  JEvent initEvent1;
  List<JEvent> initEvents;
  JavaJournal initjournal1;
  JavaJournal journal3;

  JEvent eventNew;


  @BeforeEach
  void setup() {
    task1 = new Task("Eat food", "Eat breakfast", Weekday.SUNDAY, false);
    task2 = new Task("Drink water", "Drink H2O", Weekday.SUNDAY, false);
    taskJson1 = new TaskJson("Eat food",
        "Eat breakfast", Weekday.SUNDAY, false);
    taskJson2 = new TaskJson("Drink water",
        "Drink H2O", Weekday.SUNDAY, false);

    tasks = new ArrayList<>(List.of(task1, task2));
    jsonTasks = new ArrayList<>(List.of(taskJson1, taskJson2));

    event1 = new JEvent("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
    event2 = new JEvent("Visit grandpa", "Bring grandpa cigars",
        Weekday.SUNDAY, "12:00", "1hrs");
    eventJson1 = new EventJson("Visit grandma", "Bring grandma fruit",
        Weekday.SUNDAY, "10:00", "2hrs");
    eventJson2 = new EventJson("Visit grandpa", "Bring grandpa cigars",
        Weekday.SUNDAY, "12:00", "1hrs");

    events = new ArrayList<>(List.of(event1, event2));
    jsonEvents = new ArrayList<>(List.of(eventJson1, eventJson2));

    day1 = new Day(Weekday.SUNDAY, tasks, events, 2, 1);
    dayJson1 = new DayJson(Weekday.SUNDAY, jsonTasks, jsonEvents, 2, 1);

    day2 = new Day(Weekday.SUNDAY, tasks, events, 2, 2);
    dayJson2 = new DayJson(Weekday.SUNDAY, jsonTasks, jsonEvents, 2, 2);

    journal1 = new JavaJournal();
    journal2 = new JavaJournal();
    initjournal1 = new JavaJournal();

    file1 = new File("src/main/testfiles/initDaysTest");


    initTask1 = new Task("testerTask", "descriptor1", Weekday.MONDAY, false);
    initTask2 = new Task("testerTask2", "descriptor", Weekday.MONDAY, false);
    initEvent1 = new JEvent("testerEvent", "descriptor", Weekday.MONDAY, "2:00pm", "1hr");
    List<Task> initTasks = List.of(initTask1, initTask2);
    List<JEvent> initEvents = List.of(initEvent1);

    Day sunday = new Day(Weekday.SUNDAY, new ArrayList<Task>(), new ArrayList<JEvent>(), 6, 6);
    Day monday = new Day(Weekday.MONDAY, initTasks, initEvents, 6, 6);
    Day tuesday = new Day(Weekday.TUESDAY, new ArrayList<Task>(), new ArrayList<JEvent>(), 6, 6);
    Day wednesday = new Day(Weekday.WEDNESDAY, new ArrayList<Task>(), new ArrayList<JEvent>(), 6, 6);
    Day thursday = new Day(Weekday.THURSDAY, new ArrayList<Task>(), new ArrayList<JEvent>(), 6, 6);
    Day friday = new Day(Weekday.FRIDAY, new ArrayList<Task>(), new ArrayList<JEvent>(), 6, 6);
    Day saturday = new Day(Weekday.SATURDAY, new ArrayList<Task>(), new ArrayList<JEvent>(), 6, 6);
    initDays = new Day[7];
    initDays[0] = (sunday);
    initDays[1] = (monday);
    initDays[2] = (tuesday);
    initDays[3] = (wednesday);
    initDays[4] = (thursday);
    initDays[5] = (friday);
    initDays[6] = (saturday);
    journal3 = new JavaJournal(initDays, file1);

    eventNew = new JEvent("testerEvent", "descriptor", Weekday.SATURDAY, "2:00pm", "1hr");
  }

  @Test
  void percentComplete() {
    //should be 0 for a new journal
    assertEquals(journal1.percentComplete(), 0);
    journal1.addTask(task1);
    assertEquals(journal1.percentComplete(), 0);
    task1.completeTask();
    assertEquals(journal1.percentComplete(), 100);
    journal2.addTask(task1);
    journal2.addTask(task2);
    assertEquals(journal2.percentComplete(), 50);
  }

  @Test
  void getTasks() {
    journal1.addTask(task1);
    journal1.addTask(task2);
    assertEquals(List.of(task1, task2), journal1.getTasks());
    assertEquals(new ArrayList<>(), journal2.getTasks());
  }

  @Test
  void getEvents() {
    journal1.addEvent(event1);
    journal1.addEvent(event2);
    assertEquals(List.of(event1, event2), journal1.getEvents());
    assertEquals(List.of(event2, event1).equals(journal1.getEvents()), false);
    assertEquals(List.of(), journal2.getEvents());
  }

  @Test
  void addEvent() {
    assertEquals(journal1.getEvents(), new ArrayList<>());
    journal1.addEvent(event1);
    assertEquals(journal1.getEvents(), List.of(event1));
    journal2.addEvent(eventNew);
    assertEquals(journal2.getEvents(), List.of(eventNew));
  }

  @Test
  void addTask() {
    assertEquals(journal2.getTasks(), new ArrayList<>());
    journal2.addTask(task2);
    assertEquals(journal2.getEvents(), List.of());
    assertEquals(journal2.getTasks(), List.of(task2));

    Task newTask = new Task("andre", "none", Weekday.SATURDAY, false);
    journal1.addTask(newTask);
    assertEquals(journal1.getTasks(), List.of(newTask));
  }

  @Test
  void removeTask() {
    Task newTask = new Task("andre", "none", Weekday.SATURDAY, false);
    assertEquals(journal1.getTasks().size(), 0);
    journal1.addTask(newTask);
    assertEquals(journal1.getTasks().size(), 1);
    journal1.removeTask(newTask);
    assertEquals(journal1.getTasks().size(), 0);
  }

  @Test
  void removeEvent() {
    JEvent newTask = new JEvent ("andre", "none", Weekday.SATURDAY, "2:00", "1:00");
    assertEquals(journal1.getEvents().size(), 0);
    journal1.addEvent(event1);
    assertEquals(journal1.getEvents().size(), 1);
    journal1.removeEvent(event1);
    assertEquals(journal1.getEvents().size(), 0);
  }


  @Test
  void initializeDays() {
    initjournal1.setFile(file1);
    initjournal1.initializeDays();

    JavaJournal journal2 = new JavaJournal(initDays, file1);

    List<Task> tasks1 = initjournal1.getTasks();
    List<JEvent> events1 = initjournal1.getEvents();
    Task resultTask11 = tasks1.get(0);
    Task resultTask12 = tasks1.get(1);
    JEvent resultEvent11 = events1.get(0);

    List<Task> tasks2 = journal2.getTasks();
    List<JEvent> events2 = journal2.getEvents();
    Task resultTask21 = tasks2.get(0);
    Task resultTask22 = tasks2.get(1);
    JEvent resultEvent21 = events2.get(0);

    assertEquals(resultTask11.getName(), resultTask21.getName());
    assertEquals(resultTask11.getDescription(), resultTask21.getDescription());
    assertEquals(resultTask11.getWeekday(), resultTask21.getWeekday());

    assertEquals(resultTask12.getName(), resultTask22.getName());
    assertEquals(resultTask12.getDescription(), resultTask22.getDescription());
    assertEquals(resultTask12.getWeekday(), resultTask22.getWeekday());

    assertEquals(resultEvent11.getStartTime(), resultEvent21.getStartTime());
    assertEquals(resultEvent11.getDuration(), resultEvent21.getDuration());
    assertEquals(resultEvent11.getName(), resultEvent21.getName());
    assertEquals(resultEvent11.getDescription(), resultEvent21.getDescription());
    assertEquals(resultEvent11.getWeekday(), resultEvent21.getWeekday());

    assertEquals(initjournal1.getFile(), journal2.getFile());
  }

  @Test
  void getDays() {
    initjournal1.setFile(file1);
    initjournal1.initializeDays();
    Day[] day1 = initjournal1.getDays();
    Day[] day2 = journal3.getDays();
    for (int i = 0; i < day1.length; i++) {
      String day11 = day1[i].toDayJson().toString();
      String day22 = day1[i].toDayJson().toString();
      assertEquals(day11, day22);
    }
  }

  @Test
  void setPassword() {
    assertEquals(journal1.getPassword(), journal2.getPassword());
    journal1.setPassword("Andre1");
    assertEquals(journal1.getPassword().equals(journal2.getPassword()), false);
    assertEquals(journal1.getPassword().equals("Andre1"), true);
  }

  @Test
  void writeToFile() {

    initjournal1.setFile(file1);
    initjournal1.initializeDays();
    File file2 = new File("src/main/testfiles/andreTest");
    initjournal1.writeToFile(file2);
    JavaJournalControllerImpl newController = new JavaJournalControllerImpl();
    JavaJournal newJournal = newController.openFile(file2);


    Day[] day1 = initjournal1.getDays();
    Day[] day2 = newJournal.getDays();
    for (int i = 0; i < day1.length; i++) {
      String day11 = day1[i].toDayJson().toString();
      String day22 = day1[i].toDayJson().toString();
      assertEquals(day11, day22);
    }
    assertEquals(initjournal1.getNotesAndQuotes(), newJournal.getNotesAndQuotes());
    assertEquals(initjournal1.getWeekTitle(), newJournal.getWeekTitle());
    assertEquals(initjournal1.getFile(), newJournal.getFile());
    assertEquals(initjournal1.getPassword(), newJournal.getPassword());
  }

  @Test
  void addNotes() {
    assertEquals(journal1.getNotesAndQuotes(), null);
    journal1.addNotes("myNameJeff");
    assertEquals(journal1.getNotesAndQuotes(), "myNameJeff");
  }

  @Test
  void setWeekTitle() {
    assertEquals(journal1.getWeekTitle(), null);
    journal1.setWeekTitle("myNameJeff");
    assertEquals(journal1.getWeekTitle(), "myNameJeff");
  }

  @Test
  void isJournalFileEmpty() {
    assertEquals(journal1.isJournalFileEmpty(), true);
    journal1.setFile(new File("myNameJeff"));
    assertEquals(journal1.isJournalFileEmpty(), false);
  }

  @Test
  void setMaxTasks() {
    assertEquals(journal1.getMaxTasks(), 6);
    journal1.setMaxTasks(10);
    assertEquals(journal1.getMaxTasks(), 10);
  }

  @Test
  void setMaxEvent() {
    assertEquals(journal1.getMaxEvents(), 6);
    journal1.setMaxEvent(10);
    assertEquals(journal1.getMaxEvents(), 10);
  }

  @Test
  void correctPassword() {
    assertEquals(journal1.correctPassword("andre"), false);
    assertEquals(journal1.correctPassword("cs3500"), true);
    journal1.setPassword("andre");
    assertEquals(journal1.correctPassword("andre"), true);
    assertEquals(journal1.correctPassword("cs3500"), false);
  }
}