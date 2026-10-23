package edu.usc.csci310.project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Park {

    @JsonProperty("id")
    private String id;
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("url")
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("fullName")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("parkCode")
    private String parkCode;

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    @JsonProperty("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("latitude")
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latLong")
    private String latLong;

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    @JsonProperty("activities")
    private List<Activity> activitiesList;

    public List<Activity> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<Activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @JsonProperty("states")
    private String states;

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    @JsonProperty("entranceFees")
    private List<EntranceFeePass> fees;


    public List<EntranceFeePass> getFees() {
        return fees;
    }

    public void setFees(List<EntranceFeePass> fees) {
        this.fees = fees;
    }

    @JsonProperty("entrancePasses")
    private List<EntranceFeePass> passes;


    public List<EntranceFeePass> getPasses() {
        return passes;
    }

    public void setPasses(List<EntranceFeePass> passes) {
        this.passes = passes;
    }

    @JsonProperty("directionsInfo")
    private String directionsInfo;


    public String getDirectionsInfo() {
        return directionsInfo;
    }

    public void setDirectionsInfo(String directionsInfo) {
        this.directionsInfo = directionsInfo;
    }

    @JsonProperty("directionsUrl")
    private String directionsUrl;


    public String getDirectionsUrl() {
        return directionsUrl;
    }

    public void setDirectionsUrl(String directionsUrl) {
        this.directionsUrl = directionsUrl;
    }

    public Park() {

    }

    public Park(String id, String url, String fullName, String name, String parkCode, String description,
                double latitude, double longitude, String latLong, List<Activity> activitiesList,
                String states, List<EntranceFeePass> fees, List<EntranceFeePass> passes, String directionsInfo, String directionsUrl)
    {
        this.id = id;
        this.url = url;
        this.fullName = fullName;
        this.name = name;
        this.parkCode = parkCode;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latLong = latLong;
        this.activitiesList = activitiesList;
        this.states = states;
        this.fees = fees;
        this.passes = passes;
        this.directionsInfo = directionsInfo;
        this.directionsUrl = directionsUrl;
    }


}