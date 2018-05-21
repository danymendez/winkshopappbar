package Adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palacios.winkshopappbar.OfertasFragment.OnListFragmentInteractionListener;
import com.example.palacios.winkshopappbar.R;
import com.example.palacios.winkshopappbar.dummy.DummyContent.DummyItem;

import java.util.List;

import Models.Productos;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyOfertasRecyclerViewAdapter extends RecyclerView.Adapter<MyOfertasRecyclerViewAdapter.ViewHolder> {

    private final List<Productos> mValues;
    private final Bitmap[] bitmaps;
    private final OnListFragmentInteractionListener mListener;

    public MyOfertasRecyclerViewAdapter(List<Productos> items,Bitmap[] bitmaps, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.bitmaps = bitmaps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listofertaslayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
           holder.mItem = mValues.get(position);
        holder.tvProducto.setText(mValues.get(position).NombreProducto);
        holder.tvDescripcion.setText(mValues.get(position).Descripcion);
        holder.tvPrecio.setText("$"+mValues.get(position).getPrecio());
        holder.imgView.setImageBitmap(bitmaps[position]);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvProducto,tvDescripcion,tvPrecio;
        public final ImageView imgView;
        // public final TextView mContentView;
        public Productos mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvProducto = (TextView) view.findViewById(R.id.tvProducto);
            tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);
            tvPrecio = (TextView) view.findViewById(R.id.tvPrecio);
            imgView = (ImageView) view.findViewById(R.id.imageViewProducto);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "tvProducto=" + tvProducto +
                    ", tvDescripcion=" + tvDescripcion +
                    ", tvPrecio=" + tvPrecio +
                    '}';
        }
    }
}
