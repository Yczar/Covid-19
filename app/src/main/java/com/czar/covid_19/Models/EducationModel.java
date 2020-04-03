package com.czar.covid_19.Models;

public class EducationModel {

    String image;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    String postid;
    String title, description;

    public EducationModel() {
    }

    public EducationModel(String image, String title, String description, String postid) {
        this.image = image;
        this.postid = postid;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
