package factory.product;

public class CodingTask extends BaseAssignment {
    public CodingTask(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("Opening coding task: " + getName());
    }
}
