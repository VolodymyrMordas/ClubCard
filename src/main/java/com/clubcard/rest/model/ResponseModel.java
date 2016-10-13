package com.clubcard.rest.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;

@JsonPropertyOrder({"status", "data", "message"})
public class ResponseModel<T> implements Serializable {

    public enum Status {success, error;}

    public ResponseModel(Status status) {
        this.status = status;
    }

    public ResponseModel() {
    }

    private Status status;
    private String text;
    private String message;
    private Integer code;

    private HashMap<String, T> data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HashMap<String, T> getData() {
        return data;
    }

    public void setData(final Class cls,final T t) {
        this.data = new HashMap<String, T>() {
            {
                put(cls.getSimpleName().toLowerCase(), t);
            }
        };
    }
}