package mx.foodstack.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import mx.foodstack.core.Highlight;
import mx.foodstack.jin.MainActivity;
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

        Highlight[] highlights = {
                new Highlight(){{imageUri = "@drawable/ic_launcher";}},
                new Highlight(){{imageUri = "@drawable/ic_launcher";}},
        };
    }

    public void loadSlides(String[] slideUris) {
        String[] slides = {"", ""};

        //ArrayAdapter slidesAdapter = new ArrayAdapter<>(this, R.layout.image_slider, R.id.slide, slides);
        //ListView groupsView = (ListView)findViewById(R.id.groups);
        //groupsView.setAdapter(groupsAdapter);

        //groupsView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //        slideClicked(id);
        //    }
        //});
    }

    private void slideClicked(long id) {
        //Toast.makeText(MainActivity.this, "Clicked " + id, Toast.LENGTH_SHORT).show();
    }










    //@Override
    //public int getCount() {
    //    return products.size();
    //}

    //@Override
    //public View instantiateItem(ViewGroup container, final int position) {
    //    LayoutInflater inflater = (LayoutInflater) activity
    //            .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    //    View view = inflater.inflate(R.layout.vp_image, container, false);

    //    ImageView mImageView = (ImageView) view
    //            .findViewById(R.id.image_display);
    //    mImageView.setOnClickListener(new OnClickListener() {

    //        @Override
    //        public void onClick(View v) {
    //            Bundle arguments = new Bundle();
    //            Fragment fragment = null;
    //            Log.d("position adapter", "" + position);
    //            Product product = (Product) products.get(position);
    //            arguments.putParcelable("singleProduct", product);

    //            // Start a new fragment
    //            fragment = new ProductDetailFragment();
    //            fragment.setArguments(arguments);

    //            FragmentTransaction transaction = activity
    //                    .getSupportFragmentManager().beginTransaction();
    //            transaction.replace(R.id.content_frame, fragment,
    //                    ProductDetailFragment.ARG_ITEM_ID);
    //            transaction.addToBackStack(ProductDetailFragment.ARG_ITEM_ID);
    //            transaction.commit();
    //        }
    //    });
    //    imageLoader.displayImage(
    //            ((Product) products.get(position)).getImageUrl(), mImageView,
    //            options, imageListener);
    //    container.addView(view);
    //    return view;
    //}

    //@Override
    //public void destroyItem(ViewGroup container, int position, Object object) {
    //    container.removeView((View) object);
    //}

    //@Override
    //public boolean isViewFromObject(View view, Object object) {
    //    return view == object;
    //}

    //private static class ImageDisplayListener extends
    //        SimpleImageLoadingListener {

    //    static final List<String> displayedImages = Collections
    //            .synchronizedList(new LinkedList<String>());

    //    @Override
    //    public void onLoadingComplete(String imageUri, View view,
    //                                  Bitmap loadedImage) {
    //        if (loadedImage != null) {
    //            ImageView imageView = (ImageView) view;
    //            boolean firstDisplay = !displayedImages.contains(imageUri);
    //            if (firstDisplay) {
    //                FadeInBitmapDisplayer.animate(imageView, 500);
    //                displayedImages.add(imageUri);
    //            }
    //        }
    //    }
    //}
}