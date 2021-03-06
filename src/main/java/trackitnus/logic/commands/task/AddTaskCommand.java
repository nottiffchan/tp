package trackitnus.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static trackitnus.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static trackitnus.logic.parser.CliSyntax.PREFIX_CODE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_DATE;
import static trackitnus.logic.parser.CliSyntax.PREFIX_NAME;
import static trackitnus.logic.parser.CliSyntax.PREFIX_REMARK;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.Command;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.Model;
import trackitnus.model.task.Task;

public final class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = Task.TYPE + " " + COMMAND_WORD
        + ": Adds a task to the app.\n"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_DATE + "DATE "
        + "[" + PREFIX_CODE + "CODE] "
        + "[" + PREFIX_REMARK + "REMARK]\n"
        + "Example: " + Task.TYPE + " " + COMMAND_WORD + " "
        + PREFIX_NAME + "Assignment 1 "
        + PREFIX_DATE + "20/11/2020 "
        + PREFIX_REMARK + "Focus on Chapters 1-3";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK);
        }

        if (toAdd.getCode().isPresent() && !model.hasModule(toAdd.getCode().get())) {
            throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(Messages.MESSAGE_ADD_TASK_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddTaskCommand // instanceof handles nulls
            && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
