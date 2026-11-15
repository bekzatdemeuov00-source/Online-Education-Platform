package decorator.decorator;

import decorator.component.CourseOffer;

public abstract class CourseOfferDecorator implements CourseOffer {
    protected final CourseOffer wrapped;

    protected CourseOfferDecorator(CourseOffer wrapped) {
        this.wrapped = wrapped;
    }
}
