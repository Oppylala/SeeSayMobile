package com.example.user.emergencyapps;

/**
 * Created by user on 8/23/2020.
 */

public class User {
    private String Username;
    private String email;
    private String password;
    private String phone;
    private String image;

    public User() {
    }


    public User(String Username, String email, String password, String phone, String image) {
        this.Username = Username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
