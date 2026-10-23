package edu.usc.csci310.project.repositories;

import edu.usc.csci310.project.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface FavoritesRepository extends JpaRepository<Favorite, Long> {
    @Query(value="SELECT * FROM favorites_table WHERE user_id = :user_id",nativeQuery=true)
    ArrayList<Favorite> findFavoritesByUserId(@Param("user_id") long user_id);

    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.park_id = :park_id")
    long countFavorite(@Param("park_id") String park_id);

}
