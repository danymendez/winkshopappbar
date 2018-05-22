package com.example.palacios.winkshopappbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Models.ListasProductosSing;
import Models.Productos;
import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import Tasks.ImageDownload;
import retrofit2.Call;
import retrofit2.Callback;

public class DetailActivity extends AppCompatActivity {

    TextView tvDescripcion,tvDescuento,tvPrecio ;
    ImageView imageView;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvDescripcion = findViewById(R.id.tvDescripcionDet);
        tvDescuento= findViewById(R.id.tvDescuentoDet);
        tvPrecio = findViewById(R.id.tvPrecioDet);
        imageView = findViewById(R.id.imgViewDet);

        final String id = getIntent().getStringExtra("id").toString();

        final int IdProductos = Integer.parseInt(id);
        final WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);
//        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
//
//
//        progressDialog.setMax(100);
//        progressDialog.setTitle("");
//        progressDialog.setMessage("Cargando");
//        progressDialog.show();

//
//        service.getProductos(IdProductos).enqueue(new Callback<Productos>() {
//            @Override
//            public void onResponse(Call<Productos> call, retrofit2.Response<Productos> response) {
//                if(response.isSuccessful()){
//                    Productos productos = response.body();
        Productos productos = new Productos();
       int Index = 0;
        for(int i = 0;i<ListasProductosSing.getListaProductos().size();i++){
            if(IdProductos==ListasProductosSing.getListaProductos().get(i).IdProducto){
                productos=ListasProductosSing.getListaProductos().get(i);
                Index = i;
            }
        }
                  //  ImageDownload imgdownload= new ImageDownload(imageView);
                 //   imgdownload.execute("http://wintenten.eastus2.cloudapp.azure.com/Documents/"+productos.getUrlImagen());
        imageView.setImageBitmap(ListasProductosSing.getBitmaps()[Index]);
                    tvDescripcion.setText(productos.getDescripcion());
                    tvPrecio.setText(productos.getPrecio()+"");


//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Productos> call, Throwable t) {
//
//            }
//        });




    }
}
