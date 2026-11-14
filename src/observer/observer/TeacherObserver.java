package observer.observer;

public class TeacherObserver extends NamedObserver {
    public TeacherObserver(String name) {
        super(name);
    }

    @Override
    protected String role() {
        return "Teacher";
    }
}
