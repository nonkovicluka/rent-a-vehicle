package com.rentavehicle.service;

import com.rentavehicle.model.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation findOne(Long id);

    List<Reservation> findAll();

    void save(Reservation reservation);

    List<Reservation> findByVehicleId(Long vehicleId);
}
