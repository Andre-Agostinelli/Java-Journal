package cs3500.pa05.model.json;

import java.io.File;

/**
 * Represents a java journal
 *
 * @param days the days with all the tasks and events
 * @param bujoFile the corresponding .bujo file
 * @param notesAndQuotes the notes and quotes box to save text from
 * @param weekTitle the week title
 * @param password the password for a javaJournal
 */
public record JournalJson(DayJson[] days, File bujoFile, String notesAndQuotes,
                          String weekTitle, String password) {
}
