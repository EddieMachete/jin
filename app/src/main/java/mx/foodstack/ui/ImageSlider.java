package mx.foodstack.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import mx.foodstack.jin.R;

/**
 * Created by eecheverria on 10/13/2016.
 */

// https://www.intertech.com/Blog/android-custom-view-tutorial-part-1-combining-existing-views/
public class ImageSlider extends LinearLayout {
    private View mRootView;
    private boolean mShowText = false;

    public ImageSlider(Context context) {
        super(context);
        initialize(context);
    }

    public ImageSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImageSlider,
                0, 0);

        try {
            mShowText = a.getBoolean(R.styleable.ImageSlider_showText, false);
        } finally {
            a.recycle();
        }
    }

    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }

    private void initialize(Context context) {
        mRootView = inflate(context, R.layout.image_slider, this);
    }
}