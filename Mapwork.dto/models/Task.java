package me.infomapwork.mapwork.dto.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by H.Harutyunyan on 11/24/2016.
 */

public class Task {
    private String title;
    private String description;
    private double price;
    @SerializedName("location")
    private TaskLocation location;
    private Date expiresAt;
    private String category;
    private int taskersCount;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public TaskLocation getLocation() {
        return location;
    }

    public void setLocation(TaskLocation location) {
        this.location = location;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTaskersCount() {
        return taskersCount;
    }

    public void setTaskersCount(int taskersCount) {
        this.taskersCount = taskersCount;
    }
}
