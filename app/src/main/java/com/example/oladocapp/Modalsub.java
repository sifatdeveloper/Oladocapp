package com.example.oladocapp;

import android.net.Uri;

public class Modalsub {
    String image, age, Name, Email, password, Gender, profession, Education;
    int id;
    public Modalsub() {
    }

    public Modalsub(String image, String age, String name, String email, String password, String gender, String profession, String education,int id) {
        this.image = image;
        this.age = age;
        Name = name;
        Email = email;
        this.password = password;
        Gender = gender;
        this.profession = profession;
        Education = education;
        id=id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
