package com.example.madprojecttest;

public class Alert {

    private String title_name;
    private String description_name;
    private double lattitude;
    private double longtitude;

    public String getTitle() {
        return title_name;
    }

    public void setTitle(String title_name) {
        this.title_name = title_name;
    }

    public String getDescription() {
        return description_name;
    }

    public void setDescription(String description_name) {
        this.description_name = description_name;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}