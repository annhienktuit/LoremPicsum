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

import com.annhienktuit.lorempicsum.BaseActivity;
import com.annhienktuit.lorempicsum.databinding.ActivityHomeBinding;
import com.annhienktuit.lorempicsum.db.DatabaseHandler;
import com.annhienktuit.lorempicsum.models.Photo;
import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends BaseActivity implements HomeView{

    ActivityHomeBinding binding;

    HomePresenter presenter;

    ImageView imgRandom;

    RecyclerView recyclerViewPhotoList;

    Button btnShare;

    LikeButton btnLike;

    private AlbumListAdapter albumListAdapter;

    ProgressBar indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        bindView();
        handleClickEvent();
        showLoadingIndicator();
        presenter = new HomePresenterImpl(this);
        Executors.newCachedThreadPool().execute(() -> presenter.getPhoto());
        presenter.setDatabaseHandler(dbHandler);
        attachRecyclerView();
    }

    private void attachRecyclerView(){
        albumListAdapter = new AlbumListAdapter(this);
        recyclerViewPhotoList = binding.recyclerViewAlbumList;
        recyclerViewPhotoList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerViewPhotoList.setAdapter(albumListAdapter);
    }

    private void bindView() {
        imgRandom = binding.imgRandom;
        indicator = binding.loadingIndicator;
        btnShare = binding.btnShare;
        btnLike = binding.btnLike;
    }

    @Override
    public void onBackPressed() {
        //Handle not allow user back to splash screen
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

        btnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                presenter.addFavoritePhoto();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //presenter.removeFavoritePhoto();
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
    public void setFavoriteButtonStatus(boolean isLiked) {
        btnLike.setLiked(isLiked);
    }

    @Override
    public void startSharingActivity(Intent intent) {
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}