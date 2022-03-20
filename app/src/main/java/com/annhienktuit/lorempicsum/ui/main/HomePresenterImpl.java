package com.annhienktuit.lorempicsum.ui.main;

import android.content.Intent;
import android.util.Log;
import com.annhienktuit.lorempicsum.models.Photo;
import com.annhienktuit.lorempicsum.networks.RetrofitClient;

import java.util.Random;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomePresenterImpl implements HomePresenter {

    HomeView homeView;

    private final String TAG = "HomePresenter";

    public HomePresenterImpl(HomeView view){
        this.homeView = view;
    }

    Photo currentPhoto;

    public Photo getCurrentPhoto() {
        return currentPhoto;
    }

    @Override
    public void getPhoto() {
        homeView.showLoadingIndicator();
        getObservable().subscribeWith(getObserver());
    }

    @Override
    public String convertNameToUsername(String name) {
        name = name.replaceAll("\\s+","").toLowerCase();
        return "@" + name;
    }

    @Override
    public void sharePhoto() {
        Log.i("Nhiennha ", "Start sharing photo");
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareMessage = "Wow, I got this beautiful photo. See more at: " + currentPhoto.getImageUrl();
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Lorem Picsum");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        homeView.startSharingActivity(sharingIntent);
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
                currentPhoto = photo;
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
