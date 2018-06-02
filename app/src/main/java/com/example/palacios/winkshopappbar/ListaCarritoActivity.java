package com.example.palacios.winkshopappbar;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Adapters.BaseAdaptador;
import Models.Carritos;
import Models.ListasProductosSing;
import Models.Ofertas;
import Models.Productos;

public class ListaCarritoActivity extends AppCompatActivity {
    ListView listView;
    List<Productos> listaproductosfiltrado;
    List<Productos> listaproductos;
    List<Carritos> listaCarritos;
    List<Ofertas> listaOfertas;
    ArrayList<Integer> IdsAComprar;
    ArrayList<Integer> IdsAComprarFiltradas;
    ArrayList<Integer>  cantidades;
    Bitmap[] bitmaps;
    TextView tvTotalCarrito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carrito);

        listView = findViewById(R.id.listViewCarritoDet);

      //llenarCarrito2();

        listaproductos = ListasProductosSing.getListaProductos();

       listaCarritos = ListasProductosSing.getCarritosList();

        double totales = 0;
        bitmaps = new Bitmap[listaCarritos.size()];
        for(int i =0; i<listaproductos.size();i++){

            for(int o =0;o<listaCarritos.size();o++){
                if(listaCarritos.get(o).getIdProducto()==listaproductos.get(i).getIdProducto()){
                    bitmaps[o] = ListasProductosSing.getBitmaps()[i];
                    totales +=(listaCarritos.get(o).getPrecioTotal());
                }
            }
        }

        tvTotalCarrito = findViewById(R.id.tvTotalCarrito);
        tvTotalCarrito.setText("El total de la compra es: $"+totales);
        BaseAdaptador baseAdaptador = new BaseAdaptador(getApplicationContext(),listaCarritos,bitmaps);
        listView.setAdapter(baseAdaptador);
       // baseAdaptador.notifyDataSetChanged();


    }

    public void llenarCarrito(){
       listaCarritos = ListasProductosSing.getCarritosList();


    }

    public void llenarCarrito2(){
        listaproductos = new LinkedList<Productos>();
        listaproductos = ListasProductosSing.getListaProductos();

        listaproductosfiltrado = new LinkedList<Productos>();

        listaOfertas = new LinkedList<Ofertas>();
        listaOfertas = ListasProductosSing.getListaOfertas();

        IdsAComprar = ListasProductosSing.getArrayListIdProductos();
        IdsAComprarFiltradas = new ArrayList<Integer>();
        cantidades = new ArrayList<Integer>();




        for(int i =0; i<IdsAComprar.size();i++){

            if(!IdsAComprarFiltradas.contains(IdsAComprar.get(i))){
                IdsAComprarFiltradas.add(IdsAComprar.get(i));
            }



        }
        for(int o = 0;o<IdsAComprarFiltradas.size();o++){
            int numeros = 0;
            for(int i = 0;i<IdsAComprar.size();i++){
                if(IdsAComprarFiltradas.get(o)==IdsAComprar.get(i)){
                    numeros++;
                }

            }
            cantidades.add(numeros);
        }


        //Identificando cuales son los productos a comprar;
        for(int i =0;i<listaproductos.size();i++){
            for(int o=0;o<IdsAComprarFiltradas.size();o++) {
                if (listaproductos.get(i).getIdProducto() == IdsAComprarFiltradas.get(o)) {
                    listaproductosfiltrado.add(listaproductos.get(i));
                }
            }
        }

        bitmaps = new Bitmap[listaproductosfiltrado.size()];

        double totales = 0;

        for(int i =0; i<listaproductos.size();i++){

            for(int o =0;o<listaproductosfiltrado.size();o++){
                if(listaproductosfiltrado.get(o).getIdProducto()==listaproductos.get(i).getIdProducto()){
                    bitmaps[o] = ListasProductosSing.getBitmaps()[i];
                    totales +=(cantidades.get(o)*listaproductosfiltrado.get(o).getPrecio());
                }
            }
        }

        tvTotalCarrito = findViewById(R.id.tvTotalCarrito);
        tvTotalCarrito.setText("El total de la compra es: $"+totales);
    }
}
