package Services;

import java.util.LinkedList;
import java.util.List;

import Models.Clientes;
import Models.Ofertas;
import Models.Paises;
import Models.Productos;
import Models.Usuarios;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WinkShopService {

    //Usuarios
    @GET("Usuarios")
    Call<List<Usuarios>> getUsuario();

    @GET("Usuarios")
    Call<List<Usuarios>> getUsuario(@Query("usuario") String usuario);

    @POST("Usuarios")
    Call<Usuarios> postUsuarios(@Body Usuarios usuarios);

    //Clientes
    @POST("Clientes")
    Call<Clientes> postClientes(@Body Clientes clientes);

    //Paises
    @GET("Paises")
    Call<List<Paises>> getPaises();

    //Productos
    @GET("Productos")
    Call<List<Productos>> getProductos();

    @GET("Productos/id")
    Call<Productos> getProductos(@Query("id") int id);

    //Ofertas
    @GET("Ofertas")
    Call<List<Ofertas>> getOfertas();

}
