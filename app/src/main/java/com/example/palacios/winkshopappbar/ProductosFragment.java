package com.example.palacios.winkshopappbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.palacios.winkshopappbar.dummy.DummyContent.DummyItem;

import java.util.LinkedList;
import java.util.List;

import Adapters.MyProductosRecyclerViewAdapter;
import Models.Ofertas;
import Models.Productos;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import Tasks.ImageDownloadToRecycler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ProductosFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    ViewFlipper viewFlipper;

    List<Ofertas> listaOfertas;
    List<Productos> listaProductos,listproductosFiltrados;
    RecyclerView recycler;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();
    private int index = 1;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductosFragment() {
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductosFragment newInstance(int columnCount) {
        ProductosFragment fragment = new ProductosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos_list, container, false);
        callingResponse(view);
        // Set the adapter

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Productos item);
    }

    public void callingResponse(View v){
       /* flipperImages(R.drawable.promo1);
        flipperImages(R.drawable.promo2);
        flipperImages(R.drawable.promo3);
*/
        final WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());


        progressDialog.setMax(100);
        progressDialog.setTitle("");
        progressDialog.setMessage("Cargando");
        progressDialog.show();


                    service.getProductos().enqueue(new Callback<List<Productos>>() {
                        @Override
                        public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                            if(response.isSuccessful()){

                                listaProductos = response.body();

                                listproductosFiltrados = new LinkedList<Productos>();

                                    for(int i =0;i<listaProductos.size();i++) {

                                            if(listaProductos.get(i).getIdCategoria()==index){
                                                listproductosFiltrados.add(listaProductos.get(i));
                                            }


                                        }



                               // progressDialog.dismiss();

                                if (v instanceof RecyclerView) {
                                    Context context = v.getContext();
                                    RecyclerView recyclerView = (RecyclerView) v;
                                    if (mColumnCount <= 1) {
                                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                                    } else {
                                        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                                    }
                                    if(listproductosFiltrados.size()==0){
                                        progressDialog.dismiss();
                                        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                                        dialog.setTitle( "Lo sentimos" )

                                                .setMessage("No hay productos Disponibles momentaneamente")
                                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialoginterface, int i) {
                                                        // Intent i = new Intent(getApplicationContext(),LoginActivity.class);

                                                    }
                                                }).show();
                                    }
                                     String[] urls = new String[listproductosFiltrados.size()];

                                    for(int i=0;i<listproductosFiltrados.size();i++){
                                        urls[i]=listproductosFiltrados.get(i).getUrlImagen();
                                    }

                                    ImageDownloadToRecycler imageDownloadToRecycler = new ImageDownloadToRecycler(listproductosFiltrados,mListener,recyclerView,progressDialog);
                                    imageDownloadToRecycler.execute(urls);

                                  //  recyclerView.setAdapter(new MyProductosRecyclerViewAdapter(listproductosFiltrados, mListener));

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Productos>> call, Throwable t) {
                            Toast.makeText(getActivity(),t.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                    });

    }


}
