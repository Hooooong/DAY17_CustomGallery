package com.hooooong.customgallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hooooong.customgallery.model.Photo;

import java.util.List;

/**
 * Created by Android Hong on 2017-09-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {

    private Context context;
    //private List<String> pathList;
    private List<Photo> photoList;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    /*public void setData(List<String> pathList){
        this.pathList = pathList;
        notifyDataSetChanged();
    }*/

    public void setData(List<Photo> pathList){
        this.photoList = pathList;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Photo photo = photoList.get(position);
        holder.setTextName(photo.getImageName());
        holder.setTextDate(photo.getImageDate());
        holder.setImageUri(photo.getImagePath(), photo.getThumbnailPath());

        /*String path = pathList.get(position);
        holder.setImageUri(Uri.parse(path));*/
        //holder.setTextDate();
    }

    @Override
    public int getItemCount() {
        //return pathList.size();
        return photoList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private Uri imageUri;
        private ImageView imageItem;
        private TextView  textName, textDate;

        public Holder(View itemView) {
            super(itemView);
            imageItem = (ImageView)itemView.findViewById(R.id.imageItem);
            textName = (TextView)itemView.findViewById(R.id.textName);
            textDate = (TextView)itemView.findViewById(R.id.textDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("imagePath", imageUri.getPath());
                    ((Activity)context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }
            });
        }

        public void setImageUri(String imagePath, String thumbnailPath){
            this.imageUri = Uri.parse(imagePath);
            if(thumbnailPath != null){
                imageItem.setImageURI(Uri.parse(thumbnailPath));
            }else{
                imageItem.setImageURI(null);
            }
        }


        public void setTextDate(String date) {
            textDate.setText(date);
        }

        public void setTextName(String name){
            textName.setText(name);
        }


    }
}
