package com.rentavehicle.service;

import com.rentavehicle.model.Reservation;
import org.joda.time.DateTime;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationService {

    Reservation findOne(Long id);

    List<Reservation> findAll();

    void save(Reservation reservation);

    List<Reservation> findByVehicleId(Long vehicleId);

    List<Reservation> findOverlappingReservations(@Param("startDate") DateTime startDate, @Param("endDate") DateTime endDate, @Param("vehicleId") Long vehicleId);

}
