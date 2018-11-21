package com.rentavehicle.web.controller;

import com.rentavehicle.model.Vehicle;
import com.rentavehicle.service.VehicleService;
import com.rentavehicle.support.VehicleDTOToVehicle;
import com.rentavehicle.support.VehicleToVehicleDTO;
import com.rentavehicle.web.dto.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    private VehicleDTOToVehicle toVehicle;

    @Autowired
    private VehicleToVehicleDTO toDTO;

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
    public ResponseEntity<VehicleDTO> add(@Validated @RequestBody VehicleDTO vehicleDTO) {

        Vehicle vehicle = toVehicle.convert(vehicleDTO);
        try {
            vehicleService.save(vehicle);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(vehicle), HttpStatus.CREATED);
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

}
