package factory.product;

public class Quiz extends BaseAssignment {
    public Quiz(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("Starting quiz: " + getName());
    }
}
