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

import Models.Carritos;
import Models.Productos;

public class BaseAdaptador extends BaseAdapter {



    private Context context;

    private View item;
    List<Carritos> listaCarrito;
    private Bitmap[] bitmaps;

    public BaseAdaptador(Context context,List<Carritos> listaCarrito,Bitmap[] bitmaps) {
        this.context = context;
        this.listaCarrito = listaCarrito;
        this.bitmaps = bitmaps;
    }



    @Override
    public int getCount() {
        return listaCarrito.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCarrito.get(i);
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
        tvProducto.setText(listaCarrito.get(i).getNombreProducto());
        TextView tvDescripcion =(TextView)view.findViewById(R.id.tvDescripcionCarritoDet);
        tvDescripcion.setText(listaCarrito.get(i).getDescripcion());
        TextView tvPrecio = (TextView)view.findViewById(R.id.tvPrecioCarritoDet);
        tvPrecio.setText("Cantidad: "+listaCarrito.get(i).getCantidad()+" X $"+listaCarrito.get(i).getPrecio()+" Total = $"+(listaCarrito.get(i).getPrecioTotal()));
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProductoCarritoDet);
        imageView.setImageBitmap(bitmaps[i]);
        return view;
    }
}
