package com.example.palacios.winkshopappbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import Tasks.ImageDownload;

public class ImageActivity extends AppCompatActivity {
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
img = findViewById(R.id.imageView4);
        ImageDownload imgdownload= new ImageDownload(img);

        imgdownload.execute("http://wintenten.eastus2.cloudapp.azure.com/imgapp/images2.jpg");
    }
}
