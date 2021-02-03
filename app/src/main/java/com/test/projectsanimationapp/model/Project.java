package com.test.projectsanimationapp.model;

import android.graphics.drawable.Drawable;

public class Project {

    private int id;
    private String name;
    private String date;
    private Drawable icon;

    public Project(int id, String name, String date, Drawable icon) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
