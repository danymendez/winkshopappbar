package Tasks;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Palacios on 20/5/2018.
 */

public class ImageDownload extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    ProgressDialog progressDialog;
    public ImageDownload(ImageView bmImage) {
        this.bmImage = bmImage;
    }
    public ImageDownload(ImageView bmImage,ProgressDialog progressDialog) {
        this.bmImage = bmImage;
        this.progressDialog=progressDialog;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
     //   progressDialog.dismiss();
        bmImage.setImageBitmap(result);
    }
}
