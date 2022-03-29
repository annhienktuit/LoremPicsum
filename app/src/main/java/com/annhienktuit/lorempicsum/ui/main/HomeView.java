package com.annhienktuit.lorempicsum.ui.main;

import android.content.Intent;
import android.view.View;

import com.annhienktuit.lorempicsum.models.Photo;

public interface HomeView {

    void showPhotoList();

    void hideRandomImage();

    void showRandomImage(Photo photo);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void openPhotoDetails();

    void showErrorToast(String error);

    void setAuthorName(String authorName);

    void startRotateAnimation(View v);

    void stopRotateAnimation(View v);

    void openBottomSheetBar();

    void setFavoriteButtonStatus(boolean isLiked);

    void startSharingActivity(Intent intent);

    void startFavoriteActivity();

}
