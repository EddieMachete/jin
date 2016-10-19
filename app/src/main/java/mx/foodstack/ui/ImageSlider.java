package mx.foodstack.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import mx.foodstack.core.Highlight;
import mx.foodstack.jin.R;

/**
 * Created by eecheverria on 10/13/2016.
 */

// https://www.bignerdranch.com/blog/viewpager-without-fragments/
// https://www.intertech.com/Blog/android-custom-view-tutorial-part-1-combining-existing-views/
public class ImageSlider extends LinearLayout {
    View mRootView;

    public ImageSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        mRootView = inflate(context, R.layout.image_slider, this);

        Highlight[] highlights = {
                new Highlight(){{
                    imageUri = "http://www.smithfieldfoods.com/images/home/packaged-brands/armour-food.jpg";
                    title = "Highlight1";
                }},
                new Highlight(){{
                    imageUri = "https://upload.wikimedia.org/wikipedia/commons/b/b7/Meatfoodgroup.jpg";
                    title = "Highlight2";
                }}
        };

        ViewPager pager = (ViewPager) findViewById(R.id.slides);
        ImageSliderAdapter adapter = new ImageSliderAdapter(context, highlights, "imageUri");
        pager.setAdapter(adapter);
    }
}