package com.annhienktuit.lorempicsum.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.annhienktuit.lorempicsum.databinding.ActivityHomeBinding;
import com.annhienktuit.lorempicsum.models.Photo;
import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity implements HomeView{

    ActivityHomeBinding binding;

    HomePresenter presenter;

    ImageView imgRandom;

    RecyclerView recyclerViewPhotoList;

    Button btnShare;

    private AlbumListAdapter albumListAdapter;

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
        btnShare = binding.btnShare;
        handleClickEvent();
        showLoadingIndicator();
        presenter = new HomePresenterImpl(this);
        presenter.getPhoto();
        albumListAdapter = new AlbumListAdapter(this);
        recyclerViewPhotoList = binding.recyclerViewAlbumList;
        recyclerViewPhotoList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerViewPhotoList.setAdapter(albumListAdapter);
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
                albumListAdapter = null;
                albumListAdapter = new AlbumListAdapter(getApplicationContext());
                recyclerViewPhotoList.setAdapter(albumListAdapter);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sharePhoto();
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
        binding.tvAuthorUsername.setText(presenter.convertNameToUsername(authorName));
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

    @Override
    public void openBottomSheetBar() {

    }

    @Override
    public void startSharingActivity(Intent intent) {
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}