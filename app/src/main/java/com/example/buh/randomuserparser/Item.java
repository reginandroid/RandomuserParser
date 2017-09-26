package com.example.buh.randomuserparser;

/**
 * Created by Buh on 22.09.2017.
 */

public class Item {

    private String name;

    private String gender;
    private String location;
    private String email;
    private String phone;
    private String nat;
    private String image;
    private  String surname;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Item(String name, String surname, String image) {

        this.name = name;
        this.surname = surname;
        this.image = image;
    }

    public Item(String name, String surname,  String gender, String location, String email, String phone, String nat, String image) {

        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.nat = nat;
        this.image = image;
    }



}
