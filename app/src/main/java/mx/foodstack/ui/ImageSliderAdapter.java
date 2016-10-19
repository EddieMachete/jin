package mx.foodstack.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import mx.foodstack.jin.R;

/**
 * Created by eecheverria on 10/17/2016.
 */

public class ImageSliderAdapter extends PagerAdapter {
    private static final String TAG = "ImageSliderAdapter";
    private ArrayList<ImageSliderSlide> mSlides = new ArrayList<>();
    private Context mContext;

    public ImageSliderAdapter(Context context, Object[] targets, String uriFieldName) {
        mContext = context;

        for (Object target: targets) {
            try {
                Class<?> clazz = target.getClass();
                Field uriField = target.getClass().getField(uriFieldName);
                mSlides.add(new ImageSliderSlide(uriField.get(target).toString(), target));
            } catch (NoSuchFieldException ex) {
                Log.d("ImageSliderAdapter", ex.getMessage(), ex);
            } catch (IllegalAccessException ex) {
                Log.d("ImageSliderAdapter", ex.getMessage(), ex);
            }
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mSlides.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);

        ImageSliderSlide slide = mSlides.get(position);

        LayoutInflater inflater = LayoutInflater.from(mContext);//(LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View slideView = inflater.inflate(R.layout.image_slider_slide, container, false);
        //Log.d(TAG, inflater.toString());


        // -----------------
        // Gets the URL from the UI's text field.
        ConnectivityManager connMgr = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            ImageView imageView = (ImageView)slideView.findViewById(R.id.slide);
            new DownloadBitmapTask(imageView).execute(slide.getUri());
        } else {
            Log.d(TAG, "no connection");
            //textView.setText("No network connection available.");
        }

        //ImageView imageView = (ImageView)slideView.findViewById(R.id.slide);
        //imageView.setImageBitmap(
        //        getImageBitmap("https://www.google.ca/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"));
        // -----------------
        /*try {
            URL thumb_u = new URL("https://www.google.ca/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            imageView.setImageDrawable(thumb_d);
        }
        catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }*/



        container.addView(slideView);

        return slideView;
    }

    private Bitmap downloadBitmap(String url) throws IOException {
        InputStream is = null;
        Bitmap bm = null;

        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();

        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);

        } finally {
            is.close();
        }

        return bm;
    }

    private class DownloadBitmapTask extends AsyncTask<String, Void, String> {
        ImageView mImageView;
        Bitmap mBitmap;

        public DownloadBitmapTask(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected String doInBackground(String... params) {
            // params comes from the execute() call: params[0] is the url.
            try {
                mBitmap = downloadBitmap(params[0]);
                return "success";
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            mImageView.setImageBitmap(mBitmap);
            //textView.setText(result);
        }
    }
}