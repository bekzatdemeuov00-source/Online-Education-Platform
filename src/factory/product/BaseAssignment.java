package factory.product;

/** Shared state for all assignments (DRY). */
public abstract class BaseAssignment implements Assignment {
    private final String name;

    protected BaseAssignment(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
