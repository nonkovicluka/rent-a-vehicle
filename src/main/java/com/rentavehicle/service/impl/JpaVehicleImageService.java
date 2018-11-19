package com.rentavehicle.service.impl;

import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.repository.VehicleImageRepository;
import com.rentavehicle.service.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaVehicleImageService implements VehicleImageService {

	@Autowired
	private VehicleImageRepository vehicleImageRepository;

	@Override
	public VehicleImage findOne(Long id) {

		return vehicleImageRepository.findOne(id);
	}

	@Override
	public List<VehicleImage> findAll() {

		return vehicleImageRepository.findAll();
	}

	@Override
	public void save(VehicleImage vehicleImage) {

		vehicleImageRepository.save(vehicleImage);
	}

}
