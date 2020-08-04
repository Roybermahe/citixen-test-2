package com.example.newsapp.data;

import java.util.Date;

public class newsModel {
    public source source;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public Date publishedAt;
    public String content;

    public newsModel(source source,String author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public newsModel() {}
}