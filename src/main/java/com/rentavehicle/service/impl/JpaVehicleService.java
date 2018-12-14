package com.rentavehicle.service.impl;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.repository.VehicleRepository;
import com.rentavehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaVehicleService implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle findOne(Long id) {


        return vehicleRepository.findOne(id);
    }

    @Override
    public List<Vehicle> findAll() {

        return vehicleRepository.findAll();
    }

    @Override
    public void save(Vehicle vehicle) {

        vehicleRepository.save(vehicle);
    }

}