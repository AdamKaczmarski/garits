package com.garits.auth;

import com.garits.user.Role;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    //private String username;
    private Integer idUser;
    private String email;
    private List<String> roles;

    public JwtResponse(String token,  Integer idUser,  String email, List<String> roles) {
        this.token = token;
        this.idUser = idUser;
        //this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    /* public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
