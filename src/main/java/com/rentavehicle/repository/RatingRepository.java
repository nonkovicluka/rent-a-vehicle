package com.rentavehicle.repository;

import com.rentavehicle.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query(
            "SELECT AVG(rt.score) FROM Rating rt WHERE"
                    + " rt.agency.id =:agencyId"


    )
    Double averageScore(@Param("agencyId") Long agencyId);

}
