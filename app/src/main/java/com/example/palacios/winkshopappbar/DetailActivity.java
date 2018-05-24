package com.example.palacios.winkshopappbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import Models.Ofertas;
import Models.Productos;
import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import Tasks.ImageDownload;
import retrofit2.Call;
import retrofit2.Callback;

public class DetailActivity extends AppCompatActivity {

    TextView tvDescripcion,tvDescuento,tvPrecio,tvProducto,tvPrecioConDescuento ;
    ImageView imageView;
    Button btnCarrito;
    ImageButton imageButtonCarrito;
    TextView tvCarrito;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDet);
        toolbar.setTitle("Detalle");
        setSupportActionBar(toolbar);

        tvDescripcion = findViewById(R.id.tvDescripcionDet);
        tvDescuento= findViewById(R.id.tvDescuentoDet);
        tvPrecio = findViewById(R.id.tvPrecioDet);
        imageView = findViewById(R.id.imgViewDet);
        constraintLayout = findViewById(R.id.constrainLayout);
        tvProducto = findViewById(R.id.tvNombreProductoDet);
        btnCarrito = findViewById(R.id.btnCarritoDet);
        tvPrecioConDescuento = findViewById(R.id.tvPrecioConDescuentoDet);
        final String id = getIntent().getStringExtra("id").toString();
        imageButtonCarrito = toolbar.findViewById(R.id.imageButtonCarritoDet);
        tvCarrito = toolbar.findViewById(R.id.tvCarritoDet);
        int numeroProductos = ListasProductosSing.arrayListIdProductos.size();

        if(numeroProductos!=0){
            tvCarrito.setText(""+numeroProductos);
        }else{
            tvCarrito.setText("");
        }

        final int IdProductos = Integer.parseInt(id);
        final List<Ofertas> listaOfertas = ListasProductosSing.getListaOfertas();
        final List<Productos> listaProductos = ListasProductosSing.getListaProductos();
        Productos productos = new Productos();
        double descuento =0;
        int Index = 0;
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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


        for(int i = 0;i<listaProductos.size();i++){
            if(IdProductos==listaProductos.get(i).IdProducto){
                productos=listaProductos.get(i);
                Index = i;
            }
        }

           for(int o = 0;o<listaOfertas.size();o++){

                if(listaOfertas.get(o).getIdProducto()==productos.getIdProducto()){
                    descuento=listaOfertas.get(o).getDescuento()*100;
                }
           }
                  //  ImageDownload imgdownload= new ImageDownload(imageView);
                 //   imgdownload.execute("http://wintenten.eastus2.cloudapp.azure.com/Documents/"+productos.getUrlImagen());

                    imageView.setImageBitmap(ListasProductosSing.getBitmaps()[Index]);
                    tvDescripcion.setText(productos.getDescripcion());
                    tvProducto.setText(productos.getNombreProducto());
                    tvPrecio.setText("Precio Normal: $"+productos.getPrecio());
                    if(descuento!=0){
                        tvDescuento.setText(descuento+"%");
                        tvPrecioConDescuento.setText("Con descuento: $"+(productos.getPrecio()-(productos.getPrecio()*(descuento/100))));

                    }else{
                        tvDescuento.setText("");
                        constraintLayout.setBackground(null);
                        tvPrecioConDescuento.setText("");
                    }


                    btnCarrito.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ListasProductosSing.arrayListIdProductos.add(IdProductos);
                            int numeroProductos = ListasProductosSing.arrayListIdProductos.size();

                            if(numeroProductos!=0){
                                tvCarrito.setText(""+numeroProductos);
                            }else{
                                tvCarrito.setText("");
                            }

                            dialog.setTitle( "Éxito" )

                                    .setMessage("Producto Agregado Correctamente !!!")
                                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialoginterface, int i) {

                                        }
                                    }).show();
                        }
                    });


                    imageButtonCarrito.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getApplicationContext(),ListaCarritoActivity.class);
                            startActivity(i);
                        }
                    });
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

    @Override
    protected void onPostResume() {
        super.onPostResume();

        int numeroProductos = ListasProductosSing.arrayListIdProductos.size();

        if(numeroProductos!=0){
            tvCarrito.setText(""+numeroProductos);
        }else{
            tvCarrito.setText("");
        }
    }
}
