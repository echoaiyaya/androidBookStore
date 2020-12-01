package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {

    String firstName;
    String lastName;
    String phone;
    String address;
    Button gbBtn;

    TextView fn;
    TextView ln;
    TextView ph;
    TextView ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        User user;
        user = (User) getApplication();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        phone = user.getPhone();
        address = user.getAddress();

        fn = findViewById(R.id.userFN);
        ln = findViewById(R.id.userLN);
        ph = findViewById(R.id.userPhone);
        ad = findViewById(R.id.userAddress);
        gbBtn = findViewById(R.id.userBack);

        fn.setText(firstName);
        ln.setText(lastName);
        ph.setText(phone);
        ad.setText(address);

        gbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}