package com.example.demo;

import lombok.Data;

import javax.swing.*;

@Data
public class Employee {
    private int id;
    private String title;
    private String givenName;
    private String familyName;
    private String institute;
    private ImageIcon img;

    public String getSurname() {
        return familyName;
    }

    public void SurName(String familyName) {
        this.familyName = familyName;
    }

    public void Name(String givenName) {
        this.givenName = givenName;
    }

    public String getName() {
        return givenName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id = id;
    }
}
