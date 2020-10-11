package seedu.address.logic.commands.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.commons.Code;
import seedu.address.model.lesson.Lesson;

public class ListLessonCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all lessons";
    // TODO: edit these messages
    public static final String MESSAGE_USAGE = "L " + COMMAND_WORD + ": sample";

    private final Optional<Code> codeOptional;

    /**
     * Creates a ListLessonCommand to list all {@code Lesson}s of the specified module
     * @param codeOptional
     */
    public ListLessonCommand(Optional<Code> codeOptional) {
        this.codeOptional = codeOptional;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Lesson> predicate;
        if (codeOptional.isEmpty()) {
            predicate = PREDICATE_SHOW_ALL_LESSONS;
        } else {
            predicate = lesson -> lesson.getCode().equals(codeOptional.get());
        }
        model.updateFilteredLessonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
