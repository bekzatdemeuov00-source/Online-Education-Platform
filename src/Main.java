
import builder.product.Course;
import builder.product.Level;
import decorator.component.CourseOffer;
import facade.PlatformFacade;
import strategy.strategies.ByPopularityStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== ONLINE EDUCATION PLATFORM =====");

        // FACADE + BUILDER + FACTORY
        PlatformFacade platform = new PlatformFacade();

        Map<String, String> assignments = new LinkedHashMap<>();
        assignments.put("Java Quiz", "quiz");
        assignments.put("Java Video", "video");
        assignments.put("Java Practice", "coding");

        Course course = platform.createCourse(
                "Java Programming",
                "Alex",
                8,
                Level.BEGINNER,
                120.0,
                95,
                assignments
        );

        System.out.println("\n BUILDER ");
        System.out.println(course);

        // DECORATOR
        System.out.println("\n DECORATOR ");

        CourseOffer offer = platform.buyCourse(course, true, true);

        System.out.println(offer.getDescription());
        System.out.println("Price: " + offer.getPrice());

        // OBSERVER
        System.out.println("\n OBSERVER ");

        platform.enroll(course, "John");
        platform.gradeAssignment(course, "Java Quiz");

        // FACTORY
        System.out.println("\n FACTORY ");

        platform.startCourse(course);

        // STRATEGY
        System.out.println("\n STRATEGY ");

        System.out.println("Recommended courses:");

        platform.recommend(new ByPopularityStrategy(), 5)
                .forEach(System.out::println);

        System.out.println("\n ALL OPERATIONS COMPLETED ");
    }
}
