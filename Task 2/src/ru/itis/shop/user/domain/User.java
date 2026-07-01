package ru.itis.shop.user.domain;

public class User {
    private String id;
    private String email;
    private String password;
    private String profileDescription;

    public User(String id, String email, String password, String profileDescription) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
    }

    public User(String email, String password, String profileDescription) {
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileDescription() {
        return profileDescription;
    }
}
