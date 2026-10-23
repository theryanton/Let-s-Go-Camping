
package edu.usc.csci310.project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {

    public Activity()
    {}

    public Activity(String id, String name) {
        this.id = id;
        this.name = name;
    }
    @JsonProperty("id")
    String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
