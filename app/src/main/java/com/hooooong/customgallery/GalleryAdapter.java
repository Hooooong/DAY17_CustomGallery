package com.hooooong.customgallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hooooong.customgallery.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-09-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {

    private Context context;
    private List<Photo> photoList = new ArrayList<>();
    private ArrayList<Photo> selectPhotoList;
    private GalleryListener galleryListener;

    /**
     * 생성자
     *
     * @param context
     */
    public GalleryAdapter(Context context) {
        this.context = context;
        this.selectPhotoList = new ArrayList<>();
        this.galleryListener = (GalleryListener) context;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    /**
     * PhotoList 반환
     *
     * @return
     */
    public List<Photo> getPhotoList() {
        return photoList;
    }

    /**
     * 선택된 PhotoList 반환
     *
     * @return
     */
    public ArrayList<Photo> getSelectPhotoList() {
        return selectPhotoList;
    }

    /**
     * 선택한 Photo 추가하기
     *
     * @param photo
     */
    public void addSelectPhotoList(Photo photo) {
        if (selectPhotoList.size() < 10) {
            // 10 보다 작을 때
            selectPhotoList.add(photo);
            notifyItemChanged(photoList.indexOf(photo));
            galleryListener.changeView(selectPhotoList.size());
        } else {
            // 10보다 클 때
            galleryListener.selectError();
        }
    }

    /**
     * 선택한 Photo 지우기
     *
     * @param photo
     */
    public void removeSelectPhotoList(Photo photo) {
        selectPhotoList.remove(photo);
        notifyItemChanged(photoList.indexOf(photo));
        for (Photo item: selectPhotoList) {
            notifyItemChanged(photoList.indexOf(item));
        }
        galleryListener.changeView(selectPhotoList.size());
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Photo photo = photoList.get(position);
        holder.setImageView(photo.getImagePath()) ;
        holder.setPosition(position);

        if(selectPhotoList.contains(photo)){
            holder.setLayout(View.VISIBLE);
            holder.setTextNumber(selectPhotoList.indexOf(photo)+1);
        }else{
            holder.setLayout(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        //return pathList.size();
        return photoList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private ImageView imgPhoto;
        private RelativeLayout layoutSelect;
        private TextView textNumber;

        Holder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
            layoutSelect = (RelativeLayout) itemView.findViewById(R.id.layoutSelect);
            textNumber = (TextView) itemView.findViewById(R.id.textNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (galleryListener != null) {
                        galleryListener.PhotoClick(position);
                    }
                }
            });
        }

        void setTextNumber(int number) {
            textNumber.setText(number + "");
        }

        void setImageView(String path) {
            Glide.with(context)
                    .load(path)
                    .centerCrop()
                    .into(imgPhoto);
        }

        void setLayout(int layout) {
            if (layout == View.VISIBLE) {
                layoutSelect.setVisibility(View.VISIBLE);
            } else {
                layoutSelect.setVisibility(View.INVISIBLE);
            }
        }

        void setPosition(int position) {
            this.position = position;
        }
    }
}
