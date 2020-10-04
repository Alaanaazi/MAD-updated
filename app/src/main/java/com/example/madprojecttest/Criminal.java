package com.example.madprojecttest;

import android.widget.ImageView;

public class Criminal {

    private String  id;
    private String name;
    private String  area;
    private String  crime;
    private Integer height;
    private Integer age;
    private String pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean ArealengthisValid(String area) {
        if (area.length()<=20){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean AgeisValid(int age){
        if (age>=18 && age<=70){
            return true;
        }
        else {
            return false;
        }
    }
}
