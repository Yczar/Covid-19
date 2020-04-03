package com.czar.covid_19.Models;

public class TrendingModel {

    String image, postid;
    String title, description, body;

    public String getPostid() {
        return postid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public TrendingModel() {
    }

    public TrendingModel(String image, String title, String description, String postid, String body) {
        this.image = image;
        this.title = title;
        this.body =body;
        this.postid = postid;
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
