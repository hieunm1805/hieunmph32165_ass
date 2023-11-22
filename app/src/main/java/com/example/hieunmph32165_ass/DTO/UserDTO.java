package com.example.hieunmph32165_ass.DTO;

public class UserDTO {
    String username, email, password, fullname;
    int id;

    public UserDTO(String username, String email, String password, String fullname, int id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.id = id;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
