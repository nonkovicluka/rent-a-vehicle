package com.rentavehicle.service.impl;

import com.rentavehicle.model.VehicleType;
import com.rentavehicle.repository.VehicleTypeRepository;
import com.rentavehicle.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaVehicleTypeService implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleType findOne(Long id) {


        return vehicleTypeRepository.findOne(id);
    }

    @Override
    public List<VehicleType> findAll() {

        return vehicleTypeRepository.findAll();
    }

    @Override
    public void save(VehicleType vehicleType) {

        vehicleTypeRepository.save(vehicleType);
    }

}
