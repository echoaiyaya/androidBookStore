package com.example.ebook;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class User extends Application {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String pwd;

//    public boolean isLogin() {
//        return isLogin;
//    }

    private boolean isLogin = false;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User() {}

    public User(String firstName, String lastName, String phone, String address, String pwd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.pwd = pwd;
    }

    public boolean CheckPwd(final String phone, final String pwd) {
//        Log.i("test", phone);
//        Log.i("test", pwd);
        boolean result;
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference docRef = db.collection("users").document("27332733");
//                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if(task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            if(document.exists()) {
//                                Map<String, Object> user = document.getData();
//                                String phoneCheck = user.get("phone").toString();
//                                String pwdCheck = user.get("pwd").toString();
//                                if (phone == phoneCheck && pwd == pwdCheck) {
//                                    result = true;
//                                }
//                            } else {
//                                Log.d("test", "No such document");
//                            }
//                        } else {
//                            Log.d("test", "get failed with ", task.getException());
//                        }
//                    }
//                });
        return false;
    }

    public void login() {
        isLogin = true;
    }


    public boolean checkLogin() {
        if (isLogin == false) {
            return false;
        } else {
            return true;
        }
    }
}
