package com.annhienktuit.lorempicsum.ui.main;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.annhienktuit.lorempicsum.db.DatabaseHandler;
import com.annhienktuit.lorempicsum.models.Photo;

public interface HomePresenter {

    void getPhoto();

    String convertNameToUsername(String name);

    void sharePhoto();

    void setDatabaseHandler(DatabaseHandler dbHelper);

    void addFavoritePhoto();

    void checkFavoritePhotoStatus();

    void removeFavoritePhoto();
}
