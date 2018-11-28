package com.mraon.vegekaist;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity{

    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid(){
        // 저장해둔 user id 가져옴
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        return  pref.getString("Uid", "");
    }



}
