package com.annhienktuit.lorempicsum.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.annhienktuit.lorempicsum.R;
import com.annhienktuit.lorempicsum.databinding.ActivityHomeBinding;
import com.annhienktuit.lorempicsum.models.Photo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class HomeActivity extends AppCompatActivity implements HomeView{

    ActivityHomeBinding binding;

    HomePresenterInterface presenter;

    ImageView imgRandom;

    ProgressBar indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.WHITE);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imgRandom = binding.imgRandom;
        indicator = binding.loadingIndicator;
        handleClickEvent();
        showLoadingIndicator();
        presenter = new HomePresenter(this);
        presenter.getPhoto();
    }

    private void handleClickEvent() {
        binding.imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRotateAnimation(binding.btnReload);
                presenter.getPhoto();
            }
        });
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
        Glide.with(this)
                .load(photo.getImageUrl())
                .into(imgRandom);
    }

    @Override
    public void showLoadingIndicator() {
        indicator.setVisibility(View.VISIBLE);
        indicator.animate();
    }

    @Override
    public void hideLoadingIndicator() {
        indicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openPhotoDetails() {

    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAuthorName(String authorName) {
        binding.tvAuthor.setText(authorName);
    }

    @Override
    public void startRotateAnimation(View v) {
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setDuration(2000);
        v.setAnimation(rotateAnimation);
    }

    @Override
    public void stopRotateAnimation(View v) {
        v.clearAnimation();
    }
}