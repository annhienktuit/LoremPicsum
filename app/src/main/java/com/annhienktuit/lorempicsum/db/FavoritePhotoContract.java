package com.annhienktuit.lorempicsum.db;

import android.provider.BaseColumns;

/**
 * Created by Nhien Nguyen on 3/27/2022
 */
public final class FavoritePhotoContract {

    private FavoritePhotoContract(){}

    public static class FavoriteEntry implements BaseColumns{
        public static final String TABLE_NAME = "FavoritePhoto";
        public static final String COLUMN_NAME_PHOTO_ID = "id";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_DOWNLOAD = "download_url";
    }

}
