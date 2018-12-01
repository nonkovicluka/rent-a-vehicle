package com.rentavehicle.service.impl;

import com.rentavehicle.model.Reservation;
import com.rentavehicle.repository.ReservationRepository;
import com.rentavehicle.service.ReservationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaReservationService implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation findOne(Long id) {


        return reservationRepository.findOne(id);
    }

    @Override
    public List<Reservation> findAll() {

        return reservationRepository.findAll();
    }

    @Override
    public void save(Reservation reservation) {

        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByVehicleId(Long vehicleId) {

        return reservationRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<Reservation> findOverlappingReservations(DateTime startDate, DateTime endDate, Long vehicleId) {

        return reservationRepository.findOverlappingReservations(startDate, endDate, vehicleId);
    }

    @Override
    public List<Reservation> agencyReservations(Long agencyId) {

        return reservationRepository.agencyReservations(agencyId);
    }

    @Override
    public double getTotalEarningsByAgency(Long agencyId) {

        return reservationRepository.getTotalEarningsByAgency(agencyId);
    }

    @Override
    public List<Reservation> findFinishedReservation(Long agencyId, Long userId) {

        return reservationRepository.findFinishedReservation(agencyId, userId);
    }

}
