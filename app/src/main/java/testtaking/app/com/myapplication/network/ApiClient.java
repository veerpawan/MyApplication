package testtaking.app.com.myapplication.network;


import android.provider.SyncStateContract;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

   //public static final String BASE_URL = "https://www.selfenabler.com/";
    //http://192.168.0.30:8081/StudentAssessment2/
    public static final String BASE_URL = "http://192.168.0.43:8081/StudentAssessment2/";
    //public static final String BASE_URL = "http://192.168.0.103:8080/StudentAssessment2/";
    //public static final String BASE_URL2 = "https://www.selfenabler.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }


  /*  public static Retrofit getClient2() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)

                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
*/

}
