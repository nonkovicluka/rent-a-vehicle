package com.rentavehicle.service;

import com.rentavehicle.model.VehicleImage;

import java.util.List;

public interface VehicleImageService {
	
	VehicleImage findOne(Long id);
	List<VehicleImage> findAll();
	void save(VehicleImage vehicleImage);
	
}
