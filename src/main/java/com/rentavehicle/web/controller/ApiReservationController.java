package com.rentavehicle.web.controller;

import com.rentavehicle.model.Reservation;
import com.rentavehicle.service.ReservationService;
import com.rentavehicle.support.ReservationDTOToReservation;
import com.rentavehicle.support.ReservationToReservationDTO;
import com.rentavehicle.web.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ApiReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationDTOToReservation toReservation;
	
	@Autowired
	private ReservationToReservationDTO toDTO;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservationDTO> get(@PathVariable Long id) {
		Reservation reservation = reservationService.findOne(id);

		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(reservation), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ReservationDTO> add(@Validated @RequestBody ReservationDTO reservationDTO) {

		Reservation reservation = toReservation.convert(reservationDTO);
		try {
			reservationService.save(reservation);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(reservation), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ReservationDTO> edit(@PathVariable Long id, @Validated @RequestBody ReservationDTO editedReservation) {

		if (id == null || !id.equals(editedReservation.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Reservation converted = toReservation.convert(editedReservation);

		reservationService.save(converted);

		return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
	}
	
}
