package com.redrevorebourne.asmihack;

import org.json.JSONArray;

import java.util.List;

public class App {
    private String name;
    private String description;
    private String photoURL;
    private String numberOfPages;
    private List<Integer> idNumbers;

    public App() {
    }

    public App(String name, String description, String photoURL, String numberOfPages, List<Integer> idNumbers) {
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
        this.numberOfPages = numberOfPages;
        this.idNumbers = idNumbers;
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

    public List<Integer> getIdNumbers() {
        return idNumbers;
    }

    public void setIdNumbers(List<Integer> idNumbers) {
        this.idNumbers = idNumbers;
    }
}
