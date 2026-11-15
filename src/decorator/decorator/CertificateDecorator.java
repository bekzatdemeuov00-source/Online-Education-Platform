package decorator.decorator;

import decorator.component.CourseOffer;

public class CertificateDecorator extends CourseOfferDecorator {
    private static final double CERTIFICATE_PRICE = 20.0;

    public CertificateDecorator(CourseOffer wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + Certificate";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + CERTIFICATE_PRICE;
    }
}
