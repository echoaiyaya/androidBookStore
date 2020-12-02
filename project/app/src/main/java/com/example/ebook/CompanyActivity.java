package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class CompanyActivity extends AppCompatActivity {

    ImageView bookShopBtn;
    ImageView userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        bookShopBtn = findViewById(R.id.bookShopBtn);
        userBtn = findViewById(R.id.accountBtn);


        bookShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookList = new Intent(CompanyActivity.this, MainActivity.class);
                startActivity(bookList);
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                user = (User) getApplication();
                if(user.checkLogin()) {
                    Intent userInfo = new Intent(CompanyActivity.this, UserInfoActivity.class);
                    startActivity(userInfo);
                } else {
                    Intent userInfo = new Intent(CompanyActivity.this, LoginActivity.class);
                    startActivity(userInfo);
                }
            }
        });
    }
}