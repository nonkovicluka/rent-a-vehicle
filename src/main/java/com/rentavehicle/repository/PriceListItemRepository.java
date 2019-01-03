package com.rentavehicle.repository;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long> {

    @Query(
            value = "SELECT pli.* FROM price_list_item pli " +
                    "INNER JOIN price_list pl ON pli.price_list_id = pl.id " +
                    "INNER JOIN (SELECT  v.* FROM reservation r LEFT JOIN vehicle v ON v.id = r.vehicle_id GROUP BY r.vehicle_id ORDER BY count(r.vehicle_id) desc LIMIT 3) " +
                    "AS veh ON pli.vehicle_id = veh.id WHERE pl.start_date <= now() and pl.end_date >= now()",
            nativeQuery = true
    )
    List<PriceListItem> top3Pli();


    @Query(
            "SELECT new com.rentavehicle.web.dto.VehiclePriceListItem(v, pli) FROM PriceListItem pli LEFT JOIN pli.vehicle v " +
                    "INNER JOIN pli.priceList WHERE pli.priceList.startDate <= current_date AND pli.priceList.endDate >= current_date " +
                    "AND v.agency.id = :agencyId AND (:name IS NULL OR v.name LIKE :name) AND (:vehicleTypeId IS NULL OR v.vehicleType.id =:vehicleTypeId) AND v.deleted = false"


    )
    List<VehiclePriceListItem> current(@Param("agencyId") Long agencyId, @Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId);


    @Query(
            "SELECT new com.rentavehicle.web.dto.VehiclePriceListItem(v, pli) FROM PriceListItem pli LEFT JOIN pli.vehicle v " +
                    "INNER JOIN pli.priceList WHERE pli.priceList.startDate <= current_date AND pli.priceList.endDate >= current_date " +
                    "AND v.agency.id = :agencyId AND (:name IS NULL OR v.name LIKE :name) AND (:vehicleTypeId IS NULL OR v.vehicleType.id =:vehicleTypeId) AND v.deleted = false"


    )
    Page<VehiclePriceListItem> currentPriceLIstItem(@Param("agencyId") Long agencyId, @Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId, Pageable pageRequest);


    @Query(
            "SELECT new com.rentavehicle.web.dto.VehiclePriceListItem(v, pli) FROM PriceListItem pli LEFT JOIN pli.vehicle v " +
                    "INNER JOIN pli.priceList WHERE pli.priceList.id = :priceListId " +
                    "AND v.agency.id = :agencyId AND (:name IS NULL OR v.name LIKE :name) AND (:vehicleTypeId IS NULL OR v.vehicleType.id =:vehicleTypeId) AND v.deleted = false"


    )
    Page<VehiclePriceListItem> selectedPriceListItem(@Param("priceListId") Long priceListId, @Param("agencyId") Long agencyId, @Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId, Pageable pageRequest);

}
