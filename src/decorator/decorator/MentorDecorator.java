package decorator.decorator;

import decorator.component.CourseOffer;

public class MentorDecorator extends CourseOfferDecorator {
    private static final double MENTOR_PRICE = 50.0;

    public MentorDecorator(CourseOffer wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + Personal mentor";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + MENTOR_PRICE;
    }
}
