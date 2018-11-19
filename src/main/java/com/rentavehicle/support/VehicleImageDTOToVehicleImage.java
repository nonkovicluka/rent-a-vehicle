package com.rentavehicle.support;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.service.VehicleImageService;
import com.rentavehicle.service.VehicleService;
import com.rentavehicle.web.dto.VehicleImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleImageDTOToVehicleImage implements Converter<VehicleImageDTO, VehicleImage>{
	
	@Autowired
	private VehicleImageService vehicleImageService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Override
	public VehicleImage convert(VehicleImageDTO dto) {
		
		VehicleImage vehicleImage;
		
		if(dto.getId() == null) {
			Vehicle vehicle = vehicleService.findOne(dto.getVehicleId());
			vehicleImage = new VehicleImage();
			vehicleImage.setVehicle(vehicle);
		}else {
			vehicleImage = vehicleImageService.findOne(dto.getId());
		}
		
		vehicleImage.setImageSource(dto.getImageSource());
		
		return vehicleImage;
	}
	
	public List<VehicleImage> convert(List<VehicleImageDTO> dtos) {
		List<VehicleImage> vehicleImages = new ArrayList<>();

		for (VehicleImageDTO dto : dtos) {
			vehicleImages.add(convert(dto));
		}

		return vehicleImages;
	}

}
