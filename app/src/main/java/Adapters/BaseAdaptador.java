package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palacios.winkshopappbar.R;

public class BaseAdaptador extends BaseAdapter {


    private String[] productos;
    private int[] imagenes;
    private Context context;
    private String[] descripcion;
    private double[] precio;
    private View item;

    public BaseAdaptador(Context context, String[] productos, int[] imagenes, String[] descripcion,double[] precio) {

        this.productos = productos;
        this.imagenes = imagenes;
        this.context = context;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public int getCount() {
        return productos.length;
    }

    @Override
    public Object getItem(int i) {
        return productos[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.listofertaslayout,viewGroup,false);
        }
        TextView tvProducto =(TextView)view.findViewById(R.id.tvProducto);
        tvProducto.setText(productos[i]);
        TextView tvDescripcion =(TextView)view.findViewById(R.id.tvDescripcion);
        tvDescripcion.setText(descripcion[i]);
        TextView tvPrecio = (TextView)view.findViewById(R.id.tvPrecio);
        tvPrecio.setText("$"+precio[i]);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewProducto);
        imageView.setImageResource(imagenes[i]);
        return view;
    }
}
