package com.rentavehicle.support;

import com.rentavehicle.web.dto.VehiclePriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehiclePliToVehiclePliDTO implements Converter<VehiclePriceListItem, VehiclePriceListItemDTO> {

    @Override
    public VehiclePriceListItemDTO convert(VehiclePriceListItem vehicle) {

        if (vehicle == null) {
            return null;
        }

        VehiclePriceListItemDTO dto = new VehiclePriceListItemDTO();

        dto.setVehicleId(vehicle.getVehicle().getId());
        dto.setName(vehicle.getVehicle().getName());
        dto.setDescription(vehicle.getVehicle().getDescription());
        dto.setAvailable(vehicle.getVehicle().isAvailable());
        dto.setSpecification(vehicle.getVehicle().getSpecification());
        dto.setVehicleTypeId(vehicle.getVehicle().getVehicleType().getId());
        dto.setVehicleTypeName(vehicle.getVehicle().getVehicleType().getName());
        dto.setAgencyId(vehicle.getVehicle().getAgency().getId());
        dto.setAgencyName(vehicle.getVehicle().getAgency().getName());
        dto.setDeleted(vehicle.getVehicle().isDeleted());
        dto.setPriceListItemId(vehicle.getPriceListItem().getId());
        dto.setPriceListId(vehicle.getPriceListItem().getPriceList().getId());
        dto.setPricePerHour(vehicle.getPriceListItem().getPricePerHour());

        return dto;
    }

    public List<VehiclePriceListItemDTO> convert(List<VehiclePriceListItem> vehicles) {
        List<VehiclePriceListItemDTO> ret = new ArrayList<>();

        for (VehiclePriceListItem vehicle : vehicles) {
            ret.add(convert(vehicle));
        }

        return ret;
    }

}
