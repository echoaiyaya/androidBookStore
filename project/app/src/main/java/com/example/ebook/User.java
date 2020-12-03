package com.example.ebook;

import android.app.Application;
import android.util.Log;

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

    public boolean CheckPwd(String phone, String pwd) {
        Log.i("test", phone);
        Log.i("test", pwd);

        if (this.phone.equals(phone)&& this.pwd.equals(pwd)) {
            return true;
        } else {
            return false;
        }
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
