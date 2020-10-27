package trackitnus.logic;

import java.nio.file.Path;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import trackitnus.commons.core.GuiSettings;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.logic.parser.exceptions.ParseException;
import trackitnus.model.Model;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.commons.Code;
import trackitnus.model.contact.Contact;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TrackIter.
     *
     * @see Model#getTrackIter()
     */
    ReadOnlyTrackIter getTrackIter();

    /**
     * Returns an unmodifiable view of the filtered list of contacts
     */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Returns an unmodifiable view of the filtered list of modules
     */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns an unmodifiable view of the filtered list of tasks
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns an unmodifiable view of the filtered list of lessons
     */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * @return the list of all lessons that will take place on and up to a week after the current day
     */
    ObservableList<Lesson> getUpcomingLessons();

    /**
     * @param date The date to query
     * @return all lessons happens on that date
     */
    ObservableList<Lesson> getDayUpcomingLessons(LocalDate date);

    /**
     * @param code The module code to query
     * @return the list of lesson for a specific module
     */
    ObservableList<Lesson> getModuleLessons(Code code);

//    ObservableList<Task> getOverdueTasks(LocalDate date);

    /**
     * @param code The module code to query
     * @return the list of task for a specific module
     */
    ObservableList<Task> getModuleTasks(Code code); // TODO: check if the list of tasks has been sorted in the
    // correct order

    /**
     * @param code The module code to query
     * @return the list of contacts for a specific module
     */
    ObservableList<Contact> getModuleContacts(Code code); // TODO: check if the list of contacts has been sorted in the
    // correct order

    /**
     * @return the list of all tasks that take place on and after the current day
     */
    ObservableList<Task> getUpcomingTasks();

    /**
     * @param date The date to query
     * @return the list of all tasks that take place on that specific day
     */
    ObservableList<Task> getDayUpcomingTasks(LocalDate date);

    /**
     * @param task The task to get index of
     * @return the index of the task in all tasks
     * @throws CommandException
     */
    Index getTaskIndex(Task task) throws CommandException;

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTrackIterFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
