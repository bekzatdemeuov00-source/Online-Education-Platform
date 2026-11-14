package factory.product;

public class VideoLecture extends BaseAssignment {
    public VideoLecture(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("Playing video lecture: " + getName());
    }
}
