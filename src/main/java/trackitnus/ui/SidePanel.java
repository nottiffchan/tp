package trackitnus.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.contact.Contact;
import trackitnus.model.module.Module;
import trackitnus.ui.upcoming.UpcomingPanel;

public class SidePanel extends UiPart<Region> {
    private static final String FXML = "SidePanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SidePanel.class);
    private Consumer<ArrayList<Object>> tabConsumer;
    private Logic logic;
    private final HelpWindow helpWindow;
    private final int moduleRowHeight = 32;
    private Button selectedTabButton;

    @FXML
    private Button upcomingButton;

    @FXML
    private Button contactButton;

    @FXML
    private ListView<Module> moduleListView;

    @FXML
    private Button helpButton;


    /**
     * Constructor for SidePanel
     *
     * @param tabConsumer
     */
    public SidePanel(Consumer<ArrayList<Object>> tabConsumer, Logic logic) {
        super(FXML);
        this.tabConsumer = tabConsumer;
        this.logic = logic;
        helpWindow = new HelpWindow();
        this.initialize();
    }



    /**
     * Initialises/reloads the tab buttons in the side panel.
     */
    public void initialize() {
        if (logic != null) {
            // Get modules tab buttons.
            ObservableList<Module> modules = logic.getFilteredModuleList();
            moduleListView.setPrefHeight(modules.size() * moduleRowHeight);
            moduleListView.setItems(modules);
            moduleListView.setCellFactory(listView -> new ModuleListViewCell());

            // Set Default tab as upcoming tab.
            selectedTabButton = upcomingButton;
            updateButtonDetails(upcomingButton);
        }
    }

    /**
     * Sets the view of the module tab in the side panel.
     */
    class ModuleListViewCell extends ListCell<Module> {
        @Override
        protected void updateItem(Module module, boolean empty) {
            super.updateItem(module, empty);

            if (empty || module == null) {
                setGraphic(null);
                setText(null);
            } else {
                Button updatedButton = getModuleButton(module);
                setGraphic(updatedButton);
                ArrayList<Object> upcomingValues = new ArrayList<>(Arrays.asList(Module.TYPE,
                    module));
//                tabConsumer.accept(upcomingValues);
//                updateButtonDetails(updatedButton);
            }
        }
    }

    /**
     * Relays message to MainWindow to get Upcoming panel information in TabPanel.
     */
    public void toggleUpcomingTab() {
        ArrayList<Object> upcomingValues = new ArrayList<>(Arrays.asList((Object) UpcomingPanel.TYPE));
        updateButtonDetails(upcomingButton);
        tabConsumer.accept(upcomingValues);
    }

    /**
     * Relays message to MainWindow to get Contact panel information in TabPanel.
     */
    public void toggleContactTab() {
        ArrayList<Object> contactValues = new ArrayList<>(Arrays.asList((Object) Contact.TYPE));
        updateButtonDetails(contactButton);
        tabConsumer.accept(contactValues);
    }

    /**
     * Relays message to MainWindow to get Help window information in TabPanel.
     */
    public void toggleHelpTab() {
        updateButtonDetails(helpButton);
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Updates the details of the current selected tab button.
     * @param button The new selected tab button.
     */
    public void updateButtonDetails(Button button) {
        selectedTabButton.setStyle("-fx-text-fill: white;");
        selectedTabButton = button;
        selectedTabButton.setStyle("-fx-text-fill: #68C2E8;");
    }

    /**
     * Configure the module button tab in the side panel.
     * @return moduleButton The module button.
     */
    public Button getModuleButton(Module module) {
        Button button = new Button(module.getCode().code);
        ArrayList<Object> moduleValues = new ArrayList<>(Arrays.asList(Module.TYPE, module));
        button.setOnAction(actionEvent -> {
            updateButtonDetails(button);
            tabConsumer.accept(moduleValues);
        });
        return button;
    }
}
