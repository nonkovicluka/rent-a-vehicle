package com.rentavehicle.service;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceListItemService {

    PriceListItem findOne(Long id);

    List<PriceListItem> findAll();

    void save(PriceListItem priceListItem);

    List<VehiclePriceListItem> currentPriceLIstItem(@Param("agencyId") Long agencyId, @Param("name") String name, @Param("vehicleTypeId") Long vehicleTypeId);


}
