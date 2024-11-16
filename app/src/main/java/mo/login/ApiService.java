package mo.login;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("/users")
    Call<ResponseModel> createUser(@Body User user);

    @POST("/login")
    Call<ResponseModel> loginUser(@Body ConsultaLogin loginRequest);
}
