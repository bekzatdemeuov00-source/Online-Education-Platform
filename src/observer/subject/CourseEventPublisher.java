package observer.subject;

import observer.event.CourseEvent;
import observer.event.EventType;
import observer.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Concrete subject: publishes events of one course and keeps their history. */
public class CourseEventPublisher implements Subject {
    private final String courseTitle;
    // CopyOnWrite: safe if someone unsubscribes while we are notifying
    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private final List<CourseEvent> history = new ArrayList<>();

    public CourseEventPublisher(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public void subscribe(Observer observer) {
        if (!observers.contains(observer)) {   // no duplicates
            observers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    /** Creates the event, stores it in history and notifies everyone. */
    public void publish(EventType type, String message) {
        CourseEvent event = new CourseEvent(courseTitle, type, message);
        history.add(event);
        notifyObservers(event);
    }

    @Override
    public void notifyObservers(CourseEvent event) {
        observers.forEach(observer -> observer.update(event));
    }

    public List<CourseEvent> getHistory() {
        return List.copyOf(history);
    }

    public int getObserverCount() {
        return observers.size();
    }
}
