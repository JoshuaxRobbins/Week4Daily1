package com.example.josh.week4daily1.models;

public class LoadTracker {

    boolean isLoading;

    public LoadTracker(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
