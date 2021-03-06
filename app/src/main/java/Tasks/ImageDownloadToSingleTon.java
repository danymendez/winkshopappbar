package Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.palacios.winkshopappbar.LoginActivity;
import com.example.palacios.winkshopappbar.NavigationActivity;

import java.io.InputStream;

import Models.ListasProductosSing;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Palacios on 21/5/2018.
 */

public class ImageDownloadToSingleTon extends AsyncTask<String, Void, Bitmap[]> {
    Bitmap[] bitmap;
    ProgressDialog progressDialog;
    Context context;
    String usuario;
    Intent intent;

    public ImageDownloadToSingleTon(ProgressDialog progressDialog) {

        this.progressDialog=progressDialog;
    }

    public ImageDownloadToSingleTon(ProgressDialog progressDialog,Context context,Intent intent) {

        this.progressDialog=progressDialog;
        this.context=context;
        this.intent=intent;
    }


    protected Bitmap[] doInBackground(String... urls) {
        bitmap = new Bitmap[urls.length];
        for (int i = 0; i<urls.length;i++) {
            String urldisplay = "http://wintenten.eastus2.cloudapp.azure.com/Documents/"+urls[i];
            // Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                this.bitmap[i] = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                this.bitmap[i] = null;
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

        }
        return this.bitmap;
    }
    protected void onPostExecute(Bitmap[] result) {
         //  progressDialog.dismiss();

           ListasProductosSing.setBitmaps(result);
           context.startActivity(intent);
    }
}

