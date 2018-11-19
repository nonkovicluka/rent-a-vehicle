package com.rentavehicle.support;

import com.rentavehicle.model.VehicleType;
import com.rentavehicle.web.dto.VehicleTypeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleTypeToVehicleTypeDTO implements Converter<VehicleType, VehicleTypeDTO>{

	@Override
	public VehicleTypeDTO convert(VehicleType vehicleType) {
		

		if (vehicleType == null) {
			return null;
		}
		
		VehicleTypeDTO dto = new VehicleTypeDTO();
		
		dto.setId(vehicleType.getId());
		dto.setName(vehicleType.getName());
		
		return dto;
	}
	
	public List<VehicleTypeDTO> convert(List<VehicleType> vehicleTypes) {
		List<VehicleTypeDTO> ret = new ArrayList<>();

		for (VehicleType vehicleType : vehicleTypes) {
			ret.add(convert(vehicleType));
		}

		return ret;
	}

}
