package com.clubcard.rest.model;

import java.io.Serializable;

public class PutModel implements Serializable {
    private String name;
    private String value;
    private long pk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return "PutModel{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", pk=" + pk +
                '}';
    }
}
