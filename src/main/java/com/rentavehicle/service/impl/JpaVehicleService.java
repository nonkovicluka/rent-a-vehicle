package com.rentavehicle.service.impl;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.repository.VehicleRepository;
import com.rentavehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public void delete(Long id) {

        vehicleRepository.delete(id);
    }

    @Override
    public List<Vehicle> findByAgencyId(Long agencyId) {

        return vehicleRepository.findByAgencyId(agencyId);
    }

    @Override
    public List<Vehicle> search(String name, Long vehicleTypeId, Long agencyId) {
        if (name != null) {
            name = "%" + name + "%";
        }
        return vehicleRepository.search(name, vehicleTypeId, agencyId);
    }


}