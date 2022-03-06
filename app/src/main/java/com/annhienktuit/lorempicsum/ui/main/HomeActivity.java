package com.annhienktuit.lorempicsum.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.annhienktuit.lorempicsum.R;
import com.annhienktuit.lorempicsum.databinding.ActivityHomeBinding;
import com.annhienktuit.lorempicsum.models.Photo;
import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class HomeActivity extends AppCompatActivity implements HomeView{

    HomePresenterInterface presenter;

    ImageView imgRandom;

    ProgressBar indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imgRandom = binding.imgRandom;
        indicator = binding.loadingIndicator;
        showLoadingIndicator();
        presenter = new HomePresenter(this);
        presenter.getPhoto();
    }

    @Override
    public void showPhotoList() {

    }

    @Override
    public void hideRandomImage() {
        imgRandom.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRandomImage(Photo photo) {
        imgRandom.setVisibility(View.VISIBLE);
        Glide.with(this).load(photo.getImageUrl()).into(imgRandom);
    }

    @Override
    public void showLoadingIndicator() {
        indicator.setVisibility(View.VISIBLE);
        indicator.animate();
    }

    @Override
    public void hideLoadingIndicator() {
        indicator.setVisibility(View.GONE);
    }

    @Override
    public void openPhotoDetails() {

    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
    }
}