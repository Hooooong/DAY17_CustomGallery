package com.hooooong.customgallery.model;

import java.io.Serializable;

/**
 * Created by Android Hong on 2017-10-07.
 */

public class Photo implements Serializable{

    private String imagePath;
    private String thumbnailPath;
    private String imageName;
    private String imageDate;

    public Photo() {
    }

    public Photo(String imagePath){
        this.imagePath = imagePath;
    }

    public Photo(String imagePath, String thumbnailPath, String imageName, String imageDate) {
        this.imagePath = imagePath;
        this.thumbnailPath = thumbnailPath;
        this.imageName = imageName;
        this.imageDate = imageDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

}
