package com.example.plasma.smartoffice.network;

import com.example.plasma.smartoffice.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();
                final HttpUrl url = originalHttpUrl.newBuilder()
                        .build();

                // Request customization: add request headers
                final Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                final Request request = requestBuilder.build();
                Response response = chain.proceed(request);
                if (response.code() == 502 || response.code() == 401) {
//                    startActivity(new Intent(MainActivity.this, Login.class));
//                    finish();
                }
                return  response;
            }
        });
        return httpClient.build();
    }

    public static SmartOfficeService getSmartOfficeService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_END_POINT + BuildConfig.API_VERSION + "/")
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SmartOfficeService service = retrofit.create(SmartOfficeService.class);
        return service;
    }
}
