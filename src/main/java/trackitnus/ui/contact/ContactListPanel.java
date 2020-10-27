package trackitnus.ui.contact;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.model.contact.Contact;
import trackitnus.ui.UiPart;

/**
 * Panel containing the list of contacts.
 */
public class ContactListPanel extends UiPart<Region> {
    private static final String FXML = "/Contact/ContactListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactListPanel.class);

    @FXML
    private ListView<Contact> contactListView;

    /**
     * Creates a {@code ContactListPanel} with the given {@code ObservableList}.
     */
    public ContactListPanel(ObservableList<Contact> contactList) {
        super(FXML);
        contactListView.setItems(contactList);
        contactListView.setCellFactory(listView -> new ContactListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Contact} using a {@code ContactCard}.
     */
    class ContactListViewCell extends ListCell<Contact> {
        @Override
        protected void updateItem(Contact contact, boolean empty) {
            super.updateItem(contact, empty);

            if (empty || contact == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ContactCard(contact, getIndex() + 1).getRoot());
            }
        }
    }

}
