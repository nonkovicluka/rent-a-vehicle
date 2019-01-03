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

    @Query(
            value = "SELECT vi.* FROM vehicle_image vi INNER JOIN " +
            "(SELECT  v.* FROM reservation r LEFT JOIN vehicle v ON v.id = r.vehicle_id GROUP BY r.vehicle_id ORDER BY count(r.vehicle_id) desc LIMIT 3) " +
            "AS veh ON vi.vehicle_id = veh.id",
            nativeQuery = true
    )
    List<VehicleImage> top3VehicleImages();

}
