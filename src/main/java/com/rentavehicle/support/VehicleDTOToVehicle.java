package com.rentavehicle.support;

import com.rentavehicle.model.Agency;
import com.rentavehicle.model.Vehicle;
import com.rentavehicle.model.VehicleType;
import com.rentavehicle.service.AgencyService;
import com.rentavehicle.service.VehicleService;
import com.rentavehicle.service.VehicleTypeService;
import com.rentavehicle.web.dto.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDTOToVehicle implements Converter<VehicleDTO, Vehicle> {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Override
    public Vehicle convert(VehicleDTO dto) {

        Vehicle vehicle;

        if (dto.getId() == null) {
            Agency agency = agencyService.findOne(dto.getAgencyId());
            VehicleType vehicleType = vehicleTypeService.findOne(dto.getVehicleTypeId());
            vehicle = new Vehicle();
            vehicle.setAgency(agency);
            vehicle.setVehicleType(vehicleType);
        } else {
            vehicle = vehicleService.findOne(dto.getId());
        }
        vehicle.setName(dto.getName());
        vehicle.setAvailable(dto.isAvailable());
        vehicle.setDescription(dto.getDescription());
        vehicle.setSpecification(dto.getSpecification());
        vehicle.setDeleted(dto.isDeleted());

        return vehicle;
    }

    public List<Vehicle> convert(List<VehicleDTO> dtos) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (VehicleDTO dto : dtos) {
            vehicles.add(convert(dto));
        }

        return vehicles;
    }

}
