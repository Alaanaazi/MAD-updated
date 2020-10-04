package com.example.madprojecttest;

public class Hotline {

    private String name;
    private Integer no;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public boolean isNovalid(Integer no){
        String value = ""+no;

        if(value.length()==9) {
            return true;
        } else {
            return false;
        }
    }
}
