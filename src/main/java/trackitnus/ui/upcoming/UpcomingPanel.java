package trackitnus.ui.upcoming;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import trackitnus.commons.core.LogsCenter;
import trackitnus.logic.Logic;
import trackitnus.ui.UiPart;

public class UpcomingPanel extends UiPart<Region> {
    public static final String TYPE = "U";
    private static final String FXML = "/Upcoming/UpcomingPanel.fxml";

    private final Logic logic;
    private final Logger logger = LogsCenter.getLogger(UpcomingPanel.class);

    private LocalDate today = LocalDate.now();
    private final ObservableList<Object> calendarDates = FXCollections.observableArrayList();

    @FXML
    private ListView<Object> calendarView;

    /**
     * Constructor for Upcoming Panel
     */
    public UpcomingPanel(Logic logic) {
        super(FXML);
        this.logic = logic;

        getDatesForTheWeek(today);
        calendarView.setItems(calendarDates);
        calendarView.setCellFactory(listView -> new DateListViewCell());

    }

    private void getDatesForTheWeek(LocalDate today) {
        List<LocalDate> list = today.datesUntil(today.plusDays(7)).collect(Collectors.toList());

        calendarDates.add(new CalendarSection("Overdue"));

        for (LocalDate date : list) {
            calendarDates.add(new Day(date));
        }

        calendarDates.add(new CalendarSection("Future"));
    }

    class DateListViewCell extends ListCell<Object> {
        @Override
        protected void updateItem(Object object, boolean empty) {
            super.updateItem(object, empty);

            if (empty || object == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (object instanceof Day) {
                    Day day = (Day) object;
                    setGraphic(new DayCard(day, logic.getDayUpcomingTasks(day.getDate()),
                            logic.getDayUpcomingLessons(day.getDate()), logic).getRoot());
                } else {
                    assert(object instanceof CalendarSection);
                    CalendarSection calendarSection = (CalendarSection) object;

                    //TODO: Replace with appropriate filtered TaskList for Overdue and Future sections
                    setGraphic(new CalendarSectionCard(calendarSection, logic.getFilteredTaskList(), logic).getRoot());
                }
            }
        }
    }
}
