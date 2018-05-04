package Services;

import java.util.List;

import Models.Usuarios;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WinkShopService {
    @GET("Usuarios")
    Call<List<Usuarios>> getUsuario();

}
