package com.rentavehicle.support;

import com.rentavehicle.model.VehicleType;
import com.rentavehicle.service.VehicleTypeService;
import com.rentavehicle.web.dto.VehicleTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleTypeDTOToVehicleType implements Converter<VehicleTypeDTO, VehicleType>{
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@Override
	public VehicleType convert(VehicleTypeDTO dto) {
		
		VehicleType vehicleType;
		
		if(dto.getId() == null) {
			vehicleType = new VehicleType();
		}else {
			vehicleType = vehicleTypeService.findOne(dto.getId());
		}
		
		vehicleType.setName(dto.getName());
		
		return vehicleType;
	}
	
	public List<VehicleType> convert(List<VehicleTypeDTO> dtos) {
		List<VehicleType> vehicleTypes = new ArrayList<>();

		for (VehicleTypeDTO dto : dtos) {
			vehicleTypes.add(convert(dto));
		}

		return vehicleTypes;
	}

}
