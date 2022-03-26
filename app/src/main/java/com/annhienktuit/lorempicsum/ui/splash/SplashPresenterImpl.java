package com.annhienktuit.lorempicsum.ui.splash;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nhien Nguyen on 3/26/2022
 */
public class SplashPresenterImpl implements SplashPresenter{

    SplashView splashView;

    SplashPresenterImpl(SplashView view){
        this.splashView = view;
    }

    @Override
    public void startTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                splashView.navigateToHomeActivity();
            }
        }, 2250);
    }
}
