package com.hooooong.customgallery;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        galleryAdapter = new GalleryAdapter(this);
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        galleryAdapter.setData(load());
    }

    // Content Resolver 를 통해 Image 목록을 가져온다.
    private List<String> load(){
        List<String> list = new ArrayList<>();
        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;

        String projections[] = {
                // 이미지는 Data 칼럼에 존재
                MediaStore.Images.Thumbnails.DATA
        };

        Cursor cursor = resolver.query(uri, projections, null, null, null);

        if(cursor != null ){
            while (cursor.moveToNext()){
                int index = cursor.getColumnIndex(projections[0]);
                // Uri 를 String 으로 받을 경우 String(Path) 로 받는다.
                String path = cursor.getString(index);
                list.add(path);
            }
        }
        return list;
    }

}
