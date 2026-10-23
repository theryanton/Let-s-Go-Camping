package edu.usc.csci310.project.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

// This annotation is from the Java Persistence API (JPA)
// and specifies this class is a JPA entity, meaning that
// it is mapped to a database table.
@Entity
@Table(name = "favorites_table")
public class Favorite implements Comparable<Favorite> {



    @Id
    private Long id;


    @JsonProperty("user_id")
    private Long user_id;

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    @Transient
    @JsonProperty("favorite_ratio")
    double favoriteRatio;

    public double getFavoriteRatio() {
        return this.favoriteRatio;
    }

    public void setFavoriteRatio(double ratio) {
        this.favoriteRatio = ratio;
    }

    @JsonProperty("park_id")
    String park_id;

    public String getParkId() {
        return this.park_id;
    }

    public void setParkId(String id) {
        this.park_id = id;
    }


    public Favorite() {
        this.favoriteRatio = 0;
    }

    public Favorite(Long user_id, String park_id) {
        this.user_id = user_id;
        this.park_id = park_id;
    }

    @Override
    public int compareTo(Favorite f) {
        if (this.favoriteRatio > f.favoriteRatio) {
            return 1;
        }
        else if (this.favoriteRatio < f.favoriteRatio) {
            return -1;
        }
        return 0;
    }

}
