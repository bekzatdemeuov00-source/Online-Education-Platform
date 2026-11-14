package observer.observer;

import observer.event.CourseEvent;

public interface Observer {
    void update(CourseEvent event);
}
