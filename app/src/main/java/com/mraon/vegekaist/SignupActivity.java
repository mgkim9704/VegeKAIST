package com.mraon.vegekaist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.helper.log.Logger;
import com.mraon.vegekaist.models.User;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends Activity {

    private DatabaseReference mDatabase;

    /**
     * 메인페이지와 가입 페이지 중 선택
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        requestMe();
    }

    private void requestMe() {
        List<String> keys = new ArrayList<>();
        keys.add("properties.nickname");
        keys.add("properties.profile_image");

        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
            // 요청 실패
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            // 세션이 닫혀 실패한 경우 -> 재로그인/토큰발급 필요
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectSigninActivity();
            }

            // 요청 성공: 사용자 정보 객체를 받음
            @Override
            public void onSuccess(MeV2Response response) {
//                Logger.d("user id : " + response.getId());
//                Logger.d("profile image: " + response.getProfileImagePath());
//
//                Log.e("user id : ", Long.toString(response.getId()));
//                Log.e("user nickname :", response.getNickname());
//                Log.e("profile image: ", response.getProfileImagePath());

                String userId = Long.toString(response.getId());
                String nickname = response.getNickname();
                String profileImg = response.getProfileImagePath();

                User user = new User(nickname, profileImg);
                mDatabase.child("users").child(userId).setValue(user);


                // user id 저장
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Uid", userId);
                editor.apply();


                redirectMainActivity();
            }
        });
    }

    protected void redirectSigninActivity() {
        final Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
    protected void redirectMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
