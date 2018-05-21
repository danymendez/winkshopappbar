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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Models.Clientes;
import Models.Paises;
import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import Utilities.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity {


    Button registrarBtn;
    TextInputLayout edNombre,edApellido,edFechaNac,edDireccion,edTelefono,edCelular,edUsuario,edPassword;
    Spinner spinnerPais,spinnerDepartamento;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();
    WinkShopService service;
    Usuarios usuarios;
    Clientes clientes;
    LinkedList<Paises> listaPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        edNombre = (TextInputLayout)findViewById(R.id.tlNombres);
        edApellido = getEditTextFromTextInputLayout(R.id.tlApellidos);
        edFechaNac = getEditTextFromTextInputLayout(R.id.tlFechaNac);
        edDireccion = getEditTextFromTextInputLayout(R.id.tlDireccion);
        edTelefono = getEditTextFromTextInputLayout(R.id.tlTelefono);
        edCelular = getEditTextFromTextInputLayout(R.id.tlCelular);
        edUsuario = getEditTextFromTextInputLayout(R.id.tlUsuario);
        edPassword = getEditTextFromTextInputLayout(R.id.tlPassword);
        registrarBtn = getButton(R.id.RegistrarBtn);
        spinnerPais = getSpinner(R.id.spinnerPais);

        service = winkShopHelpers.retrofit.create(WinkShopService.class);

         final ProgressDialog progressDialog = new ProgressDialog(this);
         progressDialog.setMax(100);
         progressDialog.setTitle("Pantalla Registro");
         progressDialog.setMessage("Cargando");
         progressDialog.show();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("Welcome to dear user.");



       final AlertDialog.Builder dialog = new AlertDialog.Builder(this);


        service.getPaises().enqueue(new Callback<List<Paises>>() {
            @Override
            public void onResponse(Call<List<Paises>> call, Response<List<Paises>> response) {
              if(response.isSuccessful()){

                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,response.body());
                    spinnerPais.setAdapter(arrayAdapter);
                  progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<Paises>> call, Throwable t) {

            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();

            }
        });

        edUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    service.getUsuario(edUsuario.getEditText().getText().toString().trim()).enqueue(new Callback<List<Usuarios>>() {
                        @Override
                        public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                            if(response.isSuccessful()){
                                if(response.body().size()>0){

                                    edUsuario.setError("Usuario ya Existe");
                                    edUsuario.requestFocus();
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


        registrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(validarEditText(edUsuario,"Ingresar Usuario")){

                }else if(validarEditText(edPassword,"Ingresar una Contraseña")) {

                 }else if(validarEditText(edNombre,"Ingresar un Nombre")){

                }else if(validarEditText(edApellido,"Ingresar Un Apellido")){

                }else if(validarEditText(edFechaNac,"Ingresar una Fecha de Nacimiento")){

                }else if(validarEditText(edDireccion,"Ingresar una Dirección")){

                }else if(validarEditText(edTelefono,"Ingresar un Teléfono")){

                }else if(validarEditText(edCelular,"Ingresar un Celular")){



                }else{


                     usuarios = new Usuarios();
                     clientes = new Clientes();
                    progressDialog.setMax(100);
                    progressDialog.setTitle("Pantalla Registro");
                    progressDialog.setMessage("Cargando");
                    progressDialog.show();
                    //Llenar información de usuario en la clase
                    usuarios.setIdUsuario(0);
                    usuarios.setUsuario(edUsuario.getEditText().getText().toString().trim());
                    usuarios.setPassword(edPassword.getEditText().getText().toString().trim());
                    usuarios.setNombres(edNombre.getEditText().getText().toString().trim());
                    usuarios.setApellidos(edApellido.getEditText().getText().toString().trim());
                    usuarios.setCreatedOn(new Date());
                    usuarios.setInactivo(false);
                    usuarios.setIdRol(2);

                    //Llenar Información de cliente
                    clientes.setIdCliente(0);
                    clientes.setNombre(usuarios.getNombres());
                    clientes.setApellido(usuarios.getApellidos());
                    clientes.setTelefono(edTelefono.getEditText().getText().toString().trim());
                    clientes.setCelular(edCelular.getEditText().getText().toString().trim());
                    clientes.setRazonSocial("None");
                    clientes.setDireccion(edDireccion.getEditText().getText().toString().trim());
                    clientes.setTipoCliente("N");
                    clientes.setCreatedOn(new Date());
                    clientes.setInactivo(false);

                    service.getUsuario(edUsuario.getEditText().getText().toString().trim()).enqueue(new Callback<List<Usuarios>>() {
                        @Override
                        public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                            if(response.isSuccessful()){
                                if(response.body().size()>0){
                                    progressDialog.dismiss();



                                    edUsuario.setError("Usuario ya Existe");
                                    edUsuario.requestFocus();
                                }else{
                                    service.postUsuarios(usuarios).enqueue(new Callback<Usuarios>() {
                                        @Override
                                        public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                                            if(response.isSuccessful()){

                                                usuarios = response.body();
                                                if(usuarios.getIdUsuario()!=0){
                                                    clientes.setIdUsuario(usuarios.getIdUsuario());
                                                    service.postClientes(clientes).enqueue(new Callback<Clientes>() {
                                                        @Override
                                                        public void onResponse(Call<Clientes> call, Response<Clientes> response) {
                                                            if(response.isSuccessful()){
                                                                progressDialog.dismiss();
                                                                dialog.setTitle( "WinkShop" )

                                                                        .setMessage("El registro se ha realizado exitosamente")
                                                                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialoginterface, int i) {
                                                                               // Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                                                                                finish();
                                                                            }
                                                                        }).show();

                                                            //    Toast.makeText(getApplicationContext(),"Registro Exitosamente",Toast.LENGTH_LONG).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Clientes> call, Throwable t) {

                                                        }
                                                    });
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Usuarios> call, Throwable t) {

                                        }
                                    });

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

    }

    public Button getButton(int id){
        return (Button)findViewById(id);
    }

    public EditText getEditText(int id){
        return (EditText)findViewById(id);
    }

    public TextInputLayout getEditTextFromTextInputLayout(int id){
        return (TextInputLayout)findViewById(id);
    }

    public TextView getTextView(int id){
        return (TextView)findViewById(id);
    }

    public Spinner getSpinner(int id){
        return (Spinner) findViewById(id);
    }

    public boolean validarEditText(TextInputLayout editText,String Mensaje){
        boolean estaVacio = false;
        if(TextUtils.isEmpty(editText.getEditText().getText().toString().trim())){
            editText.getEditText().setError(Mensaje);
            editText.requestFocus();
            estaVacio = true;
        }

        return estaVacio;

    }


}
