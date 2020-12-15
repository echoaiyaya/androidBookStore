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
    ImageView bookShelfBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        bookShelfBtn = findViewById(R.id.bookShelfBtn);
        bookShopBtn = findViewById(R.id.bookShopBtn);
        userBtn = findViewById(R.id.accountBtn);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                user = (User) getApplication();
                Log.i("test", String.valueOf(user.checkLogin()));
                if(user.checkLogin()) {
                    Intent userInfo = new Intent(CompanyActivity.this, UserInfoActivity.class);
                    startActivity(userInfo);
                } else {
                    Intent userInfo = new Intent(CompanyActivity.this, LoginActivity.class);
                    startActivity(userInfo);
                }

            }
        });
        bookShopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookShopPage = new Intent(CompanyActivity.this, BookShop.class);
                startActivity(bookShopPage);
            }
        });

        bookShelfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookShelf = new Intent(CompanyActivity.this, MainActivity.class);
                startActivity(bookShelf);
            }
        });

    }
}