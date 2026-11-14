package strategy.strategies;

import builder.product.Course;

import java.util.Comparator;
import java.util.List;

/** Short courses first: only courses up to maxWeeks, shortest (then most popular) on top. */
public class ByDurationStrategy implements RecommendationStrategy {
    private final int maxWeeks;

    public ByDurationStrategy(int maxWeeks) {
        if (maxWeeks <= 0) {
            throw new IllegalArgumentException("Max weeks must be positive");
        }
        this.maxWeeks = maxWeeks;
    }

    @Override
    public List<Course> recommend(List<Course> catalog, int limit) {
        return catalog.stream()
                .filter(course -> course.getDurationWeeks() <= maxWeeks)
                .sorted(Comparator.comparingInt(Course::getDurationWeeks)
                        .thenComparing(Comparator.comparingInt(Course::getPopularity).reversed()))
                .limit(limit)
                .toList();
    }
}
