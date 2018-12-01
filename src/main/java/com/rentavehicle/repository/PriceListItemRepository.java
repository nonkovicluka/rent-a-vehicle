package com.rentavehicle.repository;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long> {


    @Query(
            "SELECT new com.rentavehicle.web.dto.VehiclePriceListItem(v, pli) FROM PriceListItem pli LEFT JOIN pli.vehicle v " +
                    "INNER JOIN pli.priceList WHERE pli.priceList.startDate <= current_date AND pli.priceList.endDate >= current_date " +
                    "AND v.agency.id = :agencyId AND (:name IS NULL OR v.name LIKE :name) AND (:vehicleTypeId IS NULL OR v.vehicleType.id =:vehicleTypeId) AND v.deleted = false"


    )
    List<VehiclePriceListItem> currentPriceLIstItem(@Param("agencyId") Long agencyId, @Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId);


    @Query(
            "SELECT new com.rentavehicle.web.dto.VehiclePriceListItem(v, pli) FROM PriceListItem pli LEFT JOIN pli.vehicle v " +
                    "INNER JOIN pli.priceList WHERE pli.priceList.id = :priceListId " +
                    "AND v.agency.id = :agencyId AND (:name IS NULL OR v.name LIKE :name) AND (:vehicleTypeId IS NULL OR v.vehicleType.id =:vehicleTypeId) AND v.deleted = false"


    )
    List<VehiclePriceListItem> selectedPriceListItem(@Param("priceListId") Long priceListId, @Param("agencyId") Long agencyId, @Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId);

}
