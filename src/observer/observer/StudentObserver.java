package observer.observer;

public class StudentObserver extends NamedObserver {
    public StudentObserver(String name) {
        super(name);
    }

    @Override
    protected String role() {
        return "Student";
    }
}
