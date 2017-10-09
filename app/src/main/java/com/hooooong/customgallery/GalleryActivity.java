package com.hooooong.customgallery;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hooooong.customgallery.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        galleryAdapter = new GalleryAdapter(this);
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //galleryAdapter.setData(load());
        galleryAdapter.setData(load());
    }

    // http://shygiants.github.io/android/2016/01/13/contentresolver.html
    // Content Resolver 를 통해 Image 목록을 가져온다.
    /*private List<String> load(){
        List<String> list = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String projections[] = {
                // 이미지는 Data 칼럼에 존재
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA
        };

        Cursor cursor = resolver.query(uri, projections, null, null, null);

        if(cursor != null ){
            while (cursor.moveToNext()){
                int index = cursor.getColumnIndex(projections[0]);
                // Uri 를 String 으로 받을 경우 String(Path) 로 받는다.
                String path = cursor.getString(index);

                index = cursor.getColumnIndex(projections[1]);
                // Uri 를 String 으로 받을 경우 String(Path) 로 받는다.
                String id = cursor.getString(index);
                String thumbnailPath = uriToThumbnail(id);

                Photo


                list.add(path);
            }
        }
        return list;
    }*/

    private List<Photo> load() {
        List<Photo> list = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String projections[] = {
                // 이미지는 Data 칼럼에 존재
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DATE_ADDED
        };

        Cursor cursor = resolver.query(uri, projections, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(projections[0]);
                // Uri 를 String 으로 받을 경우 String(Path) 로 받는다.
                String id = cursor.getString(index);

                index = cursor.getColumnIndex(projections[1]);
                // Uri 를 String 으로 받을 경우 String(Path) 로 받는다.
                String imagePath = cursor.getString(index);

                String thumbnailPath = uriToThumbnail(id);

                index = cursor.getColumnIndex(projections[2]);
                String name = cursor.getString(index);

                index = cursor.getColumnIndex(projections[3]);
                String date = cursor.getString(index);

                Photo photo = new Photo(imagePath, thumbnailPath, name, date);
                list.add(photo);
            }
        }
        return list;
    }
    // 이 경우에는 원본이미지가 너무 크기 때문에 성능상 적절하지 않다.

    private String uriToThumbnail(String imageId) {

        String thumbnailPath = null;

        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        String[] projections = {MediaStore.Images.Thumbnails.DATA};

        Cursor cursor = resolver.query(uri
                , projections
                , MediaStore.Images.Thumbnails.IMAGE_ID + "= ?"
                , new String[]{imageId},
                null);

        if (cursor == null) {

        } else if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(projections[0]);
            thumbnailPath = cursor.getString(index);
        } else {
            MediaStore.Images.Thumbnails.getThumbnail(resolver, Long.parseLong(imageId), MediaStore.Images.Thumbnails.MINI_KIND, null);
            uriToThumbnail(imageId);
        }
        return thumbnailPath;
    }
}
