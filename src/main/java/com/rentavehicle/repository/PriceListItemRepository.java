package com.rentavehicle.repository;

import com.rentavehicle.model.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long> {


    @Query(
            "SELECT pli FROM PriceListItem pli WHERE"
                    + " pli.priceList.id =:priceListId AND " +
                    "pli.vehicle.id =:vehicleId"

    )
    PriceListItem currentPriceLIstItem(@Param("priceListId") Long priceListId, @Param("vehicleId") Long vehicleId);

}
