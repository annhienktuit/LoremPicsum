package com.annhienktuit.lorempicsum.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.annhienktuit.lorempicsum.R;

public class HomeActivity extends AppCompatActivity implements HomeView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showPhotoList() {

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
}