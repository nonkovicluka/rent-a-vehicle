package com.rentavehicle.support;

import com.rentavehicle.model.Branch;
import com.rentavehicle.model.Reservation;
import com.rentavehicle.model.User;
import com.rentavehicle.model.Vehicle;
import com.rentavehicle.service.BranchService;
import com.rentavehicle.service.ReservationService;
import com.rentavehicle.service.UserService;
import com.rentavehicle.service.VehicleService;
import com.rentavehicle.web.dto.ReservationDTO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationDTOToReservation implements Converter<ReservationDTO, Reservation> {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BranchService branchService;

    @Override
    public Reservation convert(ReservationDTO dto) {

        Reservation reservation;

        if (dto.getId() == null) {
            User user = userService.findOne(dto.getUserId());
            Vehicle vehicle = vehicleService.findOne(dto.getVehicleId());
            Branch branchPickup = branchService.findOne(dto.getBranchPickupId());
            Branch branchDelivery = branchService.findOne(dto.getBranchDeliveryId());
            reservation = new Reservation();
            reservation.setUser(user);
            reservation.setVehicle(vehicle);
            reservation.setBranchPickup(branchPickup);
            reservation.setBranchDelivery(branchDelivery);
        } else {
            reservation = reservationService.findOne(dto.getId());
        }

        String inputStart = dto.getStartDate();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTime dtStart = formatter.parseDateTime(inputStart);


        String inputEnd = dto.getEndDate();

        DateTime dtEnd = formatter.parseDateTime(inputEnd);

        reservation.setStartDate(dtStart);
        reservation.setEndDate(dtEnd);
        reservation.setTotalPrice(dto.getTotalPrice());

        return reservation;
    }

    public List<Reservation> convert(List<ReservationDTO> dtos) {
        List<Reservation> reservations = new ArrayList<>();

        for (ReservationDTO dto : dtos) {
            reservations.add(convert(dto));
        }

        return reservations;
    }

}
