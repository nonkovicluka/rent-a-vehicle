package com.rentavehicle.repository;

import com.rentavehicle.model.PriceList;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {

    @Query(
            "SELECT pl FROM PriceList pl WHERE"
                    + " pl.agency.id =:agencyId AND " +
                    "pl.endDate >= current_date  AND  pl.startDate <= current_date"


    )
    PriceList currentPriceList(
            @Param("agencyId") Long agencyId);



}
