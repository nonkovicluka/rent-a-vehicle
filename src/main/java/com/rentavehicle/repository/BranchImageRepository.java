package com.rentavehicle.repository;

import com.rentavehicle.model.BranchImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchImageRepository extends JpaRepository<BranchImage, Long> {

    BranchImage findByImageSource(String name);

    @Query(
            "SELECT img FROM BranchImage img " +
                    "LEFT JOIN img.branch b " +
                    "INNER JOIN b.agency a " +
                    "WHERE a.id = :agencyId"


    )
    List<BranchImage> findByAgencyId(@Param("agencyId") Long agencyId);

}
