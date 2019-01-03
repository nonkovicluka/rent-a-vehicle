package com.rentavehicle.service;

import com.rentavehicle.model.Reservation;
import com.rentavehicle.model.Vehicle;
import org.joda.time.DateTime;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationService {

    Reservation findOne(Long id);


    List<Reservation> findAll();

    void save(Reservation reservation);

    List<Reservation> findByVehicleId(Long vehicleId);

    List<Reservation> findOverlappingReservations(@Param("startDate") DateTime startDate, @Param("endDate") DateTime endDate, @Param("vehicleId") Long vehicleId);

    List<Reservation> agencyReservations(@Param("agencyId") Long agencyId);

    double getTotalEarningsByAgency(@Param("agencyId") Long agencyId);

    List<Reservation> findFinishedReservation(@Param("agencyId") Long agencyId, @Param("userId") Long userId);

}
