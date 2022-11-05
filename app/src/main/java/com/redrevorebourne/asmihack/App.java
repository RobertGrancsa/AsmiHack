package com.redrevorebourne.asmihack;

import org.json.JSONArray;

public class App {
    private String name;
    private String description;
    private String photoURL;
    private String numberOfPages;

    public App() {
    }

    public App(String name, String description, String photoURL, String numberOfPages) {
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
        this.numberOfPages = numberOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
