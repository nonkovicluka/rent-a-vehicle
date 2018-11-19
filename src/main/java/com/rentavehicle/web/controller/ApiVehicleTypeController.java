package com.rentavehicle.web.controller;

import com.rentavehicle.model.VehicleType;
import com.rentavehicle.service.VehicleTypeService;
import com.rentavehicle.support.VehicleTypeDTOToVehicleType;
import com.rentavehicle.support.VehicleTypeToVehicleTypeDTO;
import com.rentavehicle.web.dto.VehicleTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicleTypes")
public class ApiVehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private VehicleTypeDTOToVehicleType toVehicleType;

    @Autowired
    private VehicleTypeToVehicleTypeDTO toDTO;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<VehicleTypeDTO> users() {
        return toDTO.convert(vehicleTypeService.findAll());

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<VehicleTypeDTO> get(@PathVariable Long id) {
        VehicleType vehicleType = vehicleTypeService.findOne(id);

        if (vehicleType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(vehicleType), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<VehicleTypeDTO> add(@Validated @RequestBody VehicleTypeDTO vehicleTypeDTO) {

        VehicleType vehicleType = toVehicleType.convert(vehicleTypeDTO);
        try {
            vehicleTypeService.save(vehicleType);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(vehicleType), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<VehicleTypeDTO> edit(@PathVariable Long id, @Validated @RequestBody VehicleTypeDTO editedVehicleType) {

        if (id == null || !id.equals(editedVehicleType.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        VehicleType converted = toVehicleType.convert(editedVehicleType);

        vehicleTypeService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

}
