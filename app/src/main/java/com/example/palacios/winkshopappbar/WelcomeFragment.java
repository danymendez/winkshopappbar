package com.example.palacios.winkshopappbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
 * {@link WelcomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class WelcomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    ViewFlipper viewFlipper;
    ListView listView;
    String[] productos,descripcion;
    double[] precio;
    int[] imagenes;
    List<Ofertas> listaOfertas;
    List<Productos> listaProductos;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_welcome,container,false);
        viewFlipper = (ViewFlipper)view.findViewById(R.id.viewFlipper);

        listView = (ListView)view.findViewById(R.id.ListViewPpal);



        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        callingResponse();
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

    public void flipperImages(int i){


        ImageView imgView = new ImageView(getActivity());
        imgView.setBackgroundResource(i);

        viewFlipper.addView(imgView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);


    }

    public void callingResponse(){
        flipperImages(R.drawable.promo1);
        flipperImages(R.drawable.promo2);
        flipperImages(R.drawable.promo3);

        final WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());


        progressDialog.setMax(100);
        progressDialog.setTitle("");
        progressDialog.setMessage("Cargando");
        progressDialog.show();

        service.getOfertas().enqueue(new Callback<List<Ofertas>>() {
            @Override
            public void onResponse(Call<List<Ofertas>> call, Response<List<Ofertas>> response) {
                if(response.isSuccessful()){
                    listaOfertas = response.body();

                    service.getProductos().enqueue(new Callback<List<Productos>>() {
                        @Override
                        public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                            if(response.isSuccessful()){

                                listaProductos = response.body();


                                int tamanio = listaOfertas.size();
                                productos = new String[tamanio];
                                descripcion = new String[tamanio];
                                precio = new double[tamanio];
                                imagenes = new int[tamanio];




                                for(int o = 0;o< listaOfertas.size();o++) {

                                    for(int i =0;i<listaProductos.size();i++) {
                                        if (listaOfertas.get(o).getIdProducto() == listaProductos.get(i).getIdProducto()) {
                                            productos[o] = listaProductos.get(i).getNombreProducto();
                                            descripcion[o] = listaProductos.get(i).getDescripcion();
                                            precio[o] = listaProductos.get(i).getPrecio();
                                            imagenes[o] = R.drawable.camisaverde;


                                        }
                                    }
                                }

                                progressDialog.dismiss();

                                final AdaptadorOfertas adaptadorOfertas = new AdaptadorOfertas(getActivity(),productos,imagenes,descripcion,precio);
                                listView.setAdapter(adaptadorOfertas);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Productos>> call, Throwable t) {
                            Toast.makeText(getActivity(),t.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Ofertas>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
