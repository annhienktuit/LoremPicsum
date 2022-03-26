package com.annhienktuit.lorempicsum.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.annhienktuit.lorempicsum.BaseActivity;
import com.annhienktuit.lorempicsum.R;
import com.annhienktuit.lorempicsum.ui.main.HomeActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity implements SplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        presenter = new SplashPresenterImpl(this);
        presenter.startTimer();

    }

    @Override
    public void navigateToHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
    }
}