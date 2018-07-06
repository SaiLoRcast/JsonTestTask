package com.polygalov.jsontesttask;

import com.google.gson.annotations.SerializedName;

public class ApiObject {

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("description")
    private String description;

    public ApiObject(String firstName, String description) {
        this.firstName = firstName;
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
