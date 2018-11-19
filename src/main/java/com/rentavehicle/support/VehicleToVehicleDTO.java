package com.rentavehicle.support;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.web.dto.VehicleDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleToVehicleDTO implements Converter<Vehicle, VehicleDTO> {

    @Override
    public VehicleDTO convert(Vehicle vehicle) {

        if (vehicle == null) {
            return null;
        }

        VehicleDTO dto = new VehicleDTO();

        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setDescription(vehicle.getDescription());
        dto.setAvailable(vehicle.isAvailable());
        dto.setSpecification(vehicle.getSpecification());
        dto.setVehicleTypeId(vehicle.getVehicleType().getId());
        dto.setVehicleTypeName(vehicle.getVehicleType().getName());
        dto.setAgencyId(vehicle.getAgency().getId());
        dto.setAgencyName(vehicle.getAgency().getName());
        dto.setDeleted(vehicle.isDeleted());

        return dto;
    }

    public List<VehicleDTO> convert(List<Vehicle> vehicles) {
        List<VehicleDTO> ret = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            ret.add(convert(vehicle));
        }

        return ret;
    }

}
