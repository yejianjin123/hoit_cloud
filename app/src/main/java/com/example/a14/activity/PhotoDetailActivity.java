package com.example.a14.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.a14.R;
import com.example.a14.utils.Constants;
import com.example.a14.utils.ImageUtil;

public class PhotoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        //接收传递过来的图片url，并显示
        String url = getIntent().getStringExtra(Constants.INTENT_EXTRAL_PHOTO_URL);
        ImageView pvPhotoDetail = findViewById(R.id.pv_photo_detail);
        ImageUtil.show(this, pvPhotoDetail, url);
    }
}
