package com.example.palacios.winkshopappbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import Adapters.AdaptadorOfertas;
import Models.Ofertas;
import Models.Productos;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaballerosFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CaballerosFrag extends Fragment {

    private OnFragmentInteractionListener mListener;

    ListView listView;
    List<Productos> listaProductos;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();
    String[] productos,descripcion;
    double[] precio;
    int[] imagenes;

    public CaballerosFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_caballeros, container, false);
        listView = view.findViewById(R.id.ListViewPpalCaballero);


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


                    int tamanio = listaProductos.size();
                    productos = new String[tamanio];
                    descripcion = new String[tamanio];
                    precio = new double[tamanio];
                    imagenes = new int[tamanio];

                    for(int i =0;i<listaProductos.size();i++){
                        productos[i] = listaProductos.get(i).getNombreProducto();
                        descripcion[i] = listaProductos.get(i).getDescripcion();
                        precio[i] = listaProductos.get(i).getPrecio();
                        imagenes[i] = R.drawable.camisaverde;
                    }

                    progressDialog.dismiss();

                    final  AdaptadorOfertas adaptadorOfertas = new AdaptadorOfertas(getActivity(),productos,imagenes,descripcion,precio);
                    listView.setAdapter(adaptadorOfertas);
                }
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {

            }
        });






        return inflater.inflate(R.layout.fragment_caballeros, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
