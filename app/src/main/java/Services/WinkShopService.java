package Services;

import java.util.LinkedList;
import java.util.List;

import Models.Clientes;
import Models.Usuarios;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WinkShopService {

    //Usuarios
    @GET("Usuarios")
    Call<List<Usuarios>> getUsuario();

    @GET("Usuarios")
    Call<List<Usuarios>> getUsuario(@Field("usuario") String usuario);

    @POST("Usuarios")
    Call<Usuarios> postUsuarios(@Body Usuarios usuarios);

    //Clientes
    @POST("Clientes")
    Call<Clientes> postClientes(@Body Clientes clientes);



}
