package trackitnus.ui.upcoming;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Day in the Calendar view.
 */
public class Day {
    private LocalDate date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM EEEE");

    /**
     * Every field must be present and not null.
     *
     * @param date
     */
    public Day(LocalDate date) {
        this.date = date;
    }

    public String getSectionHeader() {
        return date.format(formatter);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Day)) {
            return false;
        }

        Day otherDay = (Day) other;
        return otherDay.date.equals(date);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(date);
    }

}
