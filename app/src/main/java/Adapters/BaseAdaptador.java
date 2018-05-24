package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palacios.winkshopappbar.R;

import java.util.ArrayList;
import java.util.List;

import Models.Productos;

public class BaseAdaptador extends BaseAdapter {


    private String[] productos;
    private int[] imagenes;
    private Context context;
    private String[] descripcion;
    private double[] precio;
    private View item;

    List<Productos> listaproductos;
    private ArrayList<Integer> cantidades;
    private Bitmap[] bitmaps;

    public BaseAdaptador(Context context, String[] productos, int[] imagenes, String[] descripcion,double[] precio) {

        this.productos = productos;
        this.imagenes = imagenes;
        this.context = context;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public BaseAdaptador(Context context,List<Productos> listaproductos,  ArrayList<Integer> cantidades,Bitmap[] bitmaps) {
this.context = context;
   this.listaproductos = listaproductos;
   this.cantidades = cantidades;
   this.bitmaps = bitmaps;
    }



    @Override
    public int getCount() {
        return listaproductos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaproductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.listacarritolayout,viewGroup,false);
        }
        TextView tvProducto =(TextView)view.findViewById(R.id.tvProductoCarritoDet);
        tvProducto.setText(listaproductos.get(i).getNombreProducto());
        TextView tvDescripcion =(TextView)view.findViewById(R.id.tvDescripcionCarritoDet);
        tvDescripcion.setText(listaproductos.get(i).getDescripcion());
        TextView tvPrecio = (TextView)view.findViewById(R.id.tvPrecioCarritoDet);
        tvPrecio.setText("Cantidad: "+cantidades.get(i)+" X $"+listaproductos.get(i).getPrecio()+" Total = $"+(listaproductos.get(i).getPrecio()*cantidades.get(i)));
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProductoCarritoDet);
        imageView.setImageBitmap(bitmaps[i]);
        return view;
    }
}
