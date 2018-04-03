package lom.lom_android.service;

import android.app.Application;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class App extends Application {

    private static Api api;
    private Retrofit retrofit;

    private DataModel data;
    private CustomerModel customer;
    private ResultModel resultModel;

    @Override
    public void onCreate() {
        super.onCreate();

        resultModel = new ResultModel();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", "auth-token")
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://5.23.55.214:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        api = retrofit.create(Api.class);
    }

    public static Api getApi() {
        return api;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    public DataModel getData() {
        return data;
    }

    public ResultModel getResultModel() {
        return resultModel;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public CustomerModel getCustomer() {
        return customer;
    }
}