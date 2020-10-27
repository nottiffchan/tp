package trackitnus.ui.upcoming;

import java.util.Objects;

public class CalendarSection {
    private String title;

    public CalendarSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CalendarSection)) {
            return false;
        }

        CalendarSection otherCalSection = (CalendarSection) other;
        return otherCalSection.title.equals(title);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title);
    }
}
