package Services;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import Models.Usuarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WinkShopHelpers {


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://contagpro.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    List<Usuarios> usuario;

    WinkShopService service = retrofit.create(WinkShopService.class);

    public List<Usuarios> getUsuarios() {

     service.getUsuario().enqueue(new Callback<List<Usuarios>>() {
         @Override
         public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
             if(response.isSuccessful()){
                 usuario = response.body();
             }
         }

         @Override
         public void onFailure(Call<List<Usuarios>> call, Throwable t) {
usuario = null;
         }
     });
      return usuario;
    }
}
