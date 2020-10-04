package com.example.madprojecttest;

public class Alert {

    private String title_name;
    private String description_name;
    private double lattitude;
    private double longtitude;
    private String date;
    private String nic;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public boolean TitleLengthvalid(String title_name) {
        if (title_name.length()<=25){
            return true;
        }
        else {
            return false;
        }
    }
}
