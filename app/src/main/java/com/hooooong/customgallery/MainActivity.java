package com.hooooong.customgallery;


import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hooooong.customgallery.model.Photo;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private static final int REQ_GALLERY = 999;

    private TextView textView;


    public MainActivity() {
        super(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        textView =(TextView)findViewById(R.id.textView);
    }

    public void onGallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivityForResult(intent, REQ_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_GALLERY:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        ArrayList<Photo> photoList = (ArrayList<Photo>) data.getSerializableExtra("PHOTO");
                        String imagePath ="";
                        for (Photo photo: photoList) {
                            imagePath += photo.getImagePath() + ", ";
                        }

                        textView.setText(imagePath);
                    }
                }
                break;
        }
    }
}
