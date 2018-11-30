package com.rentavehicle.repository;

import com.rentavehicle.model.Reservation;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByVehicleId(Long vehicleId);


    @Query(
            "SELECT r FROM Reservation r WHERE"
                    + "(:startDate < r.endDate) " +
                    "AND (:endDate > r.startDate)" +
                    " AND (r.vehicle.id = :vehicleId)"


    )
    List<Reservation> findOverlappingReservations(@Param("startDate") DateTime startDate,
                                                  @Param("endDate") DateTime endDate,
                                                  @Param("vehicleId") Long vehicleId);


    @Query(
            "SELECT res FROM Reservation res LEFT JOIN res.vehicle v " +
                    "INNER JOIN v.agency WHERE v.agency.id = :agencyId ORDER BY v.name"


    )
    List<Reservation> agencyReservations(@Param("agencyId") Long agencyId);


    @Query(
            "SELECT SUM(res.totalPrice) FROM Reservation res LEFT JOIN res.vehicle v " +
                    "INNER JOIN v.agency WHERE v.agency.id = :agencyId"


    )
    double getTotalEarningsByAgency(@Param("agencyId") Long agencyId);

}
