package com.mraon.vegekaist.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    /**
     * user 등록할 때 필요한 것: 닉네임, 프로필 사진
     */
    public String nickname;
    public String profileImg;

    public User(){
        // Default
    }

    public User(String nickname, String profileImg){
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}
