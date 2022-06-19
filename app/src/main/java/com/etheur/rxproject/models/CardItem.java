package com.etheur.rxproject.models;

import androidx.appcompat.app.AppCompatActivity;

public class CardItem {
    private final String title;
    private final String content;
    private final AppCompatActivity activity;

    public CardItem(String title, String content, AppCompatActivity activity){
        this.title = title;
        this.content = content;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public AppCompatActivity getActivity(){ return activity; }
}
