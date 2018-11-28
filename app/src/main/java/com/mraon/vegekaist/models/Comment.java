package com.mraon.vegekaist.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {

    public String uid;
    public String author;
    public String text;

    public Comment(){
        // default
    }

    public Comment(String uid, String author, String text){
        this.uid = uid;
        this.author = author;
        this.text = text;
    }
}
