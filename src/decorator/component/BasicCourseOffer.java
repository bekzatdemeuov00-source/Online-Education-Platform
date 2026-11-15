package decorator.component;

import builder.product.Course;

/** Concrete component: the plain course without extras. */
public class BasicCourseOffer implements CourseOffer {
    private final Course course;

    public BasicCourseOffer(Course course) {
        this.course = course;
    }

    @Override
    public String getDescription() {
        return course.getTitle();
    }

    @Override
    public double getPrice() {
        return course.getPrice();
    }
}
