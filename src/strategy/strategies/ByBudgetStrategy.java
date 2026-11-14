package strategy.strategies;

import builder.product.Course;

import java.util.Comparator;
import java.util.List;

/** Best courses (by popularity) that fit into the student's budget. */
public class ByBudgetStrategy implements RecommendationStrategy {
    private final double maxPrice;

    public ByBudgetStrategy(double maxPrice) {
        if (maxPrice < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Course> recommend(List<Course> catalog, int limit) {
        return catalog.stream()
                .filter(course -> course.getPrice() <= maxPrice)
                .sorted(Comparator.comparingInt(Course::getPopularity).reversed())
                .limit(limit)
                .toList();
    }
}
