package com.example.a14.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a14.R;
import com.example.a14.adapter.PhotoAdapter;
import com.example.a14.utils.Constants;
import com.example.a14.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存登录状态
                SharedPreferencesUtil.getInstance(MainActivity.this).setLogin(false);

                //打开登录界面，关闭本界面
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //recyclerview初始化
        RecyclerView rvPhoto = findViewById(R.id.rv_photo);
        rvPhoto.setHasFixedSize(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rvPhoto.setLayoutManager(layoutManager);

        //初始化模拟图片数据
        List<String>list = new ArrayList<>();
        for (int i = 1; i < 30; i++){
            list.add(String.format("http://dev-courses-misuc.ixuea.com/detail-recyclerview/%d.jpg",i));
        }

        //创建adapter并配置
        final PhotoAdapter photoAdapter = new PhotoAdapter(this);
        photoAdapter.setData(list);
        photoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new  Intent(MainActivity.this,PhotoDetailActivity.class);
                String url = photoAdapter.getData().get(position);
                intent.putExtra(Constants.INTENT_EXTRAL_PHOTO_URL, url);
                startActivity(intent);
            }
        });
        rvPhoto.setAdapter(photoAdapter);
    }
}
