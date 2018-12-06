package com.rentavehicle.support;

import com.rentavehicle.model.*;
import com.rentavehicle.service.*;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehiclePliDTOToVehiclePli implements Converter<VehiclePriceListItemDTO, VehiclePriceListItem> {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Override
    public VehiclePriceListItem convert(VehiclePriceListItemDTO dto) {

        VehiclePriceListItem vehiclePriceListItem;
        Vehicle vehicle;
        PriceListItem priceListItem;

        if (dto.getVehicleId() == null && dto.getPriceListItemId() == null) {
            vehiclePriceListItem = new VehiclePriceListItem();

            vehicle = new Vehicle();

            priceListItem = new PriceListItem();


            vehiclePriceListItem.setVehicle(vehicle);
            vehiclePriceListItem.setPriceListItem(priceListItem);

        } else {

            vehicle = vehicleService.findOne(dto.getVehicleId());
            priceListItem = priceListItemService.findOne(dto.getPriceListItemId());

            vehiclePriceListItem = new VehiclePriceListItem();
            vehiclePriceListItem.setVehicle(vehicle);
            vehiclePriceListItem.setPriceListItem(priceListItem);
        }

        PriceList priceList = priceListService.findOne(dto.getPriceListId());
        vehiclePriceListItem.getPriceListItem().setVehicle(vehiclePriceListItem.getVehicle());
        vehiclePriceListItem.getPriceListItem().setPriceList(priceList);
        vehiclePriceListItem.getPriceListItem().setPricePerHour(dto.getPricePerHour());

        Agency agency = agencyService.findOne(dto.getAgencyId());
        VehicleType vehicleType = vehicleTypeService.findOne(dto.getVehicleTypeId());
        vehiclePriceListItem.getVehicle().setName(dto.getName());
        vehiclePriceListItem.getVehicle().setAvailable(dto.isAvailable());
        vehiclePriceListItem.getVehicle().setDescription(dto.getDescription());
        vehiclePriceListItem.getVehicle().setSpecification(dto.getSpecification());
        vehiclePriceListItem.getVehicle().setDeleted(dto.isDeleted());
        vehiclePriceListItem.getVehicle().setAgency(agency);
        vehiclePriceListItem.getVehicle().setVehicleType(vehicleType);

        return vehiclePriceListItem;
    }

    public List<VehiclePriceListItem> convert(List<VehiclePriceListItemDTO> dtos) {
        List<VehiclePriceListItem> vehicles = new ArrayList<>();

        for (VehiclePriceListItemDTO dto : dtos) {
            vehicles.add(convert(dto));
        }

        return vehicles;
    }

}
