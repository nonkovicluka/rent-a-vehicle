package com.rentavehicle.repository;

import com.rentavehicle.model.VehicleImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleImageRepository extends PagingAndSortingRepository<VehicleImage, Long> {

    VehicleImage findByName(String name);

//    List<VehicleImage> findByVehicleId(Long vehicleId);

    @Query(
            "SELECT img FROM VehicleImage img " +
                    "LEFT JOIN img.vehicle v " +
                    "INNER JOIN v.agency a " +
                    "WHERE a.id = :agencyId"


    )
    List<VehicleImage> findByAgencyId(@Param("agencyId") Long agencyId);
}
