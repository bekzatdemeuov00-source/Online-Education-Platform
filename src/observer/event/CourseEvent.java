package observer.event;

import java.time.LocalTime;

/** Immutable event that is sent to observers (instead of a raw String). */
public record CourseEvent(String courseTitle, EventType type, String message, LocalTime time) {

    public CourseEvent(String courseTitle, EventType type, String message) {
        this(courseTitle, type, message, LocalTime.now().withNano(0));
    }
}
