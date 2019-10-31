package com.twisac.apps.amazin.networking;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static final String BASE_URL= "http://amazin.twisacdevelopers.com";
    private static Retrofit retrofit = null;


    /**
    *Desciption : Retrofit client
    *@param  : void
    *@return retrofit
    *@throws null
    */
    public static Retrofit getClient() {
        retrofit=null;
        if (retrofit==null) {
            //Provide Logging on android Monitor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //Setup the retrofit HTTP client
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
