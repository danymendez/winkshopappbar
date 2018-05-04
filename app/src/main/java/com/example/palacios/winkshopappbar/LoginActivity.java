package com.example.palacios.winkshopappbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;

import Models.Usuarios;
import Services.WinkShopHelpers;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        WinkShopHelpers winkShopHelpers = new WinkShopHelpers();


            List<Usuarios> usuariosList = winkShopHelpers.getUsuarios();

    }
}
