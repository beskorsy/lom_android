package lom.lom_android.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface Api {
    @GET("/api/data?format=json")
    Call<DataModel> getData();

    @GET("/customer?format=json")
    Call<List<CustomerModel>> getCustomer(@Query("phone") String phone);

    @POST("/requestadd?format=json")
    Call<List<Object>> sendRequest(@Query("phone") String phone,
                                        @Query("loader") Boolean loader,
                                        @Query("'cutter'") Boolean cutter,
                                        @Query("calculatedInPlace") Boolean calculatedInPlace,
                                        @Query("discount") String discount,
                                        @Query("locality") String locality,
                                        @Query("address") String address,
                                        @Query("scrapyard") String scrapyard,
                                        @Query("distantce") String distantce,
                                        @Query("transport") String transport,
                                        @Query("cost") String cost,
                                        @Query("tonn") String tonn,
                                        @Query("data") String data,
                                        @Query("comment") String comment
                                        );
}
