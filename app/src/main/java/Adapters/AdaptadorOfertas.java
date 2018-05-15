package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palacios.winkshopappbar.R;

public class AdaptadorOfertas extends ArrayAdapter {

    private String[] productos;
    private int[] imagenes;
    private Context context;
    private String[] descripcion;
    private double[] precio;
    private View item;

    public AdaptadorOfertas(Context context, String[] productos, int[] imagenes, String[] descripcion,double[] precio) {
        super(context, R.layout.listofertaslayout,productos);
        this.productos = productos;
        this.imagenes = imagenes;
        this.context = context;
        this.descripcion = descripcion;
        this.precio = precio;
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        item = inflater.inflate(R.layout.listofertaslayout,parent,false);
        TextView tvProducto =(TextView)item.findViewById(R.id.tvProducto);
        tvProducto.setText(productos[position]);
        TextView tvDescripcion =(TextView)item.findViewById(R.id.tvDescripcion);
        tvDescripcion.setText(descripcion[position]);
        TextView tvPrecio = (TextView)item.findViewById(R.id.tvPrecio);
        tvPrecio.setText("$"+precio[position]);
        ImageView imageView = (ImageView) item.findViewById(R.id.imageViewProducto);
        imageView.setImageResource(imagenes[position]);

        return item;
    }
}