package com.annhienktuit.lorempicsum.networks;

import com.annhienktuit.lorempicsum.models.Photo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetPhotoService {

    @GET("id/{id}/info")
    Observable<Photo> getSinglePhoto(@Path("id") int id);

    @GET("v2/list")
    Observable<List<Photo>> getPhotoList();
}
