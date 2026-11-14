package strategy.strategies;

import builder.product.Course;

import java.util.Comparator;
import java.util.List;

public class ByPopularityStrategy implements RecommendationStrategy {
    @Override
    public List<Course> recommend(List<Course> catalog, int limit) {
        return catalog.stream()
                .sorted(Comparator.comparingInt(Course::getPopularity).reversed())
                .limit(limit)
                .toList();
    }
}
