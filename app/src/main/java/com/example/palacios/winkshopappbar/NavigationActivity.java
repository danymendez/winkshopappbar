package com.example.palacios.winkshopappbar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import Adapters.AdaptadorOfertas;
import Models.Ofertas;
import Models.Productos;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper viewFlipper;
    ListView listView;
    String[] productos,descripcion;
    double[] precio;
    int[] imagenes;
    List<Ofertas> listaOfertas;
    List<Productos> listaProductos;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);

        flipperImages(R.drawable.promo1);
        flipperImages(R.drawable.promo2);
        flipperImages(R.drawable.promo3);





//        AdaptadorOfertas adaptadorOfertas = new AdaptadorOfertas(getApplicationContext(),productos,imagenes,descripcion,precio);
//        listView.setAdapter(adaptadorOfertas);

        final WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);

        service.getOfertas().enqueue(new Callback<List<Ofertas>>() {
            @Override
            public void onResponse(Call<List<Ofertas>> call, Response<List<Ofertas>> response) {
                if(response.isSuccessful()){


                    service.getProductos().enqueue(new Callback<List<Productos>>() {
                        @Override
                        public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                            if(response.isSuccessful()){

                                listaProductos = response.body();


                                int tamanio = listaProductos.size();
                                productos = new String[tamanio];
                                descripcion = new String[tamanio];
                                precio = new double[tamanio];
                                imagenes = new int[tamanio];


                                for(int i =0;i<listaProductos.size();i++) {

                                    if (listaOfertas.get(i).getIdProducto() == listaProductos.get(i).getIdProducto()) {
                                        productos[i] = listaProductos.get(i).getNombreProducto();
                                        descripcion[i] = listaProductos.get(i).getDescripcion();
                                        precio[i] = listaProductos.get(i).getPrecio();
                                        imagenes[i] = R.drawable.camisaverde;


                                    }
                                }

        AdaptadorOfertas adaptadorOfertas = new AdaptadorOfertas(getApplicationContext(),productos,imagenes,descripcion,precio);
        listView.setAdapter(adaptadorOfertas);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Productos>> call, Throwable t) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Ofertas>> call, Throwable t) {

            }
        });




//        service.getProductos(4).enqueue(new Callback<Productos>() {
//            @Override
//            public void onResponse(Call<Productos> call, Response<Productos> response) {
//                if(response.isSuccessful()){
//
//                    Productos productos = response.body();
//
//                    Toast.makeText(getApplicationContext(),productos.NombreProducto+"",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Productos> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),t.getMessage().toString()+"",Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void flipperImages(int i){


        ImageView imgView = new ImageView(getApplication());
        imgView.setBackgroundResource(i);

        viewFlipper.addView(imgView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getApplicationContext(),android.R.anim.slide_in_left);


    }

}
