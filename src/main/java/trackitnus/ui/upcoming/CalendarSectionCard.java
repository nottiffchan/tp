package trackitnus.ui.upcoming;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.logic.Logic;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;
import trackitnus.ui.task.TaskCard;

public class CalendarSectionCard extends UiPart<Region> {
    private static final String FXML = "/Upcoming/CalendarSectionCard.fxml";

    public final CalendarSection calendarSection;
    private Logic logic;
    private String title;
    private final int taskRowHeight = 45;

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private Label sectionTitle;

    /**
     * Constructor for a section in the calendar
     * @param calendarSection
     * @param taskList
     * @param logic
     */
    public CalendarSectionCard(CalendarSection calendarSection, ObservableList<Task> taskList, Logic logic) {
        super(FXML);
        this.calendarSection = calendarSection;
        this.logic = logic;
        sectionTitle.setText(calendarSection.getTitle());

        taskListView.prefHeightProperty().bind(Bindings.size(taskList).multiply(taskRowHeight).add(10));
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalendarSectionCard)) {
            return false;
        }

        // state check
        CalendarSectionCard card = (CalendarSectionCard) other;
        return title.equals(card.title);
    }

    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    setGraphic(new TaskCard(task, logic.getTaskIndex(task).getOneBased()).getRoot());
                } catch (CommandException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
