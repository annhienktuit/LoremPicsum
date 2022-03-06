package com.annhienktuit.lorempicsum.networks;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "https://picsum.photos/";

    private Retrofit retrofit;

    public RetrofitClient() {
    }

    public GetPhotoService getPhotoService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(GetPhotoService.class);
    }



}
