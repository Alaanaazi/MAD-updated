package com.example.madprojecttest;

public class PoliceStation {

    private String id;
    private String address;
    private Integer phone;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isIdValid(String id) {
        if ((id.charAt(0)=='L')&&(id.charAt(1)=='K')&&(id.charAt(2)=='P')){
            return true;
        }
        else {
            return false;
        }
    }

}
