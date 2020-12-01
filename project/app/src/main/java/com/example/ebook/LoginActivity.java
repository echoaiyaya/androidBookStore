package com.example.ebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText phone;
    EditText pwd;
    Button signBtn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.login_phoneInput);
        pwd = findViewById(R.id.login_passwordInput);
        signBtn = findViewById(R.id.btn_SignIn);
        user = (User) getApplication();
        signBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user.CheckPwd(phone.getText().toString(), pwd.getText().toString())) {
                    user.login();
                    showLoginResult(true);
                } else {
                    showLoginResult(false);
                }
            }
        });



    }

    private void showLoginResult(boolean result) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        if (result) {
            dialog.setTitle("Login Success");
            dialog.setPositiveButton("Comfirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
                            startActivity(intent);
                        }
                    }).show();
        } else {
            dialog.setTitle("Your accound is invalid!");
            dialog.setPositiveButton("Comfirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }

    }
}