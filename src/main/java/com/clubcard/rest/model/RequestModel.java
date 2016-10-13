package com.clubcard.rest.model;

import com.clubcard.rest.ApplicationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestModel<T> implements Serializable {

    private Authorization authorization;

    private HashMap<String, List<T>> data;

    public List<T> getData(Class<T> clazz) throws ApplicationException {
        if (!data.containsKey(clazz.getSimpleName().toLowerCase())) {
            throw new ApplicationException(ApplicationException.Error.E_REQUEST_WRNG_DATA_KEY);
        }
        return data.get(clazz.getSimpleName().toLowerCase());
    }

    public void setData(HashMap<String, List<T>> data) {
        this.data = data;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public HashMap<String, List<T>> getData() {
        return data;
    }

    public void addData(T t){
        if(this.data == null){
            this.data = new HashMap();
        }

        if(!this.data.containsKey(t.getClass().getSimpleName().toLowerCase())){
            this.data.put(t.getClass().getSimpleName().toLowerCase(), new ArrayList<T>());
        }

        this.data.get(t.getClass().getSimpleName().toLowerCase()).add(t);
    }
}
