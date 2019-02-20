package com.rentavehicle.web.controller;

import com.rentavehicle.model.Reservation;
import com.rentavehicle.model.Vehicle;
import com.rentavehicle.service.ReservationService;
import com.rentavehicle.support.ReservationDTOToReservation;
import com.rentavehicle.support.ReservationToReservationDTO;
import com.rentavehicle.web.dto.ReservationDTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ApiReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationDTOToReservation toReservation;

    @Autowired
    private ReservationToReservationDTO toDTO;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReservationDTO> get(@PathVariable Long id) {
        Reservation reservation = reservationService.findOne(id);

        if (reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(reservation), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ReservationDTO> add(@Validated @RequestBody ReservationDTO reservationDTO) {

        Reservation reservation = toReservation.convert(reservationDTO);


        DateTime startDate = reservation.getStartDate();
        DateTime endDate = reservation.getEndDate();

        Vehicle vehicle = reservation.getVehicle();

        List<Reservation> overlappingReservations = reservationService.findOverlappingReservations(startDate, endDate, vehicle.getId());

        DateTime now = new DateTime().minusHours(14);

        if (vehicle.isAvailable() && reservation.getTotalPrice() > 0 && overlappingReservations.size() == 0 && startDate.isAfter(now)) {

            reservationService.save(reservation);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(toDTO.convert(reservation), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ReservationDTO> edit(@PathVariable Long id, @Validated @RequestBody ReservationDTO editedReservation) {

        if (id == null || !id.equals(editedReservation.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reservation converted = toReservation.convert(editedReservation);

        reservationService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/{vehicleId}r", method = RequestMethod.GET)
    public List<ReservationDTO> vehicleReservations(@PathVariable Long vehicleId) {
        List<Reservation> reservations = reservationService.findByVehicleId(vehicleId);


        return toDTO.convert(reservations);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/agencyReservations", method = RequestMethod.GET)
    public List<ReservationDTO> getAgencyReservations(@RequestParam Long agencyId) {

        List<Reservation> reservations = reservationService.agencyReservations(agencyId);


        return toDTO.convert(reservations);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @RequestMapping(value = "/agencyTotalEarnings", method = RequestMethod.GET)
    public double getAgencyTotalEarning(@RequestParam Long agencyId) {


        return reservationService.getTotalEarningsByAgency(agencyId);
    }

}
