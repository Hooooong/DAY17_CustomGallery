package com.hooooong.customgallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.hooooong.customgallery.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-11-17.
 */

public class GalleryUtil {

    /**
     * Gallery 이미지 반환
     *
     * @param context
     * @return
     */
    public static List<Photo> getAllPhotoPathList(final Context context) {
        ArrayList<Photo> photoList = new ArrayList<>();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_MODIFIED
        };

        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                null,
                null,
                projection[1] + " DESC");
        int columnIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()) {
            Photo photo = new Photo(cursor.getString(columnIndexData));
            photoList.add(photo);
        }
        cursor.close();

        return photoList;
    }
}