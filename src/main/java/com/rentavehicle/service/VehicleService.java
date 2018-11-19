package com.rentavehicle.service;

import com.rentavehicle.model.Vehicle;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleService {

    Vehicle findOne(Long id);

    List<Vehicle> findAll();

    void save(Vehicle vehicle);

    List<Vehicle> findByAgencyId(Long agencyId);

    List<Vehicle> search(@Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId, @Param("agencyId") Long agencyId);
}
