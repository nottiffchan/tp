package trackitnus.ui.contact;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.model.contact.Contact;
import trackitnus.ui.UiPart;


public class ContactPanel extends UiPart<Region> {
    private static final String FXML = "/Contact/ContactPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactPanel.class);

    private ContactListPanel contactListPanel;

    private final int defaultRowHeight = 50;
    private final int paddingHeight = 10;

    @FXML
    private StackPane contactListPanelPlaceholder;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ContactPanel(Logic logic) {
        super(FXML);
        ObservableList<Contact> contacts = logic.getFilteredContactList();

        // Allow height of lists to update automatically
        contactListPanelPlaceholder.prefHeightProperty().bind(Bindings.size(contacts)
                .multiply(defaultRowHeight).add(paddingHeight));

        contactListPanel = new ContactListPanel(contacts);
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());
    }


}
