package strategy.context;

import builder.product.Course;
import strategy.strategies.RecommendationStrategy;

import java.util.List;
import java.util.Objects;

/** Context: uses whichever strategy is currently set. */
public class CourseRecommender {
    private RecommendationStrategy strategy;

    public CourseRecommender(RecommendationStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    public RecommendationStrategy getStrategy() {
        return strategy;
    }

    public List<Course> recommend(List<Course> catalog, int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit cannot be negative");
        }
        return strategy.recommend(catalog, limit);
    }
}
