package com.example.madprojecttest;

public class News {

    private String title_name;
    private String description_name;

    public News(String title_name, String description_name) {
        this.title_name = title_name;
        this.description_name = description_name;
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


}
