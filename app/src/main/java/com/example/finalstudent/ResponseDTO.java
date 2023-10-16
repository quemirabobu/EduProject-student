package com.example.finalstudent;

import java.util.List;

public class ResponseDTO<T> {

    private T item;
    private int statusCode;
    private String errorMessage;
    private List<T> items;


    public ResponseDTO(T item, int statusCode, String errorMessage, List<T> items) {
        this.item = item;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.items = items;
    }


    @Override
    public String toString() {
        return "ResponseDTO{" +
                "item=" + item +
                ", statusCode=" + statusCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", items=" + items +
                '}';
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
