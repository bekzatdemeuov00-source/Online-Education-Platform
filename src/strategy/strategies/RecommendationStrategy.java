package strategy.strategies;

import builder.product.Course;

import java.util.List;

public interface RecommendationStrategy {
    List<Course> recommend(List<Course> catalog, int limit);

    /** Human-readable name (useful for UI and logs). */
    default String getName() {
        return getClass().getSimpleName();
    }
}
