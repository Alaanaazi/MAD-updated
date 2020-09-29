package com.example.madprojecttest;

public class Contacts {

    private String contact_name;
    private String phone_no;

    public Contacts(String contact_name, String phone_no) {
        this.contact_name = contact_name;
        this.phone_no = phone_no;
    }

    public String getContact() {
        return contact_name;
    }

    public void setContact(String contact) {
        this.contact_name = contact_name;
    }

    public String getNo() {
        return phone_no;
    }

    public void setNo(String phone_no) {
        this.phone_no = phone_no;
    }
}
