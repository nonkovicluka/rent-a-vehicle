package com.rentavehicle.support;

import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.web.dto.VehicleImageDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleImageToVehicleImageDTO implements Converter<VehicleImage, VehicleImageDTO>{

	@Override
	public VehicleImageDTO convert(VehicleImage vehicleImage) {
		
		if (vehicleImage == null) {
			return null;
		}
		
		VehicleImageDTO dto = new VehicleImageDTO();
		
		dto.setId(vehicleImage.getId());
		dto.setImageSource(vehicleImage.getName());
		dto.setVehicleId(vehicleImage.getVehicle().getId());
		
		return dto;
	}
	
	public List<VehicleImageDTO> convert(List<VehicleImage> vehicleImages) {
		List<VehicleImageDTO> ret = new ArrayList<>();

		for (VehicleImage vehicleImage : vehicleImages) {
			ret.add(convert(vehicleImage));
		}

		return ret;
	}

}
