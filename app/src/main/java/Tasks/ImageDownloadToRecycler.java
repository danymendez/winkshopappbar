package Tasks;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.palacios.winkshopappbar.OfertasFragment;
import com.example.palacios.winkshopappbar.ProductosFragment;

import java.io.InputStream;
import java.util.List;

import Adapters.MyOfertasRecyclerViewAdapter;
import Adapters.MyProductosRecyclerViewAdapter;
import Models.Productos;

/**
 * Created by Palacios on 20/5/2018.
 */

public class ImageDownloadToRecycler extends AsyncTask<String, Void, Bitmap[]> {

    Bitmap[] bitmap;
    RecyclerView recyclerView;
    List<Productos> listaproductos;
    OfertasFragment.OnListFragmentInteractionListener listenerOfertas;
    ProductosFragment.OnListFragmentInteractionListener listenerProductos;
    ProgressDialog progressDialog;
    public ImageDownloadToRecycler(List<Productos> listaproductos, OfertasFragment.OnListFragmentInteractionListener listener, RecyclerView recyclerView,ProgressDialog progressDialog) {

        this.recyclerView = recyclerView;
        this.listaproductos = listaproductos;
        this.listenerOfertas = listener;
        this.listenerProductos = null;
        this.bitmap = new Bitmap[listaproductos.size()];
        this.progressDialog = progressDialog;
    }
    public ImageDownloadToRecycler(List<Productos> listaproductos, ProductosFragment.OnListFragmentInteractionListener listener, RecyclerView recyclerView,ProgressDialog progressDialog ) {

        this.recyclerView = recyclerView;
        this.listaproductos = listaproductos;
        this.listenerOfertas = null;
        this.listenerProductos = listener;
        this.bitmap = new Bitmap[listaproductos.size()];
        this.progressDialog = progressDialog;
    }

    protected Bitmap[] doInBackground(String... urls) {
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

      if(listenerProductos!=null) {
          this.progressDialog.dismiss();
          this.recyclerView.setAdapter(new MyProductosRecyclerViewAdapter(listaproductos, result, listenerProductos));
      }

      if(listenerOfertas!=null){
          this.progressDialog.dismiss();
          this.recyclerView.setAdapter(new MyOfertasRecyclerViewAdapter(listaproductos, result, listenerOfertas));

      }


    }

    }