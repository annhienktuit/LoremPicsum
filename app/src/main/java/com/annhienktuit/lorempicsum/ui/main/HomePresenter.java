package com.annhienktuit.lorempicsum.ui.main;

import android.content.Context;

import com.annhienktuit.lorempicsum.models.Photo;

public interface HomePresenter {

    void getPhoto();

    String convertNameToUsername(String name);

    void sharePhoto();
}
