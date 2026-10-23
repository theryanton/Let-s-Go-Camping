package edu.usc.csci310.project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class EntranceFeePass {
    public EntranceFeePass() {
    }

    public EntranceFeePass(double cost, String description, String title) {
        this.cost = cost;
        this.description = description;
        this.title = title;
    }

    @JsonProperty("cost")
    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @JsonProperty("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("title")
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}