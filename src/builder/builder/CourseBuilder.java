package builder.builder;

import builder.product.Course;
import builder.product.Level;
import factory.product.Assignment;

import java.util.ArrayList;
import java.util.List;

/** Builder: step-by-step creation of a Course. */
public class CourseBuilder {
    private String title;
    private String teacher;
    private int durationWeeks = 4;
    private Level level = Level.BEGINNER;
    private double price = 0;
    private int popularity = 0;
    private final List<Assignment> assignments = new ArrayList<>();

    public CourseBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder setTeacher(String teacher) {
        this.teacher = teacher;
        return this;
    }

    public CourseBuilder setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
        return this;
    }

    public CourseBuilder setLevel(Level level) {
        this.level = level;
        return this;
    }

    public CourseBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public CourseBuilder setPopularity(int popularity) {
        this.popularity = popularity;
        return this;
    }

    public CourseBuilder addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
        return this;
    }

    public Course build() {
        if (title == null || title.isBlank()) {
            throw new IllegalStateException("Course title is required");
        }
        if (teacher == null || teacher.isBlank()) {
            throw new IllegalStateException("Course teacher is required");
        }
        if (durationWeeks <= 0) {
            throw new IllegalStateException("Duration must be positive");
        }
        if (price < 0) {
            throw new IllegalStateException("Price cannot be negative");
        }
        return new Course(title, teacher, durationWeeks, level, price, popularity, assignments);
    }
}
