package com.clubcard.generic.entity;

import java.io.Serializable;

public abstract class GenericEntity<T extends Serializable> implements Identifiable<T> {

    public boolean isNew() {
        return getId() == null;
    }
}