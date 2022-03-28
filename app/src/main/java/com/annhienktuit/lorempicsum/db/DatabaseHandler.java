package com.annhienktuit.lorempicsum.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.annhienktuit.lorempicsum.models.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhien Nguyen on 3/27/2022
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FavoriteManager.db";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Favorite_Table = "CREATE TABLE "
                + FavoritePhotoContract.FavoriteEntry.TABLE_NAME + " ("
                + FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_PHOTO_ID + " TEXT PRIMARY KEY,"
                + FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_URL + " TEXT,"
                + FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_DOWNLOAD + " TEXT)";
        db.execSQL(Create_Favorite_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String Drop_Favorite_Table = "DROP TABLE IF EXISTS " + FavoritePhotoContract.FavoriteEntry.TABLE_NAME;
        db.execSQL(Drop_Favorite_Table);
        onCreate(db);
    }

    public void addFavoritePhoto(Photo photo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_PHOTO_ID, photo.getId());
        values.put(FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_URL, photo.getUrl());
        values.put(FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_DOWNLOAD, photo.getImageUrl());
        db.insert(FavoritePhotoContract.FavoriteEntry.TABLE_NAME, null, values);
        db.close();
    }

    public void removeFavoritePhoto(Photo photo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                FavoritePhotoContract.FavoriteEntry.TABLE_NAME,
                FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_PHOTO_ID + "= ?",
                new String[]{photo.getId()});
        db.close();
    }

    public List<Photo> getAllFavoritePhoto() {
        List<Photo> photoList = new ArrayList<Photo>();
        String query = "SELECT * FROM " + FavoritePhotoContract.FavoriteEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Photo photoItem = new Photo(
                    cursor.getString(0),
                    null,
                    0,
                    0,
                    cursor.getString(1),
                    cursor.getString(2)
            );
            photoList.add(photoItem);
            cursor.moveToNext();
        }
        return photoList;
    }

    public boolean isPhotoFavorite(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "
                + FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_PHOTO_ID + " FROM "
                + FavoritePhotoContract.FavoriteEntry.TABLE_NAME
                + " WHERE " + FavoritePhotoContract.FavoriteEntry.COLUMN_NAME_PHOTO_ID + "="
                + id;
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount() > 0;
    }

    public void clearDatabase(String DATABASE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + DATABASE_NAME);
    }

}
