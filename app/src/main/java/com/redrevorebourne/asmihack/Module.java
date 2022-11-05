package com.redrevorebourne.asmihack;

public class Module {
    private String name;
    private String rating;
    private String ratingNumber;
    private String photoUrl;
    private String description;
    private String downloadNumber;

    public Module() {
    }

    public Module(String name, String rating, String ratingNumber, String photoUrl, String description, String downloadNumber) {
        this.name = name;
        this.rating = rating;
        this.ratingNumber = ratingNumber;
        this.photoUrl = photoUrl;
        this.description = description;
        this.downloadNumber = downloadNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(String ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(String downloadNumber) {
        this.downloadNumber = downloadNumber;
    }
}
