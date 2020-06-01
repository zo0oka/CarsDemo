package com.zo0okadev.carsdemo.remote;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zo0okaDev (https://github.com/zo0oka)
 * On 01 Jun, 2020.
 * Have a nice day!
 */
public class ApiClient {


    private static final String BASE_URL = "http://demo1286023.mockable.io/api/v1/";
    private static ApiService instance = null;

    public static ApiService getInstance() {

        if (instance == null) {

//            int cacheSize = 30 * 1024 * 1024; // 10 MB
//            Cache cache = new Cache(Context.getCacheDir(), cacheSize);

            // For logging
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

            // Building OkHttp client
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            return chain.proceed(chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Accept", "application/json")
                                    .build());
                        }
                    })
//                    .cache(cache)
                    .callTimeout(0, TimeUnit.MILLISECONDS)
                    .readTimeout(0, TimeUnit.MILLISECONDS)
                    .writeTimeout(0, TimeUnit.MILLISECONDS)
                    .connectTimeout(0, TimeUnit.MILLISECONDS)
                    .build();

            // Retrofit Builder
            Retrofit.Builder builder =
                    new Retrofit
                            .Builder()
                            .baseUrl(BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create());

            instance = builder.build().create(ApiService.class);
        }
        return instance;
    }
}