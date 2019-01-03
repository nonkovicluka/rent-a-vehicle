package com.rentavehicle.repository;

import com.rentavehicle.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(
            value = "SELECT  v.* FROM reservation r LEFT JOIN vehicle v ON v.id = r.vehicle_id GROUP BY r.vehicle_id ORDER BY count(r.vehicle_id) desc LIMIT 3",
            nativeQuery = true
    )

    List<Vehicle> top3Vehicles();

}


