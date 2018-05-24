package com.example.palacios.winkshopappbar;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Adapters.BaseAdaptador;
import Models.ListasProductosSing;
import Models.Ofertas;
import Models.Productos;

public class ListaCarritoActivity extends AppCompatActivity {
    ListView listView;
    List<Productos> listaproductosfiltrado;
    List<Productos> listaproductos;
    List<Ofertas> listaOfertas;
    ArrayList<Integer> IdsAComprar;
    ArrayList<Integer> IdsAComprarFiltradas;
    ArrayList<Integer>  cantidades;
    ArrayList<Integer> cantidadesFiltradas;
    Bitmap[] bitmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carrito);

        listView = findViewById(R.id.listViewCarritoDet);

      listaproductos = new LinkedList<Productos>();
      listaproductos = ListasProductosSing.getListaProductos();

      listaOfertas = new LinkedList<Ofertas>();
      listaOfertas = ListasProductosSing.getListaOfertas();

      IdsAComprar = ListasProductosSing.arrayListIdProductos;


      for(int i = 0;i<IdsAComprar.size();i++){

          IdsAComprarFiltradas.add(IdsAComprar.get(i));

          if(IdsAComprarFiltradas.size()>0) {
              for (int o = 0; o < IdsAComprarFiltradas.size(); o++) {
                  if (IdsAComprar.get(i) == IdsAComprarFiltradas.get(o)) {
                        cantidades.set(o,cantidades.get(o)+1);
                  }else{
                      IdsAComprarFiltradas.add(IdsAComprar.get(i));
                  }
              }
          }else{
              IdsAComprarFiltradas.add(IdsAComprar.get(i));
              cantidades.add(1);
          }
      }

      //Identificando cuales son los productos a comprar;
      for(int i =0;i<IdsAComprarFiltradas.size();i++){
          if(listaproductos.get(i).getIdProducto()==IdsAComprarFiltradas.get(i)){
              listaproductosfiltrado.add(listaproductos.get(i));
          }
      }

      bitmaps = new Bitmap[listaproductosfiltrado.size()];

        for(int i =0; i<listaproductos.size();i++){

            for(int o =0;o<listaproductosfiltrado.size();o++){
                if(listaproductosfiltrado.get(i).getIdProducto()==listaproductos.get(i).getIdProducto()){
                    bitmaps[o] = ListasProductosSing.getBitmaps()[i];
                }
            }
        }

        listView.setAdapter(new BaseAdaptador(getApplicationContext(),listaproductos,cantidades,bitmaps));


    }
}
