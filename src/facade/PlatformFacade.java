
package facade;

import builder.builder.CourseBuilder;
import builder.product.Course;
import builder.product.Level;
import decorator.component.BasicCourseOffer;
import decorator.component.CourseOffer;
import decorator.decorator.CertificateDecorator;
import decorator.decorator.MentorDecorator;
import factory.creator.AssignmentFactory;
import factory.product.Assignment;
import factory.product.CodingTask;
import factory.product.Quiz;
import factory.product.VideoLecture;
import observer.event.EventType;
import observer.observer.StudentObserver;
import observer.observer.TeacherObserver;
import observer.subject.CourseEventPublisher;
import strategy.context.CourseRecommender;
import strategy.strategies.ByPopularityStrategy;
import strategy.strategies.RecommendationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Facade: one simple entry point to the whole platform. */
public class PlatformFacade {

    private final AssignmentFactory assignmentFactory = new AssignmentFactory();
    private final CourseRecommender recommender =
            new CourseRecommender(new ByPopularityStrategy());
    private final List<Course> catalog = new ArrayList<>();
    private final Map<Course, CourseEventPublisher> publishers = new HashMap<>();

    public PlatformFacade() {
        assignmentFactory.register("quiz", Quiz::new);
        assignmentFactory.register("video", VideoLecture::new);
        assignmentFactory.register("coding", CodingTask::new);
    }

    /**
     * @param assignments assignment name -> assignment type (insertion order is kept)
     */
    public Course createCourse(
            String title,
            String teacher,
            int weeks,
            Level level,
            double price,
            int popularity,
            Map<String, String> assignments
    ) {
        CourseBuilder builder = new CourseBuilder()
                .setTitle(title)
                .setTeacher(teacher)
                .setDurationWeeks(weeks)
                .setLevel(level)
                .setPrice(price)
                .setPopularity(popularity);

        assignments.forEach((name, type) ->
                builder.addAssignment(assignmentFactory.create(type, name)));

        Course course = builder.build();
        catalog.add(course);

        CourseEventPublisher publisher = new CourseEventPublisher(title);
        publisher.subscribe(new TeacherObserver(teacher));
        publishers.put(course, publisher);

        return course;
    }

    public void enroll(Course course, String studentName) {
        CourseEventPublisher publisher = publisherOf(course);
        publisher.subscribe(new StudentObserver(studentName));

        publisher.publish(
                EventType.ENROLLED,
                studentName + " enrolled"
        );
    }

    public void gradeAssignment(Course course, String assignmentName) {
        boolean exists = course.getAssignments().stream()
                .anyMatch(a -> a.getName().equals(assignmentName));

        if (!exists) {
            throw new IllegalArgumentException(
                    "No such assignment: " + assignmentName
            );
        }

        publisherOf(course).publish(
                EventType.GRADED,
                "Assignment '" + assignmentName + "' has been graded"
        );
    }

    public void startCourse(Course course) {
        course.getAssignments().forEach(Assignment::start);
    }

    public List<Course> recommend(RecommendationStrategy strategy, int limit) {
        recommender.setStrategy(strategy);
        return recommender.recommend(catalog, limit);
    }

    public CourseOffer buyCourse(
            Course course,
            boolean withCertificate,
            boolean withMentor
    ) {
        CourseOffer offer = new BasicCourseOffer(course);

        if (withCertificate) {
            offer = new CertificateDecorator(offer);
        }

        if (withMentor) {
            offer = new MentorDecorator(offer);
        }

        return offer;
    }

    private CourseEventPublisher publisherOf(Course course) {
        CourseEventPublisher publisher = publishers.get(course);

        if (publisher == null) {
            throw new IllegalArgumentException(
                    "Unknown course: " + course.getTitle()
            );
        }

        return publisher;
    }
}
