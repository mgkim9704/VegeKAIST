package com.mraon.vegekaist.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {
    /**
     * 포스팅할 때 필요한 항목들: uid, 작성자 닉네임, 메뉴명, 가게명, 별점, 리뷰내용
     */
    public String uid;
    public String author;
    public String title;
    public String body;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post(){
        // default
    }

    public Post(String uid, String author, String menu, String review){
        this.uid = uid;
        this.author = author;
        this.title = menu;
        this.body = review;
    }

    // post to map
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }
}
