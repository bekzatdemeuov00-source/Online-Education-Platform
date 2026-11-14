package observer.observer;

import observer.event.CourseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Shared behaviour of all observers (DRY): name, inbox, printing, equality. */
public abstract class NamedObserver implements Observer {
    private final String name;
    private final List<CourseEvent> inbox = new ArrayList<>();

    protected NamedObserver(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    protected abstract String role();

    public String getName() {
        return name;
    }

    /** Everything this observer has received so far (handy for UI and tests). */
    public List<CourseEvent> getInbox() {
        return List.copyOf(inbox);
    }

    @Override
    public void update(CourseEvent event) {
        inbox.add(event);
        System.out.println("  [" + role() + " " + name + "] " + event.courseTitle() + ": " + event.message());
    }

    /** Same role + same name = same observer, so nobody is subscribed twice. */
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() && name.equals(((NamedObserver) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), name);
    }
}
