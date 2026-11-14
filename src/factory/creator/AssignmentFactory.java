package factory.creator;

import factory.product.Assignment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Factory: creates assignments by type.
 * New types are registered, so the factory is never modified (Open/Closed).
 */
public class AssignmentFactory {
    private final Map<String, Function<String, Assignment>> creators = new HashMap<>();

    public void register(String type, Function<String, Assignment> creator) {
        creators.put(type.toLowerCase(), creator);
    }

    public Assignment create(String type, String name) {
        Function<String, Assignment> creator = creators.get(type.toLowerCase());
        if (creator == null) {
            throw new IllegalArgumentException("Unknown assignment type: " + type);
        }
        return creator.apply(name);
    }
}
