package com.example.palacios.winkshopappbar;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.palacios.winkshopappbar.dummy.DummyContent;

import java.util.List;

import Adapters.AdaptadorOfertas;
import Models.Carritos;
import Models.ListasProductosSing;
import Models.Ofertas;
import Models.Productos;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProductosFragment.OnListFragmentInteractionListener,OfertasFragment.OnListFragmentInteractionListener {

    ImageButton imageButtonCarrito;
    TextView tvCarrito,tvUsuario,tvCorreo;
    String idusuario ="",nombre = "",correo="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = getIntent().getStringExtra("nombre");
        correo = getIntent().getStringExtra("correo");
        idusuario = getIntent().getStringExtra("idusuario");
//
//        tvUsuario = findViewById(R.id.tvUsuario);
//        tvCorreo = findViewById(R.id.tvCorreo);
//        tvUsuario.setText(correo);
//        tvUsuario.setText(nombre);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
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
        View hView =  navigationView.getHeaderView(0);
        tvCorreo = (TextView)hView.findViewById(R.id.tvCorreo);
        tvUsuario = (TextView)hView.findViewById(R.id.tvUsuario);
        if(nombre!=null)
        tvUsuario.setText(nombre);
        if(correo!=null)
            tvCorreo.setText(correo);

        imageButtonCarrito = toolbar.findViewById(R.id.imageButtonCarrito);
        tvCarrito = toolbar.findViewById(R.id.tvCarrito);

        int numeroProductos = ListasProductosSing.arrayListIdProductos.size();

        if(numeroProductos!=0){
            tvCarrito.setText(""+numeroProductos);
        }else{
            tvCarrito.setText("");
        }
        imageButtonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListaCarritoActivity.class);
                startActivity(i);
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,new OfertasFragment()).commit();

        getSupportActionBar().setTitle("WinkShop Ofertas");
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

      android.support.v4.app.Fragment f = null;

        boolean fragmentSeleccionado = false;
        String titulo = "";

        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

f = new OfertasFragment();
fragmentSeleccionado = true;


        } else if (id == R.id.nav_caballeros) {
            ProductosFragment productosFragment = new ProductosFragment();
            productosFragment.setIndex(1);
            f= productosFragment;
            fragmentSeleccionado = true;
            titulo = "Caballeros";

        } else if (id == R.id.nav_damas) {
                ProductosFragment productosFragment = new ProductosFragment();
                productosFragment.setIndex(2);
                f = productosFragment;
            fragmentSeleccionado = true;
                titulo="Damas";
        } else if (id == R.id.nav_ninios) {
            ProductosFragment productosFragment = new ProductosFragment();
            productosFragment.setIndex(14);
            f = productosFragment;
            fragmentSeleccionado = true;
            titulo="Niñ@s";
        } else if (id == R.id.nav_share) {
        }
//        } else if (id == R.id.nav_send) {
//
//        }


        if(fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,f).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(titulo);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void flipperImages(int i){


 /*       ImageView imgView = new ImageView(getApplication());
        imgView.setBackgroundResource(i);

        viewFlipper.addView(imgView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getApplicationContext(),android.R.anim.slide_in_left);
*/

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onListFragmentInteraction(Productos item) {

        Intent i = new Intent(getApplicationContext(),DetailActivity.class);
        i.putExtra("id",item.getIdProducto()+"");
        startActivity(i);
//Toast.makeText(getApplicationContext(),item.getIdProducto()+"",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Carritos> listaCarritos = ListasProductosSing.getCarritosList();

        int numeroProductos =0;

        for(int i = 0;i<listaCarritos.size();i++){
            numeroProductos = listaCarritos.get(i).getCantidad()+numeroProductos;
        }

        if(numeroProductos!=0){
            tvCarrito.setText(""+numeroProductos);
        }else{
            tvCarrito.setText("");
        }
        // your code (for example: initialize(); etc.. )
    }
}
