package com.annhienktuit.lorempicsum.ui.main;

import android.content.Context;
import android.util.Log;
import com.annhienktuit.lorempicsum.models.Photo;
import com.annhienktuit.lorempicsum.networks.RetrofitClient;
import com.bumptech.glide.Glide;

import java.util.Random;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomePresenter implements HomePresenterInterface{

    HomeView homeView;

    private final String TAG = "HomePresenter";

    public HomePresenter(HomeView view){
        this.homeView = view;
    }

    @Override
    public void getPhoto() {
        homeView.showLoadingIndicator();
        getObservable().subscribeWith(getObserver());
    }

    public Observable<Photo> getObservable(){
        int id = randomNumber();
        Log.i("Nhiennha ", "ID " + id);
        return new RetrofitClient().getPhotoService().getSinglePhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Photo> getObserver(){
        return new DisposableObserver<Photo>() {
            @Override
            public void onNext(@NonNull Photo photo) {
                Log.i(TAG, photo.getImageUrl());
                homeView.setAuthorName(photo.getAuthor());
                homeView.hideLoadingIndicator();
                homeView.showRandomImage(photo);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, e.toString());
                homeView.showErrorToast("Error while getting photo, please try again :(");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "Completed");
            }
        };
    }

    public static int randomNumber() {
        Random ran = new Random();
        return ran.nextInt(1000);
    }
}
