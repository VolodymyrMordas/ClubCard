package com.clubcard.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Row<T> implements Serializable {
    private List<T> rows;
    private int total;

    public Row() {
    }

    public Row(List<T> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    public void addRow(T t){
        if(this.rows == null){
            this.rows = new ArrayList();
        }

        this.rows.add(t);
    }

    public void addRows(List<T> t){
        if(this.rows == null){
            this.rows = new ArrayList();
        }

        this.rows.addAll(t);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
