package com.rentavehicle.repository;

import com.rentavehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByAgencyId(Long agencyId);

    @Query(
            "SELECT v FROM Vehicle v WHERE"
                    + "(:name IS NULL OR v.name LIKE :name) AND"
                    + "(:vehicleTypeId IS NULL OR v.vehicleType.id =:vehicleTypeId)" +
                    " AND (:agencyId IS NULL OR v.agency.id =:agencyId)"


    )
    List<Vehicle> search(
            @Param("name") String name,
            @Param("vehicleTypeId") Long vehicleTypeId,
            @Param("agencyId") Long agencyId);

}


