package strategy.strategies;

import builder.product.Course;
import builder.product.Level;

import java.util.Comparator;
import java.util.List;

public class ByLevelStrategy implements RecommendationStrategy {
    private final Level level;

    public ByLevelStrategy(Level level) {
        this.level = level;
    }

    @Override
    public List<Course> recommend(List<Course> catalog, int limit) {
        return catalog.stream()
                .filter(course -> course.getLevel() == level)
                .sorted(Comparator.comparingInt(Course::getPopularity).reversed())
                .limit(limit)
                .toList();
    }
}
