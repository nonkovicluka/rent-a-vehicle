package com.rentavehicle.service;

import com.rentavehicle.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleService {

    List<Vehicle> top3Vehicles();

    Vehicle findOne(Long id);

    List<Vehicle> findAll();

    void save(Vehicle vehicle);

}
