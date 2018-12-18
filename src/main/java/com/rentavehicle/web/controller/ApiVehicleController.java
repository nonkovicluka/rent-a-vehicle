package com.rentavehicle.web.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.model.Vehicle;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.service.VehicleImageService;
import com.rentavehicle.service.VehicleService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class ApiVehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private VehicleImageService vehicleImageService;

    @Autowired
    private VehicleToVehicleDTO toDTO;

    @Autowired
    private VehiclePliDTOToVehiclePli toVehiclePli;

    @Autowired
    private VehiclePliToVehiclePliDTO toPliDTO;

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

    @RequestMapping(value = "/addAll", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity<?> addAll(
            @RequestParam(required = false) MultipartFile[] vehicleImages, @RequestParam String vehicleDTO) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        VehiclePriceListItemDTO vehiclePriceListItemDTO = mapper.readValue(vehicleDTO, VehiclePriceListItemDTO.class);

        VehiclePriceListItem vehiclePriceListItem = toVehiclePli.convert(vehiclePriceListItemDTO);

        Vehicle vehicle = vehiclePriceListItem.getVehicle();
        PriceListItem priceListItem = vehiclePriceListItem.getPriceListItem();

        if (priceListItem != null && vehicle != null) {
            vehicleService.save(vehicle);
            priceListItemService.save(priceListItem);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (vehicleImages.length > 0) {
            for (MultipartFile vehicleImage : vehicleImages) {
                try {
                    vehicleImageService.createImage(vehicleImage, vehicle, vehicle.getAgency().getId());
                } catch (IOException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

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

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public ResponseEntity<VehiclePriceListItemDTO> delete(@Validated @RequestBody VehiclePriceListItemDTO vehiclePliDTO) {

        VehiclePriceListItem vehiclePriceListItem = toVehiclePli.convert(vehiclePliDTO);

        Vehicle vehicle = vehiclePriceListItem.getVehicle();

        if (vehicle != null) {
            vehicle.setDeleted(true);
            vehicleService.save(vehicle);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toPliDTO.convert(vehiclePriceListItem), HttpStatus.OK);
    }

}
