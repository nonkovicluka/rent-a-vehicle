package com.rentavehicle.web.controller;

import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.service.VehicleImageService;
import com.rentavehicle.support.VehicleImageDTOToVehicleImage;
import com.rentavehicle.support.VehicleImageToVehicleImageDTO;
import com.rentavehicle.web.dto.VehicleImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicleImages")
public class ApiVehicleImageController {
	
	@Autowired
	private VehicleImageService vehicleImageService;
	
	@Autowired
	private VehicleImageDTOToVehicleImage toVehicleImage;
	
	@Autowired
	private VehicleImageToVehicleImageDTO toDTO;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VehicleImageDTO> get(@PathVariable Long id) {
		VehicleImage vehicleImage = vehicleImageService.findOne(id);

		if (vehicleImage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(vehicleImage), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<VehicleImageDTO> add(@Validated @RequestBody VehicleImageDTO vehicleImageDTO) {

		VehicleImage vehicleImage = toVehicleImage.convert(vehicleImageDTO);
		try {
			vehicleImageService.save(vehicleImage);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(vehicleImage), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<VehicleImageDTO> edit(@PathVariable Long id, @Validated @RequestBody VehicleImageDTO editedVehicleImage) {

		if (id == null || !id.equals(editedVehicleImage.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		VehicleImage converted = toVehicleImage.convert(editedVehicleImage);

		vehicleImageService.save(converted);

		return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
	}
	
}
