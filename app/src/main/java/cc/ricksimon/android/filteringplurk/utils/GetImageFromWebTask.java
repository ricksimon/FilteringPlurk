package cc.ricksimon.android.filteringplurk.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

import cc.ricksimon.android.filteringplurk.R;

/**
 * Created by Simon on 2018/2/15.
 */

public class GetImageFromWebTask extends AsyncTask <Void,Void,Bitmap>{

    private static final String TAG = GetImageFromWebTask.class.getSimpleName();

    private ImageView imageView;
    private String imageUrl;
    private Object tag;
    private long userId;

    public GetImageFromWebTask(ImageView imageView, String imageUrl){
        this.imageView = imageView;
        this.imageUrl = imageUrl;

        tag = imageView.getTag(R.id.TAG_PLURK_USER_ID);
        if(tag != null) {
            userId = (long) tag;
        }
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        //TODO: check cache and load
        Bitmap result = null;

        if(imageUrl == null || imageUrl.isEmpty()){
            Log.e(TAG, "imageUrl invalid");
        }else if(tag == null){
            Log.e(TAG, "tag on view is null");
        }else {
            try {
                URL urlImage = new URL(imageUrl);
                result = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(bitmap == null){
            Log.e(TAG, "bitmap is null");
            return;
        }

        //TODO: cache image

        if(imageView == null){
            Log.e(TAG, "imageView is null");
            return;
        }

        if(tag == null){
            Log.e(TAG, "tag on view is null");
            return;
        }

        long thisUserId = (long)imageView.getTag(R.id.TAG_PLURK_USER_ID);
        if(userId == thisUserId) {
            Log.e(TAG,"set image:"+imageUrl);
            imageView.setImageBitmap(bitmap);
        } else {
            Log.e(TAG,"user changed from "+userId+" to "+thisUserId+" , skip");
        }
    }
}
