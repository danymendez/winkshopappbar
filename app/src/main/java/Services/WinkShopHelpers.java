package Services;

import android.app.ListActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import Models.Usuarios;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WinkShopHelpers {


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();
   public final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://contagpro.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    final WinkShopService service = retrofit.create(WinkShopService.class);




}
