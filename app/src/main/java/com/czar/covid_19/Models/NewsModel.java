package com.czar.covid_19.Models;

public class NewsModel {

    String image;
    String title;
    String description;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    String body;

    public NewsModel() {
    }

    public NewsModel(String image, String title, String description, String body) {
        this.image = image;
        this.title = title;
        this.body = body;
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
