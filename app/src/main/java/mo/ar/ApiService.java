package mo.ar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("producto/{product_id}")
    Call<ProductoRespuesta> obtenerProductoPorId(@Path("product_id") String productId);

}
