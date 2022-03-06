package com.annhienktuit.lorempicsum.ui.main;

import com.annhienktuit.lorempicsum.models.Photo;

public interface HomeView {

    void showPhotoList();

    void showRandomImage(Photo photo);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void openPhotoDetails();

    void showErrorToast(String error);

}
