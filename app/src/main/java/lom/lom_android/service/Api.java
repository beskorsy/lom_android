package lom.lom_android.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface Api {
    @GET("/customer")
    Call<List<CustomerModel>> getCustomer(@Query("phone") String resourceName);
}
