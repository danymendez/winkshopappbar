package com.example.palacios.winkshopappbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Models.Usuarios;
import Services.WinkShopHelpers;
import Services.WinkShopService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button ingresarBtn;
    EditText editTextUsuario;
    EditText editTextPassword;
    List<Usuarios> usuariosList;

    WinkShopHelpers winkShopHelpers = new WinkShopHelpers();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsuario = getEdit(R.id.edTextUsuario);
        editTextPassword = getEdit(R.id.edTextPassword);
        ingresarBtn = getButton(R.id.btnIngresar);


        ingresarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WinkShopService service = winkShopHelpers.retrofit.create(WinkShopService.class);
                service.getUsuario().enqueue(new Callback<List<Usuarios>>() {
                    @Override
                    public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                        if(response.isSuccessful()){
                            List<Usuarios> listaUsuario = response.body();
                            for(Usuarios us: listaUsuario){
                                Toast.makeText(getApplicationContext(),us.getApellidos(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuarios>> call, Throwable t) {

                    }
                });
            }
        });




    }

    public EditText getEdit(int i){
        return (EditText)findViewById(i);
    }

    public Button getButton(int i){
        return (Button)findViewById(i);
    }

}
