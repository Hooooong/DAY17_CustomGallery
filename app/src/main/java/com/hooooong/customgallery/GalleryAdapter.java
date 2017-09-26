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

import java.util.List;

/**
 * Created by Android Hong on 2017-09-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {

    private Context context;
    private List<String> pathList;

    public GalleryAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<String> pathList){
        this.pathList = pathList;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String path = pathList.get(position);
        holder.setImageUri(Uri.parse(path));
        //holder.setTextDate();
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private Uri uri;
        private ImageView imageItem;
        private TextView textDate;

        public Holder(View itemView) {
            super(itemView);
            imageItem = (ImageView)itemView.findViewById(R.id.imageItem);
            textDate = (TextView)itemView.findViewById(R.id.textDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("imagePath", uri.getPath());
                    ((Activity)context).setResult(Activity.RESULT_OK, intent);
                    ((Activity) context).finish();
                }
            });
        }

        public void setImageUri(Uri uri) {
            this.uri = uri;
            imageItem.setImageURI(uri);
        }

        public void setTextDate(String date) {
            textDate.setText(date);
        }


    }
}
