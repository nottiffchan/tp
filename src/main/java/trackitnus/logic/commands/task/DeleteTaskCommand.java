package trackitnus.logic.commands.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import trackitnus.commons.core.Messages;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.task.Task;

public final class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = Task.TYPE + " " + COMMAND_WORD
        + ": Deletes the task identified by the index number currently displayed on the screen.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + Task.TYPE + " " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Creates a DeleteTaskCommand to delete the specified {@code Task}
     *
     * @param targetIndex index of the task to delete in the current FilteredTaskList
     */
    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(Messages.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteTaskCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
