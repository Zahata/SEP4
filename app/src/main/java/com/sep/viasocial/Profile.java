package com.sep.viasocial;

import android.net.Uri;

import java.net.URI;

public class Profile {

    public Uri photoURL;
    public String fullName;
    public String address;
    public String phone;
    public String studyProgramme;
    public String interests;


    public Profile(){}

    public Profile(Uri photo, String name, String address,String phone, String programme, String interests){
        this.photoURL = photo;
        this.fullName = name;
        this.address = address;
        this.phone = phone;
        this.studyProgramme = programme;
        this.interests = interests;
    }

    public Uri getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(Uri photoURL) {
        this.photoURL = photoURL;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStudyProgramme() {
        return studyProgramme;
    }

    public void setStudyProgramme(String studyProgramme) {
        this.studyProgramme = studyProgramme;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}
