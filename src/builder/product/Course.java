package builder.product;

import factory.product.Assignment;

import java.util.List;

/** Product of the Builder pattern. Immutable once built. */
public class Course {
    private final String title;
    private final String teacher;
    private final int durationWeeks;
    private final Level level;
    private final double price;
    private final int popularity;
    private final List<Assignment> assignments;

    public Course(String title, String teacher, int durationWeeks, Level level,
                  double price, int popularity, List<Assignment> assignments) {
        this.title = title;
        this.teacher = teacher;
        this.durationWeeks = durationWeeks;
        this.level = level;
        this.price = price;
        this.popularity = popularity;
        this.assignments = List.copyOf(assignments);
    }

    public String getTitle() { return title; }
    public String getTeacher() { return teacher; }
    public int getDurationWeeks() { return durationWeeks; }
    public Level getLevel() { return level; }
    public double getPrice() { return price; }
    public int getPopularity() { return popularity; }
    public List<Assignment> getAssignments() { return assignments; }

    @Override
    public String toString() {
        return title + " | " + teacher + " | " + level + " | " + durationWeeks + " weeks | "
                + assignments.size() + " assignments | $" + price + " | popularity " + popularity;
    }
}
