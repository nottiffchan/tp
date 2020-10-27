package trackitnus.ui.module;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.contact.Contact;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;
import trackitnus.ui.UiPart;
import trackitnus.ui.contact.ContactListPanel;
import trackitnus.ui.lesson.LessonListPanel;
import trackitnus.ui.task.TaskListPanel;

/**
 * Panel containing the list of modules.
 */
public class ModulePanel extends UiPart<Region> {
    private static final String FXML = "/Module/ModulePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModulePanel.class);

    private LessonListPanel lessonListPanel;
    private TaskListPanel taskListPanel;
    private ContactListPanel contactListPanel;

    private final int defaultRowHeight = 50;
    private final int lessonRowHeight = 40;
    private final int paddingHeight = 10;

    @FXML
    private HBox moduleHeader;
    @FXML
    private Label header;
    @FXML
    private StackPane lessonListPanelPlaceholder;
    @FXML
    private StackPane taskListPanelPlaceholder;
    @FXML
    private StackPane contactListPanelPlaceholder;

    /**
     * Creates a {@code ModuleListPanel} with the given {@code ObservableList}.
     */
    public ModulePanel(Module module, Logic logic) {
        super(FXML);
        header.setWrapText(true);
        header.setMaxWidth(500);
        header.setText(module.getCode().code + " " + module.getName().fullName);

        ObservableList<Lesson> lessons = logic.getModuleLessons(module.getCode());
        ObservableList<Task> tasks = logic.getModuleTasks(module.getCode());
        ObservableList<Contact> contacts = logic.getModuleContacts(module.getCode());

        // Allow height of lists to update automatically
        lessonListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(lessons)
                .multiply(lessonRowHeight).add(paddingHeight));
        taskListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(tasks)
                .multiply(defaultRowHeight).add(paddingHeight));
        contactListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(contacts)
                .multiply(defaultRowHeight).add(paddingHeight));

        lessonListPanel = new LessonListPanel(lessons);
        lessonListPanelPlaceholder.getChildren().add(lessonListPanel.getRoot());

        taskListPanel = new TaskListPanel(tasks);
        taskListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        contactListPanel = new ContactListPanel(contacts);
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }


}
