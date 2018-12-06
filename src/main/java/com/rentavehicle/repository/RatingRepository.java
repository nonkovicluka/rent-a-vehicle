package com.rentavehicle.repository;

import com.rentavehicle.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query(
            "SELECT AVG(rt.score) FROM Rating rt WHERE"
                    + " rt.agency.id =:agencyId"


    )
    Double averageScore(@Param("agencyId") Long agencyId);

    @Query(
            "SELECT rt FROM Rating rt WHERE"
                    + " rt.agency.id =:agencyId AND rt.user.id =:userId"


    )
    List<Rating> findUserRatings(@Param("agencyId") Long agencyId, @Param("userId") Long userId);

}
