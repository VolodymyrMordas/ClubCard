package com.clubcard.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by volodymyrmordas on 1/6/16.
 */
public class Authorization implements Serializable{
    @JsonProperty(value = "auth_key")
    private String authKey;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
