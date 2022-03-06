package com.annhienktuit.lorempicsum.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.annhienktuit.lorempicsum.R;
import com.annhienktuit.lorempicsum.databinding.ActivityHomeBinding;
import com.annhienktuit.lorempicsum.models.Photo;
import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity implements HomeView{

    HomePresenterInterface presenter;

    ImageView imgRandom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imgRandom = binding.imgRandom;
        presenter = new HomePresenter(this);
        presenter.getPhoto();
    }

    @Override
    public void showPhotoList() {

    }

    @Override
    public void showRandomImage(Photo photo) {
        Glide.with(this).load(photo.getImageUrl()).placeholder(R.drawable.ic_notfound).into(imgRandom);
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void openPhotoDetails() {

    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
    }
}