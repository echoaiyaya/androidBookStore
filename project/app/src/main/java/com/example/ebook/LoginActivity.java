package com.example.ebook;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText phone;
    EditText pwd;
    Button signBtn;
    TextView registerText;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.login_phoneInput);
        pwd = findViewById(R.id.login_passwordInput);
        signBtn = findViewById(R.id.btn_SignIn);
        registerText = findViewById(R.id.login_textClick);
        user = (User) getApplication();
        signBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("users").document("27332733");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                Map<String, Object> userData = document.getData();
                                String phoneCheck = userData.get("phone").toString();
                                String pwdCheck = userData.get("pwd").toString();
                                String addressCheck = userData.get("address").toString();
                                String firtnameCheck = userData.get("firstName").toString();
                                String lastnameCheck = userData.get("lastName").toString();
//                                Log.d("test", phoneCheck);
//                                Log.d("test", pwdCheck);
//                                Log.d("test", phone.getText().toString());
//                                Log.d("test", pwd.getText().toString());
                                if (phone.getText().toString().equals(phoneCheck) && pwd.getText().toString().equals(pwdCheck)) {
                                    user.login();
                                    user.setAddress(addressCheck);
                                    user.setFirstName(firtnameCheck);
                                    user.setLastName(lastnameCheck);
                                    user.setPhone(phoneCheck);
                                    showLoginResult(true);
                                } else {
                                    showLoginResult(false);
                                }
                            } else {
                                Log.d("test", "No such document");
                                showLoginResult(false);
                            }
                        } else {
                            Log.d("test", "get failed with ", task.getException());
                            showLoginResult(false);
                        }
                    }
                });
//                if (user.CheckPwd(phone.getText().toString(), pwd.getText().toString())) {
//                    user.login();
//                    showLoginResult(true);
//                } else {
//                    showLoginResult(false);
//                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
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