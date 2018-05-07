package com.example.palacios.winkshopappbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button ingresarBtn;
    EditText editTextUsuario;
    EditText editTextPassword;
    TextView tvwRegistrarse;
    List<Usuarios> usuariosList;

    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = getEdit(R.id.edTextUsuario);
        editTextPassword = getEdit(R.id.edTextPassword);
        ingresarBtn = getButton(R.id.btnIngresar);
        tvwRegistrarse= getTextView(R.id.tvwRegistrarse);



        ingresarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usuario = editTextUsuario.getText().toString();
                final String password = editTextPassword.getText().toString();

                if(TextUtils.isEmpty(usuario)){

                    editTextUsuario.setError("Ingresar Usuario");
                    editTextUsuario.requestFocus();

                }else if(TextUtils.isEmpty(password)){

                    editTextPassword.setError("Ingresar Contraseña");
                    editTextPassword.requestFocus();
                }else {


                    WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);

                    service.getUsuario().enqueue(new Callback<List<Usuarios>>() {



                        @Override
                        public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                            if (response.isSuccessful()) {
                                boolean seEncontroUsuario = false;
                                List<Usuarios> listaUsuario = response.body();
                                for (Usuarios us : listaUsuario) {
                                   if(us.getUsuario().equals(usuario) && us.getPassword().equals(password)){
                                      seEncontroUsuario = true;
                                   }
                                }
                                if(seEncontroUsuario){
                                    Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Usuario y/o Contraseña Erronea",Toast.LENGTH_SHORT).show();
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


               tvwRegistrarse.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(getApplicationContext(),RegistrarActivity.class);
                       startActivity(intent);
                   }
               });

    }

    public EditText getEdit(int i){
        return (EditText)findViewById(i);
    }
    public TextView getTextView(int i){
        return (TextView)findViewById(i);
    }

    public Button getButton(int i){
        return (Button)findViewById(i);
    }

}
