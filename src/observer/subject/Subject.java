package observer.subject;

import observer.event.CourseEvent;
import observer.observer.Observer;

public interface Subject {
    void subscribe(Observer observer);

    void unsubscribe(Observer observer);

    void notifyObservers(CourseEvent event);
}
