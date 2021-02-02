package com.example.sns_project;

import android.widget.EditText;

public class UserInfo {
    private String name;
    private String phoneNumber;
    private String birthDate;
    private String address;
    private String phoroUrl;

    public UserInfo(String name, String phoneNumber, String birthDate, String address, String phoroUrl){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.phoroUrl = phoroUrl;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getphoneNumber(){
        return this.phoneNumber;
    }
    public void setphoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getbirthDate(){
        return this.birthDate;
    }
    public void setbirthDate(String birthDate){
        this.birthDate = birthDate;
    }

    public String getaddress(){
        return this.address;
    }
    public void setaddress(String address){
        this.address = address;
    }

    public String getphoroUrl(){
        return this.phoroUrl;
    }
    public void setphoroUrl(String phoroUrl){
        this.phoroUrl = phoroUrl;
    }


}
