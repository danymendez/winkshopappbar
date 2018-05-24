package com.example.palacios.winkshopappbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Models.ListasProductosSing;
import Models.Ofertas;
import Models.Productos;
import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import Tasks.ImageDownloadToSingleTon;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button ingresarBtn;
    TextInputLayout editTextUsuario;
    TextInputLayout editTextPassword;
    TextView tvwRegistrarse;
    Button btnRegistrarse,btnInvitado;
    List<Usuarios> usuariosList;
    String idusuario ="",nombre = "";

    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = getTextInputLayout(R.id.tlUsuarioLogin);
        editTextPassword = getTextInputLayout(R.id.tlPasswordLogin);
        ingresarBtn = (Button) findViewById(R.id.btnIngresar);
        btnInvitado = (Button)findViewById(R.id.btnInvitado);
        btnRegistrarse= (Button)findViewById(R.id.btnRegistrarseLogin);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

               progressDialog.setMax(100);
        progressDialog.setTitle("");
        progressDialog.setMessage("Cargando");

      //  WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);

        btnInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
//                startActivity(i);
                callResponse(progressDialog);
            }
        });

        ingresarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usuario = editTextUsuario.getEditText().getText().toString();
                final String password = editTextPassword.getEditText().getText().toString();

                if(TextUtils.isEmpty(usuario)){

                    editTextUsuario.getEditText().setError("Ingresar Usuario");
                    editTextUsuario.requestFocus();

                }else if(TextUtils.isEmpty(password)){

                    editTextPassword.getEditText().setError("Ingresar Contraseña");
                    editTextPassword.requestFocus();
                }else {

                    WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);

                    progressDialog.setMax(100);
                    progressDialog.setTitle("Login");
                    progressDialog.setMessage("Cargando");
                    progressDialog.show();

                    service.getUsuario().enqueue(new Callback<List<Usuarios>>() {
                        @Override
                        public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                            if (response.isSuccessful()) {
                                boolean seEncontroUsuario = false;
                                List<Usuarios> listaUsuario = response.body();

                                for (Usuarios us : listaUsuario) {
                                   if(us.getUsuario().equals(usuario) && us.getPassword().equals(password)){
                                      seEncontroUsuario = true;
                                      nombre = us.getNombres()+" "+us.getApellidos();
                                       idusuario = us.getIdUsuario()+"";
                                   }
                                }
                                if(seEncontroUsuario){

                                }else{
                                    progressDialog.dismiss();
                                    dialog.setTitle( "Error" )

                                            .setMessage("Usuario y/o Contraseña Erronea")
                                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialoginterface, int i) {

                                                }
                                            }).show();
                            }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Usuarios>> call, Throwable t) {

                        }
                    });
                }
            }
        });


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                      Intent intent = new Intent(getApplicationContext(),RegistrarActivity.class);
                       startActivity(intent);
                   }
               });

    }
public void callResponse(ProgressDialog progressDialog, String usuario, String IdUsuario, String Correo){
    WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);
//    final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
//    progressDialog.setMax(100);
//    progressDialog.setTitle("");
//    progressDialog.setMessage("Cargando");
//    progressDialog.show();
    service.getOfertas().enqueue(new Callback<List<Ofertas>>() {
        @Override
        public void onResponse(Call<List<Ofertas>> call, Response<List<Ofertas>> response) {
            if(response.isSuccessful()){
                ListasProductosSing.setListaOfertas(response.body());
                service.getProductos().enqueue(new Callback<List<Productos>>() {
                    @Override
                    public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                        if(response.isSuccessful()){
                            //   progressDialog.dismiss();
                            ListasProductosSing.setListaProductos(response.body());
                            String[] urls = new String[ListasProductosSing.getListaProductos().size()];

                            for(int i = 0;i<ListasProductosSing.getListaProductos().size();i++){
                                urls[i] = ListasProductosSing.getListaProductos().get(i).getUrlImagen();
                            }
                            Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
                         //   intent.putExtra("usuario",usuario);
                         //   intent.putExtra("idusuario",idusuario);
                         //   intent.putExtra("nombre",nombre);
                            ImageDownloadToSingleTon imageDownloadToSingleTon = new ImageDownloadToSingleTon(progressDialog,getApplicationContext(),intent);
                            imageDownloadToSingleTon.execute(urls);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Productos>> call, Throwable t) {

                    }
                });
            }
        }

        @Override
        public void onFailure(Call<List<Ofertas>> call, Throwable t) {

        }
    });

}

    public void callResponse(ProgressDialog progressDialog){
        WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);
        progressDialog.show();
//    final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
//    progressDialog.setMax(100);
//    progressDialog.setTitle("");
//    progressDialog.setMessage("Cargando");
//    progressDialog.show();
        service.getOfertas().enqueue(new Callback<List<Ofertas>>() {
            @Override
            public void onResponse(Call<List<Ofertas>> call, Response<List<Ofertas>> response) {
                if(response.isSuccessful()){
                    ListasProductosSing.setListaOfertas(response.body());
                    service.getProductos().enqueue(new Callback<List<Productos>>() {
                        @Override
                        public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                            if(response.isSuccessful()){
                                //   progressDialog.dismiss();
                                ListasProductosSing.setListaProductos(response.body());
                                String[] urls = new String[ListasProductosSing.getListaProductos().size()];

                                for(int i = 0;i<ListasProductosSing.getListaProductos().size();i++){
                                    urls[i] = ListasProductosSing.getListaProductos().get(i).getUrlImagen();
                                }
                                Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
                                //   intent.putExtra("usuario",usuario);
                                //   intent.putExtra("idusuario",idusuario);
                                //   intent.putExtra("nombre",nombre);
                                ImageDownloadToSingleTon imageDownloadToSingleTon = new ImageDownloadToSingleTon(progressDialog,getApplicationContext(),intent);
                                imageDownloadToSingleTon.execute(urls);

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Productos>> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Ofertas>> call, Throwable t) {

            }
        });

    }
    public EditText getEdit(int i){
        return (EditText)findViewById(i);
    }
    public TextView getTextView(int i){
        return (TextView)findViewById(i);
    }

    public TextInputLayout getTextInputLayout(int i){
        return (TextInputLayout)findViewById(i);
    }

    public Button getButton(int i){
        return (Button)findViewById(i);
    }

}
