package com.example.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.registerBtn);





        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText fNameInput = findViewById(R.id.registerFN);
                EditText lNameInput = findViewById(R.id.registerLN);
                EditText phoneInput = findViewById(R.id.registerPhone);
                EditText pwdInput = findViewById(R.id.registerPassword);
                EditText addressInput = findViewById(R.id.registerAddress);

                String fName = fNameInput.getText().toString();
                String lName = lNameInput.getText().toString();
                String phone = phoneInput.getText().toString();
                String pwd = pwdInput.getText().toString();
                String address = addressInput.getText().toString();

                Map<String, Object> user = new HashMap<>();
                user.put("firstName", fName);
                user.put("lastName", lName);
                user.put("pwd", pwd);
                user.put("phone", phone);
                user.put("address", address);

                //need a validate to check only one phone

                db.collection("users").document(phone).set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });




    }
}