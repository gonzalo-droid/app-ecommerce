package com.app.ecommerce.models;

import com.app.ecommerce.models.user_map.UserResponse;

public class User {

    private UserResponse response = null;
    final private String token;

    User(UserResponse response, String token) {
        this.response = response;
        this.token = token;
    }

    public UserResponse getResponse() {
        return response;
    }

    public void setResponse(UserResponse response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }
}
