package com.clubcard.generic.repository;

import javax.ejb.ApplicationException;

@ApplicationException(inherited = true, rollback = false)
public class EntityNotFoundException extends RuntimeException {

    private Class entityClass;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(Class entityClass, String message) {
        super(message);

        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

}