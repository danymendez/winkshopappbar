package com.example.palacios.winkshopappbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import Models.Clientes;
import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import Utilities.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity {


    Button registrarBtn;
    EditText edNombre,edApellido,edFechaNac,edDireccion,edTelefono,edCelular,edUsuario,edPassword;
    Spinner spinnerPais,spinnerDepartamento;
    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();
    WinkShopService service;
    Usuarios usuarios;
    Clientes clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        edNombre = getEditText(R.id.edNombre);
        edApellido = getEditText(R.id.edApellidos);
        edFechaNac = getEditText(R.id.edFechaNac);
        edDireccion = getEditText(R.id.edDireccion);
        edTelefono = getEditText(R.id.edTelefono);
        edCelular = getEditText(R.id.edCelular);
        edUsuario = getEditText(R.id.edUsuario);
        edPassword = getEditText(R.id.edPassword);
        registrarBtn = getButton(R.id.RegistrarBtn);

        registrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarEditText(edNombre,"Ingresar un Nombre")){

                }else if(validarEditText(edApellido,"Ingresar Un Apellido")){

                }else if(validarEditText(edFechaNac,"Ingresar una Fecha de Nacimiento")){

                }else if(validarEditText(edDireccion,"Ingresar una Dirección")){

                }else if(validarEditText(edTelefono,"Ingresar un Teléfono")){

                }else if(validarEditText(edCelular,"Ingresar un Celular")){

                }else if(validarEditText(edUsuario,"Ingresar Usuario")){

                }else if(validarEditText(edPassword,"Ingresar una Contraseña")){

                }else{
                     service = winkShopHelpers.retrofit.create(WinkShopService.class);

                     usuarios = new Usuarios();
                     clientes = new Clientes();

                    //Llenar información de usuario en la clase
                    usuarios.setIdUsuario(0);
                    usuarios.setUsuario(edUsuario.getText().toString().trim());
                    usuarios.setPassword(edPassword.getText().toString().trim());
                    usuarios.setNombres(edNombre.getText().toString().trim());
                    usuarios.setApellidos(edApellido.getText().toString().trim());
                    usuarios.setCreatedOn(new Date());
                    usuarios.setInactivo(false);
                    usuarios.setIdRol(2);

                    //Llenar Información de cliente
                    clientes.setIdCliente(0);
                    clientes.setNombre(usuarios.getNombres());
                    clientes.setApellido(usuarios.getApellidos());
                    clientes.setTelefono(edTelefono.getText().toString().trim());
                    clientes.setCelular(edCelular.getText().toString().trim());
                    clientes.setRazonSocial("None");
                    clientes.setDireccion(edDireccion.getText().toString().trim());
                    clientes.setTipoCliente("N");
                    clientes.setCreatedOn(new Date());
                    clientes.setInactivo(false);

                    service.getUsuario(edUsuario.getText().toString().trim()).enqueue(new Callback<List<Usuarios>>() {
                        @Override
                        public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {

                        }

                        @Override
                        public void onFailure(Call<List<Usuarios>> call, Throwable t) {

                        }
                    });

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
                                                Toast.makeText(getApplicationContext(),"Registro Exitosamente",Toast.LENGTH_LONG).show();
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
        });

    }

    public Button getButton(int id){
        return (Button)findViewById(id);
    }

    public EditText getEditText(int id){
        return (EditText)findViewById(id);
    }

    public TextView getTextView(int id){
        return (TextView)findViewById(id);
    }

    public Spinner getSpinner(int id){
        return (Spinner) findViewById(id);
    }

    public boolean validarEditText(EditText editText,String Mensaje){
        boolean estaVacio = false;
        if(TextUtils.isEmpty(editText.getText().toString().trim())){
            editText.setError(Mensaje);
            editText.requestFocus();
            estaVacio = true;
        }

        return estaVacio;

    }


}
