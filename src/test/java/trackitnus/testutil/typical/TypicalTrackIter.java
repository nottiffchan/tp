package trackitnus.testutil.typical;

import trackitnus.model.TrackIter;
import trackitnus.model.contact.Contact;
import trackitnus.model.lesson.Lesson;
import trackitnus.model.module.Module;
import trackitnus.model.task.Task;

public class TypicalTrackIter {
    /**
     * Returns an {@code TrackIter} with all the typical data
     */
    public static TrackIter getTypicalTrackIter() {
        TrackIter track = new TrackIter();
        for (Contact contact : TypicalContacts.get()) {
            track.addContact(contact);
        }
        for (Module module : TypicalModule.get()) {
            track.addModule(module);
        }
        for (Task task : TypicalTask.get()) {
            track.addTask(task);
        }
        for (Lesson lesson : TypicalLessons.get()) {
            track.addLesson(lesson);
        }
        return track;
    }

    /**
     * @return an constant {@code TrackIter} for JSON testing
     * all the underlying data will be static instead of dynamic data like getTypicalTrackIter
     */
    public static TrackIter getTypicalTrackIterConst() {
        TrackIter track = new TrackIter();
        for (Contact contact : TypicalContacts.get()) {
            track.addContact(contact);
        }
        for (Module module : TypicalModule.get()) {
            track.addModule(module);
        }
        for (Task task : TypicalTask.getConst()) {
            track.addTask(task);
        }
        for (Lesson lesson : TypicalLessons.get()) {
            track.addLesson(lesson);
        }
        return track;
    }
}
