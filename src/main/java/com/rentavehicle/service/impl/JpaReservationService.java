package com.rentavehicle.service.impl;

import com.rentavehicle.model.Reservation;
import com.rentavehicle.repository.ReservationRepository;
import com.rentavehicle.repository.VehicleRepository;
import com.rentavehicle.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaReservationService implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

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

        reservation.getVehicle().setAvailable(false);

        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByVehicleId(Long vehicleId) {

        return reservationRepository.findByVehicleId(vehicleId);
    }

}
