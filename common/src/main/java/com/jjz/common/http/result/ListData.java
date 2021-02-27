package com.jjz.common.http.result;


import java.util.List;

public class ListData<T> {

    private List<T> content;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;

    public List<T> getContent() {
        return this.content;
    }

    public void setContent(List<T> var1) {
        this.content = var1;
    }

    public int getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(int var1) {
        this.totalElements = var1;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int var1) {
        this.totalPages = var1;
    }

    public boolean getLast() {
        return this.last;
    }

    public void setLast(boolean var1) {
        this.last = var1;
    }

    public boolean getFirst() {
        return this.first;
    }

    public void setFirst(boolean var1) {
        this.first = var1;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public void setNumberOfElements(int var1) {
        this.numberOfElements = var1;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int var1) {
        this.size = var1;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int var1) {
        this.number = var1;
    }

    public boolean isHasNext() {
        return !this.last;
    }

}