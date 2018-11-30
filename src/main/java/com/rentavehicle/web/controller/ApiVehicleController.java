package com.rentavehicle.web.controller;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.model.Vehicle;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.service.VehicleService;
import com.rentavehicle.support.VehicleDTOToVehicle;
import com.rentavehicle.support.VehiclePliDTOToVehiclePli;
import com.rentavehicle.support.VehiclePliToVehiclePliDTO;
import com.rentavehicle.support.VehicleToVehicleDTO;
import com.rentavehicle.web.dto.VehicleDTO;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class ApiVehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private VehicleDTOToVehicle toVehicle;

    @Autowired
    private VehicleToVehicleDTO toDTO;

    @Autowired
    private VehiclePliDTOToVehiclePli toVehiclePli;

    @Autowired
    private VehiclePliToVehiclePliDTO toPliDTO;

    @RequestMapping(value = "/{agencyId}/all", method = RequestMethod.GET)
    public List<VehicleDTO> get(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Long vehicleTypeId, @PathVariable @RequestParam Long agencyId) {

        List<Vehicle> vehicles;

        if (name != null || vehicleTypeId != null || agencyId != null) {
            vehicles = vehicleService.search(name, vehicleTypeId, agencyId);
        } else {
            vehicles = vehicleService.findAll();
        }


        return toDTO.convert(vehicles);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<VehicleDTO> getAll() {
        return toDTO.convert(vehicleService.findAll());

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<VehicleDTO> get(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.findOne(id);

        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(vehicle), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<VehiclePriceListItemDTO> add(@Validated @RequestBody VehiclePriceListItemDTO vehiclePliDTO) {

        VehiclePriceListItem vehiclePriceListItem = toVehiclePli.convert(vehiclePliDTO);

        Vehicle vehicle = vehiclePriceListItem.getVehicle();
        PriceListItem priceListItem = vehiclePriceListItem.getPriceListItem();

        if (priceListItem != null && vehicle != null) {
            vehicleService.save(vehicle);
            priceListItemService.save(priceListItem);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toPliDTO.convert(vehiclePriceListItem), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<VehiclePriceListItemDTO> edit(@Validated @RequestBody VehiclePriceListItemDTO vehiclePliDTO) {

        VehiclePriceListItem vehiclePriceListItem = toVehiclePli.convert(vehiclePliDTO);

        Vehicle vehicle = vehiclePriceListItem.getVehicle();
        PriceListItem priceListItem = vehiclePriceListItem.getPriceListItem();

        if (priceListItem != null && vehicle != null) {
            vehicleService.save(vehicle);
            priceListItemService.save(priceListItem);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toPliDTO.convert(vehiclePriceListItem), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<VehicleDTO> edit(@PathVariable Long id, @Validated @RequestBody VehicleDTO editedVehicle) {

        if (id == null || !id.equals(editedVehicle.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Vehicle converted = toVehicle.convert(editedVehicle);

        vehicleService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @RequestMapping(value = "/{agencyId}v", method = RequestMethod.GET)
    public List<VehicleDTO> agencyVehicles(@PathVariable Long agencyId) {
        List<Vehicle> vehicles = vehicleService.findByAgencyId(agencyId);


        return toDTO.convert(vehicles);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) {

        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
