package trackitnus.ui.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.model.module.Module;
import trackitnus.ui.UiPart;

/**
 * Panel containing the list of modules.
 */
public class ModuleListPanel extends UiPart<Region> {
    private static final String FXML = "ModuleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ModuleListPanel.class);

    @FXML
    private ListView<Module> moduleListView;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ModuleListPanel(ObservableList<Module> moduleList) {
        super(FXML);
        moduleListView.setItems(moduleList);
        moduleListView.setCellFactory(listView -> new ModuleListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Module} using a {@code ContactCard}.
     */
    class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(getModuleButton(module));
            }
        }
    }

    public Button getModuleButton(Module module) {
        Button button = new Button(module.getCode().code);
        ArrayList<Object> moduleValues = new ArrayList<>(Arrays.asList((Object) Module.TYPE, (Object) module));
//        button.setOnAction(actionEvent -> {
//            tabConsumer.accept(moduleValues);
//        });
        return button;
    }

}
