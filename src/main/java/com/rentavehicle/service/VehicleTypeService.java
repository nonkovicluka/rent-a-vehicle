package com.rentavehicle.service;

import com.rentavehicle.model.VehicleType;

import java.util.List;

public interface VehicleTypeService {

    VehicleType findOne(Long id);

    List<VehicleType> findAll();

    void save(VehicleType vehicleType);

}
