package mx.foodstack.ui;

/**
 * Created by eecheverria on 10/17/2016.
 */

public class ImageSliderSlide {
    private String mUri;
    private Object mTarget;

    public ImageSliderSlide() {

    }

    public ImageSliderSlide(String uri, Object target) {
        mUri = uri;
        mTarget = target;
    }

    public String getUri() {
        return mUri;
    }

    public Object getTarget() {
        return mTarget;
    }
}